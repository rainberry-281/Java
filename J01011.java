import java.util.Scanner;

public class J01011 {
    public static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long g = gcd(a, b);
            long l = lcm(a, b);
            System.out.println(l + " " + g);
        }

        sc.close();
    }
}
