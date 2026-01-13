import java.io.*;
import java.util.Scanner;

class GiaoVien {
    private String code, name;
    private int lcb;

    public GiaoVien(String code, String name, int lcb) {
        this.code = code;
        this.name = name;
        this.lcb = lcb;
    }

    public int phucap() {
        String pc = code.substring(0, 2);
        if (pc.equals("HT")) {
            return 2000000;
        }
        if (pc.equals("HP")) {
            return 900000;
        } else {
            return 500000;
        }
    }

    public int heso() {
        String hs = code.substring(2);
        return Integer.parseInt(hs);
    }

    public int tongluong() {
        return lcb * heso() + phucap();
    }

    public String toString() {
        return String.format("%s %s %d %d %d", code, name, heso(), phucap(), tongluong());
    }
}

public class J04015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = sc.nextLine();
        String name = sc.nextLine();
        int lcb = sc.nextInt();
        GiaoVien a = new GiaoVien(code, name, lcb);
        System.out.println(a);
    }
}