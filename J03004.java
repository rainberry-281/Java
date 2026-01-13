import java.util.*;

public class J03004 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while( t --> 0){
            String s = sc.nextLine();
            String arr[] = s.trim().toLowerCase().split("\\s+");
            String res = "";
            for(String x : arr){
                res += Character.toUpperCase(x.charAt(0)) + x.substring(1) + " ";
            }
            System.out.println(res.trim());
        }
    }
}