import java.util.*;
import java.math.*;

public class J03013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t --> 0){
            String a = sc.nextLine();
            String b = sc.nextLine();
            BigInteger ai = new BigInteger(a);
            BigInteger bi = new BigInteger(b);
            if(ai.compareTo(bi) < 0) {
                BigInteger ci = ai;
                ai = bi;
                bi = ci;
            }
            BigInteger result = ai.subtract(bi);
            String s = result.toString();
            int maxlen = Math.max(a.length(), b.length());
            if(s.length() < maxlen){
                s = "0" + s;
            }
            System.out.println(s);
        }
    }
}
