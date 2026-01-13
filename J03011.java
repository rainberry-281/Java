import java.util.*;
import java.math.*;

public class J03011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t --> 0){
            String a = sc.nextLine();
            String b = sc.nextLine();
            BigInteger ai = new BigInteger(a);
            BigInteger bi = new BigInteger(b);
            BigInteger result = ai.gcd(bi);
            System.out.println(result);
        }
    }
}
