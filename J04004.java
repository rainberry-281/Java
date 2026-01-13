import java.util.*;

class PhanSo {
    private long tu, mau;

    public PhanSo(long tu, long mau) {
        this.tu = tu / gcd(tu, mau);
        this.mau = mau / gcd(tu, mau);
    }

    public long gcd(long x, long y) {
        while (y > 0) {
            long tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }

    public PhanSo tongps(PhanSo that) {
        long tu = this.tu * that.mau + this.mau * that.tu;
        long mau = this.mau * that.mau;
        return new PhanSo(tu, mau);
    }

    public String toString() {
        return String.format("%d/%d", tu, mau);
    }
}

public class J04004 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long tu1 = sc.nextLong();
        long mau1 = sc.nextLong();
        long tu2 = sc.nextLong();
        long mau2 = sc.nextLong();
        PhanSo ps1 = new PhanSo(tu1, mau1);
        PhanSo ps2 = new PhanSo(tu2, mau2);
        PhanSo tps = ps1.tongps(ps2);
        System.out.println(tps);
    }
}
