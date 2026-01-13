import java.util.*;

public class J03021 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while(t --> 0){
            String s = sc.nextLine().toUpperCase();
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<s.length(); i++){
                char c = s.charAt(i);
                if (c == 'A' || c == 'B' || c == 'C') sb.append('2');
                else if (c == 'D' || c == 'E' || c == 'F') sb.append('3');
                else if (c == 'G' || c == 'H' || c == 'I') sb.append('4');
                else if (c == 'J' || c == 'K' || c == 'L') sb.append('5');
                else if (c == 'M' || c == 'N' || c == 'O') sb.append('6');
                else if (c == 'P' || c == 'Q' || c == 'R' || c == 'S') sb.append('7');
                else if (c == 'T' || c == 'U' || c == 'V') sb.append('8');
                else if (c == 'W' || c == 'X' || c == 'Y' || c == 'Z') sb.append('9');
            }
            String tmp = sb.toString();
            String a = sb.reverse().toString();
            if(tmp.equals(a)){
                System.out.println("YES");
            }
            else System.out.println("NO");
        }
    }
}
