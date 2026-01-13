import java.util.*;

public class J01009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[21];
        arr[1] = 1;
        long total = 1;
        for(int i=2; i<=n; i++){
            arr[i] = arr[i-1] * i;
            total += arr[i];
        }
        System.out.println(total);
        sc.close();
    }
}
