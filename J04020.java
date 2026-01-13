import java.util.*;

class Pair<P1, P2> {
    private P1 a;
    private P2 b;

    public Pair(P1 a, P2 b) {
        this.a = a;
        this.b = b;
    }

    private boolean check(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public String toString() {
        return String.format("%d %d", a, b);
    }

    public boolean isPrime() {
        return check((Integer) a) && check((Integer) b);
    }
}

public class J04020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            boolean check = false;
            for (int i = 2; i <= 2 * Math.sqrt(n); i++) {
                Pair<Integer, Integer> p = new Pair<>(i, n - i);
                if (p.isPrime()) {
                    System.out.println(p);
                    check = true;
                    break;
                }
            }
            if (!check)
                System.out.println(-1);
        }
    }
}
