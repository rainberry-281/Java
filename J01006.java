import java.util.*;

public class J01006 {
    public static long fibo(int a) {
        if(a == 1) return 1;
        if(a == 2) return 1;
        long f0 = 1, f1 = 1, fn = 0;
        for(int i=3; i<=a; i++){
            fn = (f0 + f1) % 1000000007;
            f0 = f1;
            f1 = fn;
        }
        return (long)fn % 1000000007;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while( t --> 0 ){
            int n = sc.nextInt();
            System.out.println(fibo(n));
        }
        sc.close();
    }
}
