import java.io.*;
import java.util.*;

class ThiSinh implements Comparable<ThiSinh> {
    private String code, name, status;
    private Double toan, ly, hoa, ut, total;

    public ThiSinh(String code, String name, Double toan, Double ly, Double hoa) {
        this.code = code;
        this.name = chuanhoa(name);
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
        this.ut = uutien();
        this.total = this.toan * 2 + this.ly + this.hoa + this.ut;
    }

    public Double uutien() {
        String kv = code.substring(0, 3);
        if (kv.equals("KV1"))
            return 0.5;
        if (kv.equals("KV2"))
            return 1.0;
        return 2.5;
    }

    public String chuanhoa(String s) {
        String[] arr = s.split("\\s+");
        String result = "";
        for (String i : arr) {
            result += Character.toUpperCase(i.charAt(0)) + i.substring(1).toLowerCase() + " ";
        }
        return result.trim();
    }

    public Double getTotal() {
        return total;
    }

    public String setStatus(Double diemchuan) {
        if (total >= diemchuan)
            status = "TRUNG TUYEN";
        else
            status = "TRUOT";
        return status;
    }

    public int compareTo(ThiSinh that) {
        return Double.compare(that.total, this.total);
    }

    public String chuanhoadiem(Double s) {
        if (s == s.intValue()) {
            return String.format("%d", s.intValue());
        } else {
            return String.format("%.1f", s);
        }
    }

    public String toString() {
        return String.format("%s %s %s %s %s", code, name, chuanhoadiem(ut), chuanhoadiem(total), status);
    }
}

public class J07052 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("THISINH.in"));
        int t = Integer.parseInt(sc.nextLine().trim());
        ArrayList<ThiSinh> arr = new ArrayList<>();
        while (t-- > 0) {
            String code = sc.nextLine().trim();
            String name = sc.nextLine().trim();
            Double toan = Double.parseDouble(sc.nextLine().trim());
            Double ly = Double.parseDouble(sc.nextLine().trim());
            Double hoa = Double.parseDouble(sc.nextLine().trim());
            arr.add(new ThiSinh(code, name, toan, ly, hoa));
        }

        int chitieu = Integer.parseInt(sc.nextLine().trim());
        Collections.sort(arr);
        Double diemchuan = arr.get(chitieu - 1).getTotal();
        if (diemchuan == diemchuan.intValue()) {
            System.out.println(diemchuan.intValue());
        } else {
            System.out.println(diemchuan);
        }
        for (ThiSinh i : arr) {
            i.setStatus(diemchuan);
            System.out.println(i);
        }
    }
}