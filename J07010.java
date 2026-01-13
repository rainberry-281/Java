import java.util.*;
import java.io.*;

class SinhVien {
    private String msv, name, lop, birth;
    private double gpa;
    private static int count = 1;

    public SinhVien(String msv, String name, String lop, String birth, double gpa) {
        this.msv = msv;
        this.name = name;
        this.lop = lop;
        this.birth = chuanhoa(birth);
        this.gpa = gpa;
    }

    public String chuanhoa(String s) {
        String[] arr = s.split("/");
        if (arr[0].length() < 2) {
            arr[0] = "0" + arr[0];
        }
        if (arr[1].length() < 2) {
            arr[1] = "0" + arr[1];
        }
        return arr[0] + "/" + arr[1] + "/" + arr[2];
    }

    public String toString() {
        return String.format("%s %s %s %s %.2f", msv, name, lop, birth, gpa);
    }
}

public class J07010 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("SV.in"));
        int t = Integer.parseInt(sc.nextLine().trim());
        int count = 1;
        while (t-- > 0) {
            String msv = String.format("B20DCCN%03d", count++);
            String name = sc.nextLine().trim();
            String lop = sc.nextLine().trim();
            String birth = sc.nextLine().trim();
            double gpa = Double.parseDouble(sc.nextLine().trim());
            SinhVien a = new SinhVien(msv, name, lop, birth, gpa);
            System.out.println(a);
        }
    }
}