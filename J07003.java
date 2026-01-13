import java.util.*;
import java.io.*;
import java.math.*;

public class J07003 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("DATA.in"));
        if(!sc.hasNext()){
            sc.close();
            return;
        }
        String s = sc.next();
        while(s.length() > 1){
            int n = s.length() / 2;
            int mid = n;
            String left = s.substring(0, mid);
            String right = s.substring(mid);
            BigInteger a = left.isEmpty() ? BigInteger.ZERO : new BigInteger(left);
            BigInteger b = right.isEmpty() ? BigInteger.ZERO : new BigInteger(right);
            BigInteger sum = a.add(b);
            s = sum.toString();
            System.out.println(s);
        }
        sc.close();
    }
}
