import java.io.*;
import java.util.*;

class DoanhNghiep implements Comparable<DoanhNghiep> {
    private String code, name;
    private int svv;

    public DoanhNghiep(String code, String name, int svv) {
        this.code = code;
        this.name = name;
        this.svv = svv;
    }

    public int compareTo(DoanhNghiep that) {
        return this.code.compareTo(that.code);
    }

    public String toString() {
        return String.format("%s %s %d", code, name, svv);
    }
}

public class J07037 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("DN.in"));
        int t = Integer.parseInt(sc.nextLine().trim());
        ArrayList<DoanhNghiep> arr = new ArrayList<>();
        while (t-- > 0) {
            String code = sc.nextLine().trim();
            String name = sc.nextLine().trim();
            int svv = Integer.parseInt(sc.nextLine().trim());
            arr.add(new DoanhNghiep(code, name, svv));
        }
        Collections.sort(arr);
        for (DoanhNghiep i : arr) {
            System.out.println(i);
        }
    }
}
