import java.util.Scanner;

public class J01017 {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            String n = sc.next();
            boolean ok = true;
            for (int i = 0; i < n.length() - 1; i++) {
                int d1 = n.charAt(i) - '0';
                int d2 = n.charAt(i + 1) - '0';
                if (Math.abs(d1 - d2) != 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
