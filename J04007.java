import java.io.*;
import java.util.Scanner;

class nhanvien {
    private String mnv, name, gen, birth, add, phone, contract;

    public nhanvien(String mnv, String name, String gen, String birth, String add, String phone, String contract) {
        this.mnv = mnv;
        this.name = name;
        this.gen = gen;
        this.birth = birth;
        this.add = add;
        this.phone = phone;
        this.contract = contract;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s %s", mnv, name, gen, birth, add, phone, contract);
    }
}

public class J04007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mnv = "00001";
        String name = sc.nextLine();
        String gen = sc.nextLine();
        String birth = sc.nextLine();
        String add = sc.nextLine();
        String phone = sc.nextLine();
        String contract = sc.nextLine();
        nhanvien a = new nhanvien(mnv, name, gen, birth, add, phone, contract);
        System.out.println(a);
    }
}
