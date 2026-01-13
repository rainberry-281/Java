import java.util.Scanner;

public class J02019{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        int[] sumDiv = new int[b + 1];
        for (int i = 1; i <= b; i++) sumDiv[i] = 1;
        sumDiv[1] = 0;
        for (int i = 2; i <= b / 2; i++) {
            for (int j = 2 * i; j <= b; j += i) {
                sumDiv[j] += i;
            }
        }
        int count = 0;
        for (int n = a; n <= b; n++) {
            if (sumDiv[n] > n) count++;
        }

        System.out.println(count);
    }
}
