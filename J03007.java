import java.util.*;

public class J03007 {
    static boolean isBeautiful(String s) {
        if (s.charAt(0) != '8' || s.charAt(s.length() - 1) != '8')
            return false;
        int sum = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
            sum += s.charAt(i) - '0';
        }
        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine().trim());
        while (T-- > 0) {
            String s = sc.nextLine().trim();
            System.out.println(isBeautiful(s) ? "YES" : "NO");
        }
        sc.close();
    }
}

