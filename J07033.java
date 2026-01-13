import java.util.*;
import java.io.*;

class SinhVien implements Comparable<SinhVien> {
    private String code, name, lop, email;

    public SinhVien(String code, String name, String lop, String email) {
        this.code = code;
        this.name = chuanhoa(name);
        this.lop = lop;
        this.email = email;
    }

    public String chuanhoa(String s) {
        String[] arr = s.split("\\s+");
        String result = "";
        for (String i : arr) {
            result += Character.toUpperCase(i.charAt(0)) + i.substring(1).toLowerCase() + " ";
        }
        return result.strip();
    }

    public int compareTo(SinhVien that) {
        return this.code.compareTo(that.code);
    }

    public String toString() {
        return String.format("%s %s %s %s", code, name, lop, email);
    }
}

public class J07033 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("SINHVIEN.in"));
        int t = Integer.parseInt(sc.nextLine().trim());
        ArrayList<SinhVien> arr = new ArrayList<>();
        while (t-- > 0) {
            String code = sc.nextLine().trim();
            String name = sc.nextLine().trim();
            String lop = sc.nextLine().trim();
            String email = sc.nextLine().trim();
            arr.add(new SinhVien(code, name, lop, email));
        }
        Collections.sort(arr);
        for (SinhVien i : arr) {
            System.out.println(i);
        }
    }
}