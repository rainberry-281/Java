import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class PayrollManager extends JFrame {
    // File names
    private static final String FILE_CN = "CN.TXT";
    private static final String FILE_SP = "SP.TXT";
    private static final String FILE_TINHCONG = "TINHCONG.TXT";

    // Data stores
    private java.util.List<Worker> workers = new ArrayList<>();
    private java.util.List<Product> products = new ArrayList<>();
    private java.util.List<WorkSheet> sheets = new ArrayList<>();

    // GUI components
    private DefaultTableModel workerTableModel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel sheetTableModel;

    private JTable workerTable;
    private JTable productTable;
    private JTable sheetTable;

    // Shift multipliers (editable by user)
    private Map<Shift, Double> shiftMultiplier = new EnumMap<>(Shift.class);

    private DecimalFormat moneyFmt = new DecimalFormat("#,##0.00");

    public PayrollManager() {
        super("Quản lý Tính Công - Java GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // default multipliers
        shiftMultiplier.put(Shift.SANG, 1.0);
        shiftMultiplier.put(Shift.CHIEU, 1.05);
        shiftMultiplier.put(Shift.DEM, 1.2);

        loadAllFiles();
        initUI();
    }

    private void loadAllFiles() {
        loadWorkers();
        loadProducts();
        loadSheets();
    }

    private void loadWorkers() {
        workers.clear();
        try {
            if (!Files.exists(Paths.get(FILE_CN)))
                return;
            BufferedReader br = new BufferedReader(new FileReader(FILE_CN));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
                String[] parts = line.split("\\|");
                if (parts.length < 4)
                    continue;
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String addr = parts[2];
                Shift s = Shift.fromString(parts[3]);
                workers.add(new Worker(id, name, addr, s));
            }
            br.close();
        } catch (Exception e) {
            showError("Lỗi khi đọc " + FILE_CN + ": " + e.getMessage());
        }
    }

    private void saveWorkers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_CN))) {
            for (Worker w : workers) {
                pw.println(String.format("%05d|%s|%s|%s", w.getId(), w.getName(), w.getAddress(), w.getShift()));
            }
        } catch (Exception e) {
            showError("Lỗi khi lưu " + FILE_CN + ": " + e.getMessage());
        }
    }

    private void loadProducts() {
        products.clear();
        try {
            if (!Files.exists(Paths.get(FILE_SP)))
                return;
            BufferedReader br = new BufferedReader(new FileReader(FILE_SP));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
                String[] parts = line.split("\\|");
                if (parts.length < 3)
                    continue;
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                products.add(new Product(id, name, price));
            }
            br.close();
        } catch (Exception e) {
            showError("Lỗi khi đọc " + FILE_SP + ": " + e.getMessage());
        }
    }

    private void saveProducts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_SP))) {
            for (Product p : products) {
                pw.println(String.format("%05d|%s|%f", p.getId(), p.getName(), p.getUnitPrice()));
            }
        } catch (Exception e) {
            showError("Lỗi khi lưu " + FILE_SP + ": " + e.getMessage());
        }
    }

    private void loadSheets() {
        sheets.clear();
        try {
            if (!Files.exists(Paths.get(FILE_TINHCONG)))
                return;
            BufferedReader br = new BufferedReader(new FileReader(FILE_TINHCONG));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split("\\|");
                if (parts.length < 4)
                    continue;
                int workerId = Integer.parseInt(parts[0]);
                String workerName = parts[1];
                Shift s = Shift.fromString(parts[2]);
                String prods = parts[3];
                Map<Integer, Integer> map = new LinkedHashMap<>();
                if (!prods.isEmpty()) {
                    String[] items = prods.split(",");
                    for (String it : items) {
                        String[] kv = it.split(":");
                        if (kv.length < 2)
                            continue;
                        int pid = Integer.parseInt(kv[0]);
                        int qty = Integer.parseInt(kv[1]);
                        map.put(pid, qty);
                    }
                }
                sheets.add(new WorkSheet(workerId, workerName, s, map));
            }
            br.close();
        } catch (Exception e) {
            showError("Lỗi khi đọc " + FILE_TINHCONG + ": " + e.getMessage());
        }
    }

    private void saveSheets() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_TINHCONG))) {
            for (WorkSheet ws : sheets) {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (Map.Entry<Integer, Integer> en : ws.getProducts().entrySet()) {
                    if (!first)
                        sb.append(",");
                    sb.append(String.format("%05d:%d", en.getKey(), en.getValue()));
                    first = false;
                }
                pw.println(String.format("%05d|%s|%s|%s", ws.getWorkerId(), ws.getWorkerName(), ws.getShift(),
                        sb.toString()));
            }
        } catch (Exception e) {
            showError("Lỗi khi lưu " + FILE_TINHCONG + ": " + e.getMessage());
        }
    }

    private void initUI() {
        JTabbedPane tabs = new JTabbedPane();

        JPanel pWorkers = new JPanel(new BorderLayout());
        workerTableModel = new DefaultTableModel(new Object[] { "Mã CN", "Họ tên", "Địa chỉ", "Ca" }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        workerTable = new JTable(workerTableModel);
        refreshWorkerTable();
        pWorkers.add(new JScrollPane(workerTable), BorderLayout.CENTER);

        JPanel workerBtns = new JPanel();
        JButton btnAddWorker = new JButton("Thêm Công nhân");
        btnAddWorker.addActionListener(e -> addWorkerDialog());
        JButton btnReloadWorkers = new JButton("Tải lại từ file");
        btnReloadWorkers.addActionListener(e -> {
            loadWorkers();
            refreshWorkerTable();
        });
        workerBtns.add(btnAddWorker);
        workerBtns.add(btnReloadWorkers);
        pWorkers.add(workerBtns, BorderLayout.SOUTH);

        JPanel pProducts = new JPanel(new BorderLayout());
        productTableModel = new DefaultTableModel(new Object[] { "Mã SP", "Tên SP", "Đơn giá" }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        productTable = new JTable(productTableModel);
        refreshProductTable();
        pProducts.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel prodBtns = new JPanel();
        JButton btnAddProduct = new JButton("Thêm Sản phẩm");
        btnAddProduct.addActionListener(e -> addProductDialog());
        JButton btnReloadProd = new JButton("Tải lại từ file");
        btnReloadProd.addActionListener(e -> {
            loadProducts();
            refreshProductTable();
        });
        prodBtns.add(btnAddProduct);
        prodBtns.add(btnReloadProd);
        pProducts.add(prodBtns, BorderLayout.SOUTH);

        JPanel pSheets = new JPanel(new BorderLayout());
        sheetTableModel = new DefaultTableModel(
                new Object[] { "Mã CN", "Họ tên", "Ca", "Số loại SP", "Tổng số lượng", "Tổng tiền" }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        sheetTable = new JTable(sheetTableModel);
        refreshSheetTable();
        pSheets.add(new JScrollPane(sheetTable), BorderLayout.CENTER);

        JPanel sheetBtns = new JPanel();
        JButton btnCreateSheet = new JButton("Lập Bảng Tính Công");
        btnCreateSheet.addActionListener(e -> createSheetDialog());
        JButton btnSaveSheets = new JButton("Lưu TINHCONG.TXT");
        btnSaveSheets.addActionListener(e -> {
            saveSheets();
            showInfo("Đã lưu vào " + FILE_TINHCONG);
        });
        JButton btnReloadSheets = new JButton("Tải lại từ file");
        btnReloadSheets.addActionListener(e -> {
            loadSheets();
            refreshSheetTable();
        });
        JButton btnSortByName = new JButton("Sắp xếp theo Họ tên (A-Z)");
        btnSortByName.addActionListener(e -> {
            sheets.sort(Comparator.comparing(WorkSheet::getWorkerName, String.CASE_INSENSITIVE_ORDER));
            refreshSheetTable();
        });
        JButton btnSortByQty = new JButton("Sắp xếp theo Tổng số lượng (Giảm dần)");
        btnSortByQty.addActionListener(e -> {
            sheets.sort(Comparator.comparingInt(WorkSheet::totalQuantity).reversed());
            refreshSheetTable();
        });
        JButton btnCalcIncome = new JButton("Tính thu nhập chi tiết");
        btnCalcIncome.addActionListener(e -> showIncomeDialog());

        JButton btnShiftConfig = new JButton("Cấu hình hệ số ca");
        btnShiftConfig.addActionListener(e -> shiftConfigDialog());

        sheetBtns.add(btnCreateSheet);
        sheetBtns.add(btnSaveSheets);
        sheetBtns.add(btnReloadSheets);
        sheetBtns.add(btnSortByName);
        sheetBtns.add(btnSortByQty);
        sheetBtns.add(btnCalcIncome);
        sheetBtns.add(btnShiftConfig);
        pSheets.add(sheetBtns, BorderLayout.SOUTH);

        tabs.addTab("Danh sách Công nhân", pWorkers);
        tabs.addTab("Danh sách Sản phẩm", pProducts);
        tabs.addTab("Bảng Tính Công", pSheets);

        add(tabs);
    }

    private void refreshWorkerTable() {
        workerTableModel.setRowCount(0);
        for (Worker w : workers) {
            workerTableModel.addRow(new Object[] { String.format("%05d", w.getId()), w.getName(), w.getAddress(),
                    w.getShift().toString() });
        }
    }

    private void refreshProductTable() {
        productTableModel.setRowCount(0);
        for (Product p : products) {
            productTableModel.addRow(
                    new Object[] { String.format("%05d", p.getId()), p.getName(), moneyFmt.format(p.getUnitPrice()) });
        }
    }

    private void refreshSheetTable() {
        sheetTableModel.setRowCount(0);
        for (WorkSheet ws : sheets) {
            int types = ws.getProducts().size();
            int totalQty = ws.totalQuantity();
            double total = ws.computeTotal(products) * shiftMultiplier.getOrDefault(ws.getShift(), 1.0);
            sheetTableModel.addRow(new Object[] { String.format("%05d", ws.getWorkerId()), ws.getWorkerName(),
                    ws.getShift().toString(), types, totalQty, moneyFmt.format(total) });
        }
    }

    // ---------- Dialogs / Actions ----------
    private void addWorkerDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField tfName = new JTextField();
        JTextField tfAddr = new JTextField();
        JComboBox<String> cbShift = new JComboBox<>(new String[] { "SANG", "CHIEU", "DEM" });
        panel.add(new JLabel("Họ tên:"));
        panel.add(tfName);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(tfAddr);
        panel.add(new JLabel("Ca sản xuất:"));
        panel.add(cbShift);

        int res = JOptionPane.showConfirmDialog(this, panel, "Thêm Công nhân", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String name = tfName.getText().trim();
                String addr = tfAddr.getText().trim();
                String ca = (String) cbShift.getSelectedItem();
                if (name.isEmpty())
                    throw new IllegalArgumentException("Họ tên không được để trống");
                if (addr.isEmpty())
                    throw new IllegalArgumentException("Địa chỉ không được để trống");

                int nextId = 10000;
                for (Worker w : workers)
                    nextId = Math.max(nextId, w.getId());
                nextId++;
                if (String.valueOf(nextId).length() > 5)
                    nextId = Integer.parseInt(String.valueOf(nextId).substring(String.valueOf(nextId).length() - 5));
                Worker w = new Worker(nextId, name, addr, Shift.fromString(ca));
                workers.add(w);
                saveWorkers();
                refreshWorkerTable();
                showInfo("Đã thêm công nhân: " + name);
            } catch (Exception ex) {
                showError("Lỗi khi thêm công nhân: " + ex.getMessage());
            }
        }
    }

    private void addProductDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField tfName = new JTextField();
        JTextField tfPrice = new JTextField();
        panel.add(new JLabel("Tên sản phẩm:"));
        panel.add(tfName);
        panel.add(new JLabel("Đơn giá (số, >0):"));
        panel.add(tfPrice);

        int res = JOptionPane.showConfirmDialog(this, panel, "Thêm Sản phẩm", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String name = tfName.getText().trim();
                String sPrice = tfPrice.getText().trim();
                if (name.isEmpty())
                    throw new IllegalArgumentException("Tên sản phẩm không được để trống");
                if (sPrice.isEmpty())
                    throw new IllegalArgumentException("Đơn giá không được để trống");
                double price = Double.parseDouble(sPrice);
                if (price < 0)
                    throw new IllegalArgumentException("Đơn giá phải là số không âm");
                int nextId = 20000;
                for (Product p : products)
                    nextId = Math.max(nextId, p.getId());
                nextId++;
                if (String.valueOf(nextId).length() > 5)
                    nextId = Integer.parseInt(String.valueOf(nextId).substring(String.valueOf(nextId).length() - 5));
                Product p = new Product(nextId, name, price);
                products.add(p);
                saveProducts();
                refreshProductTable();
                showInfo("Đã thêm sản phẩm: " + name);
            } catch (NumberFormatException nf) {
                showError("Đơn giá phải là một số hợp lệ");
            } catch (Exception ex) {
                showError("Lỗi khi thêm sản phẩm: " + ex.getMessage());
            }
        }
    }

    private void createSheetDialog() {
        if (workers.isEmpty()) {
            showError("Chưa có công nhân trong file. Vui lòng thêm công nhân trước.");
            return;
        }
        if (products.isEmpty()) {
            showError("Chưa có sản phẩm trong file. Vui lòng thêm sản phẩm trước.");
            return;
        }

        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new GridLayout(0, 2));
        JComboBox<String> cbWorker = new JComboBox<>();
        for (Worker w : workers)
            cbWorker.addItem(String.format("%05d - %s", w.getId(), w.getName()));
        top.add(new JLabel("Chọn công nhân:"));
        top.add(cbWorker);

        panel.add(top, BorderLayout.NORTH);

        DefaultTableModel prodModel = new DefaultTableModel(new Object[] { "Mã SP", "Tên SP", "Đơn giá", "Số lượng" },
                0) {
            public boolean isCellEditable(int r, int c) {
                return c == 3;
            }
        };
        for (Product p : products)
            prodModel.addRow(new Object[] { String.format("%05d", p.getId()), p.getName(),
                    moneyFmt.format(p.getUnitPrice()), "0" });
        JTable tbl = new JTable(prodModel);
        tbl.setPreferredScrollableViewportSize(new Dimension(800, 300));
        panel.add(new JScrollPane(tbl), BorderLayout.CENTER);

        panel.add(new JLabel("Nhập số lượng (>=0) cho tối đa 5 loại sản phẩm khác 0. Không được nhập 2 lần cùng SP."),
                BorderLayout.SOUTH);

        int res = JOptionPane.showConfirmDialog(this, panel, "Lập Bảng Tính Công", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String sel = (String) cbWorker.getSelectedItem();
                int selId = Integer.parseInt(sel.split(" ")[0]);
                Worker w = workers.stream().filter(x -> x.getId() == selId).findFirst().orElse(null);
                if (w == null)
                    throw new IllegalStateException("Công nhân không tìm thấy");
                Map<Integer, Integer> entries = new LinkedHashMap<>();
                int nonZeroTypes = 0;
                for (int i = 0; i < prodModel.getRowCount(); i++) {
                    String idStr = ((String) prodModel.getValueAt(i, 0)).trim();
                    int pid = Integer.parseInt(idStr);
                    String qtyStr = String.valueOf(prodModel.getValueAt(i, 3)).trim();
                    if (qtyStr.isEmpty())
                        qtyStr = "0";
                    int qty;
                    try {
                        qty = Integer.parseInt(qtyStr);
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Số lượng phải là số nguyên tại dòng " + (i + 1));
                    }
                    if (qty < 0)
                        throw new IllegalArgumentException("Số lượng không được âm (dòng " + (i + 1) + ")");
                    if (qty > 0) {
                        nonZeroTypes++;
                        if (entries.containsKey(pid))
                            throw new IllegalArgumentException("Không được nhập trùng 1 sản phẩm nhiều lần");
                        entries.put(pid, qty);
                    }
                }
                if (nonZeroTypes == 0)
                    throw new IllegalArgumentException("Phải nhập ít nhất một loại sản phẩm có số lượng > 0");
                if (nonZeroTypes > 5)
                    throw new IllegalArgumentException(
                            "Mỗi công nhân chỉ được phép tối đa 5 loại sản phẩm khác nhau (hiện: " + nonZeroTypes
                                    + ")");

                Optional<WorkSheet> existing = sheets.stream().filter(s -> s.getWorkerId() == w.getId()).findFirst();
                if (existing.isPresent()) {
                    int ans = JOptionPane.showConfirmDialog(this,
                            "Đã tồn tại bảng tính công cho công nhân này. Bạn có muốn ghi đè?", "Ghi đè?",
                            JOptionPane.YES_NO_OPTION);
                    if (ans != JOptionPane.YES_OPTION)
                        return;
                    sheets.remove(existing.get());
                }
                WorkSheet ws = new WorkSheet(w.getId(), w.getName(), w.getShift(), entries);
                sheets.add(ws);
                saveSheets();
                refreshSheetTable();
                showInfo("Đã lập bảng tính công cho " + w.getName());
            } catch (Exception ex) {
                showError("Lỗi khi lập bảng tính công: " + ex.getMessage());
            }
        }
    }

    private void showIncomeDialog() {
        int sel = sheetTable.getSelectedRow();
        WorkSheet ws = null;
        if (sel >= 0) {
            int wid = Integer.parseInt((String) sheetTableModel.getValueAt(sel, 0));
            for (WorkSheet x : sheets)
                if (x.getWorkerId() == wid) {
                    ws = x;
                    break;
                }
        } else if (!sheets.isEmpty()) {
            ws = sheets.get(0);
        }
        if (ws == null) {
            showError("Chưa có bảng tính công. Vui lòng lập bảng trước.");
            return;
        }

        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền" }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        double sum = 0.0;
        for (Map.Entry<Integer, Integer> en : ws.getProducts().entrySet()) {
            int pid = en.getKey();
            int qty = en.getValue();
            Product p = findProduct(pid);
            String pname = (p == null) ? "[Không tìm thấy SP]" : p.getName();
            double price = (p == null) ? 0.0 : p.getUnitPrice();
            double amt = price * qty;
            sum += amt;
            model.addRow(new Object[] { String.format("%05d", pid), pname, moneyFmt.format(price), qty,
                    moneyFmt.format(amt) });
        }
        JTable tbl = new JTable(model);
        panel.add(new JScrollPane(tbl), BorderLayout.CENTER);

        JPanel south = new JPanel(new GridLayout(0, 1));
        south.add(new JLabel("Tổng trước hệ số ca: " + moneyFmt.format(sum)));
        double mult = shiftMultiplier.getOrDefault(ws.getShift(), 1.0);
        south.add(new JLabel("Ca: " + ws.getShift() + "   Hệ số: " + mult));
        south.add(new JLabel("Tổng sau hệ số: " + moneyFmt.format(sum * mult)));
        panel.add(south, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Chi tiết thu nhập - " + ws.getWorkerName(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void shiftConfigDialog() {
        JPanel p = new JPanel(new GridLayout(0, 2));
        JTextField fS = new JTextField(String.valueOf(shiftMultiplier.get(Shift.SANG)));
        JTextField fC = new JTextField(String.valueOf(shiftMultiplier.get(Shift.CHIEU)));
        JTextField fD = new JTextField(String.valueOf(shiftMultiplier.get(Shift.DEM)));
        p.add(new JLabel("Hệ số ca sáng:"));
        p.add(fS);
        p.add(new JLabel("Hệ số ca chiều:"));
        p.add(fC);
        p.add(new JLabel("Hệ số ca đêm:"));
        p.add(fD);
        int res = JOptionPane.showConfirmDialog(this, p, "Cấu hình hệ số ca", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                double s = Double.parseDouble(fS.getText().trim());
                double c = Double.parseDouble(fC.getText().trim());
                double d = Double.parseDouble(fD.getText().trim());
                if (s <= 0 || c <= 0 || d <= 0)
                    throw new IllegalArgumentException("Hệ số phải > 0");
                shiftMultiplier.put(Shift.SANG, s);
                shiftMultiplier.put(Shift.CHIEU, c);
                shiftMultiplier.put(Shift.DEM, d);
                refreshSheetTable();
            } catch (NumberFormatException nfe) {
                showError("Hệ số phải là số hợp lệ");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        }
    }

    private Product findProduct(int id) {
        for (Product p : products)
            if (p.getId() == id)
                return p;
        return null;
    }

    // ---------- Utils ----------
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PayrollManager app = new PayrollManager();
            app.setVisible(true);
        });
    }

    enum Shift {
        SANG, CHIEU, DEM;

        public static Shift fromString(String s) {
            if (s == null)
                return SANG;
            s = s.trim().toUpperCase();
            switch (s) {
                case "SANG":
                    return SANG;
                case "CHIEU":
                    return CHIEU;
                case "DEM":
                    return DEM;
                default:
                    return SANG;
            }
        }

        public String toString() {
            switch (this) {
                case SANG:
                    return "SANG";
                case CHIEU:
                    return "CHIEU";
                default:
                    return "DEM";
            }
        }
    }

    static class Worker {
        private int id;
        private String name;
        private String address;
        private Shift shift;

        public Worker(int id, String name, String address, Shift shift) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.shift = shift;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public Shift getShift() {
            return shift;
        }

        public String toString() {
            return String.format("%05d - %s", id, name);
        }
    }

    static class Product {
        private int id;
        private String name;
        private double unitPrice;

        public Product(int id, String name, double unitPrice) {
            this.id = id;
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public String toString() {
            return String.format("%05d - %s", id, name);
        }
    }

    static class WorkSheet {
        private int workerId;
        private String workerName;
        private Shift shift;
        private Map<Integer, Integer> products;

        public WorkSheet(int wid, String wname, Shift shift, Map<Integer, Integer> prods) {
            this.workerId = wid;
            this.workerName = wname;
            this.shift = shift;
            this.products = new LinkedHashMap<>(prods);
        }

        public int getWorkerId() {
            return workerId;
        }

        public String getWorkerName() {
            return workerName;
        }

        public Shift getShift() {
            return shift;
        }

        public Map<Integer, Integer> getProducts() {
            return products;
        }

        public int totalQuantity() {
            int s = 0;
            for (int q : products.values())
                s += q;
            return s;
        }

        public double computeTotal(java.util.List<Product> allProducts) {
            double sum = 0.0;
            for (Map.Entry<Integer, Integer> en : products.entrySet()) {
                int pid = en.getKey();
                int qty = en.getValue();
                Product p = null;
                for (Product pp : allProducts)
                    if (pp.getId() == pid) {
                        p = pp;
                        break;
                    }
                double price = (p == null) ? 0.0 : p.getUnitPrice();
                sum += price * qty;
            }
            return sum;
        }
    }
}
