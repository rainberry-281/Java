import java.util.*;

class MatHang implements Comparable<MatHang> {
    private String code, name, dvi;
    private int mua, ban;
    public static int counter = 1;

    public MatHang(String name, String dvi, int mua, int ban) {
        this.code = String.format("MH%03d", counter++);
        this.name = name;
        this.dvi = dvi;
        this.mua = mua;
        this.ban = ban;
    }

    public int loinhuan() {
        return ban - mua;
    }

    public int compareTo(MatHang that) {
        if (this.loinhuan() != that.loinhuan()) {
            return that.loinhuan() - this.loinhuan();
        }
        return this.code.compareTo(that.code);
    }

    public String toString() {
        return String.format("%s %s %s %d %d %d", code, name, dvi, mua, ban, loinhuan());
    }
}

public class J05081 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        // sc.nextLine();
        ArrayList<MatHang> arr = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            sc.nextLine();
            String name = sc.nextLine();
            String dvi = sc.nextLine();
            int mua = sc.nextInt();
            int ban = sc.nextInt();
            arr.add(new MatHang(name, dvi, mua, ban));
        }
        Collections.sort(arr);
        for (MatHang i : arr) {
            System.out.println(i);
        }
    }
}