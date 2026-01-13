import java.util.*;

public class J01007 {
    public static void main(String[] args) {
        long[] fibo = new long[94];
        fibo[0] = 0;
        fibo[1] = 1;
        for(int i=2; i<94; i++){
            fibo[i] = fibo[i-1] + fibo[i-2];
        }
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t --> 0){
            long n = sc.nextLong();
            boolean found = false;
            for(int i=0; i<94; i++){
                if(fibo[i] == n){
                    found = true;
                    break;
                }
                else if(fibo[i] > n){
                    break;
                }
            }
            if(found) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
