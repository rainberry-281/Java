import java.util.*;

public class J01010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();   
        while(t --> 0){
            String s = sc.next(); 
            StringBuilder res = new StringBuilder();
            boolean invalid = false;
            for (char x : s.toCharArray()) {
                switch (x) {
                    case '0':
                        res.append('0');
                        break;
                    case '1':
                        res.append('1');
                        break;
                    case '8':
                    case '9':
                        res.append('0');
                        break;
                    default:
                        invalid = true;
                        break;
                }
                if (invalid) break;
            }
            if (invalid) {
                System.out.println("INVALID");
                continue;
            }
            String result = res.toString().replaceFirst("^0+", "");
            if (result.isEmpty()) System.out.println("INVALID");
            else System.out.println(result);
        }
        sc.close();
    }
}
