import java.util.*;

class SinhVien {
    public String msv, name, lop, birth;
    public float gpa;

    public SinhVien(String msv, String name, String lop, String birth, float gpa) {
        this.msv = msv;
        this.name = name;
        this.lop = lop;
        this.birth = chuanhoa(birth);
        this.gpa = gpa;
    }

    public String chuanhoa(String birth) {
        String[] arr = birth.split("/");
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

public class J04006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msv = "B20DCCN001";
        String name = sc.nextLine();
        String lop = sc.nextLine();
        String birth = sc.nextLine();
        float gpa = sc.nextFloat();
        SinhVien a = new SinhVien(msv, name, lop, birth, gpa);
        System.out.println(a);
    }
}