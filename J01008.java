import java.util.*;

public class J01008 {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int j = 1;
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.print("Test " + j + ": ");
            j++;
            for(int i=2; i<=Math.sqrt(n); i++){
                int count = 0;
                while(n % i == 0){
                    count++;
                    n /= i;
                }
                if(count > 0){
                    System.out.print(i + "(" + count + ") ");
                }
            }
            if(n > 1){
                System.out.print(n + "(1)");
            }
            System.out.println();
        }
        sc.close();
    }
}
