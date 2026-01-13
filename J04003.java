import java.util.*;

class PhanSo {
    private long tu, mau;

    public PhanSo(long tu, long mau) {
        this.tu = tu / gcd(tu, mau);
        this.mau = mau / gcd(tu, mau);
    }

    public long gcd(long tu, long mau) {
        while (mau > 0) {
            long tmp = tu % mau;
            tu = mau;
            mau = tmp;
        }
        return tu;
    }

    public String toString() {
        return String.format("%d/%d", tu, mau);
    }
}

public class J04003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long tu = sc.nextLong();
        long mau = sc.nextLong();
        PhanSo ps = new PhanSo(tu, mau);
        System.err.println(ps);
    }
}