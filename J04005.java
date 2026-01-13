import java.io.*;
import java.util.Scanner;

class thisinh {
    private String name, birth;
    private double m1, m2, m3;

    public thisinh(String name, String birth, double m1, double m2, double m3) {
        this.name = name;
        this.birth = birth;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    public double tongdiem() {
        return m1 + m2 + m3;
    }

    public String toString() {
        return String.format("%s %s %.1f", name, birth, tongdiem());
    }
}

public class J04005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String birth = sc.nextLine();
        double m1 = sc.nextDouble();
        double m2 = sc.nextDouble();
        double m3 = sc.nextDouble();
        thisinh a = new thisinh(name, birth, m1, m2, m3);
        System.out.println(a);
    }
}