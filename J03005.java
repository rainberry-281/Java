import java.util.*;

public class J03005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while( t --> 0){
            String s = sc.nextLine();
            String arr[] = s.trim().toLowerCase().split("\\s+");
            StringBuilder res = new StringBuilder();
            for(int i=1; i<arr.length; i++){
                res.append(Character.toUpperCase(arr[i].charAt(0)))
                   .append(arr[i].substring(1))
                   .append(" ");
            }
            System.out.println(res.toString().trim() + ", " + arr[0].toUpperCase());
        }
    }
}
