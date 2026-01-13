import java.util.*;

public class J03024 {
    public static void main(String agrs[]){
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while(t --> 0){
            String s = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            if(!s.matches("[0-9]+") || s.charAt(0) == '0'){
                System.out.println("INVALID");
                continue;
            }
            if(s.length() % 2 == 0){
                int count1 = 0;
                int count2 = 0;
                for(int i=0; i<s.length(); i++){
                    char a = s.charAt(i);
                    if(a == '0' || a == '2' || a == '4' || a == '6' || a == '8'){
                        count1++;
                    }
                    else{
                        count2++;
                    }
                }
                if(count1 > count2) {
                    System.out.println("YES");
                }
                else System.out.println("NO");
            }
            else{
                int count1 = 0;
                int count2 = 0;
                for(int i=0; i<s.length(); i++){
                    char a = s.charAt(i);
                    if(a == '1' || a == '3' || a == '5' || a == '7' || a == '9'){
                        count1++;
                    }
                    else{
                        count2++;
                    }
                }
                if(count1 > count2) {
                    System.out.println("YES");
                }
                else System.out.println("NO");
            }
        }
    }
}
