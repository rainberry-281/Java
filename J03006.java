import java.util.*;

public class J03006 {
    public static boolean isBeuti(String s){
        StringBuilder res = new StringBuilder(s);
        res.reverse();
        return s.equals(res.toString());
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t --> 0){
            String s = sc.nextLine();
            boolean check = true;
            for(char ch : s.toCharArray()){
                if((ch - '0') % 2 != 0){
                    check = false;
                    break;
                }
            }
            if(!isBeuti(s)) check = false;
            if(check) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
