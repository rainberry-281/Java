import java.util.*;

public class J03025 {
    static String canMakePalindrome(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                cnt++;
            }
        }
        if (cnt == 1) return "YES";
        if (cnt == 0) {
            return (n % 2 == 1) ? "YES" : "NO";
        }
        return "NO";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine().trim());
        while (T-- > 0) {
            String s = sc.nextLine().trim();
            System.out.println(canMakePalindrome(s));
        }
        sc.close();
    }
}
