import java.util.*;

public class J01002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long a = sc.nextInt();
            long sum = (a * (a + 1)) / 2;
            System.out.println(sum);
        }
        sc.close();
    }
}
