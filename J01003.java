import java.util.*;

public class J01003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            float a = sc.nextFloat();
            float b = sc.nextFloat();
            if(a == 0 && b == 0){
                System.out.println("VSN");
            }
            else if(a == 0){
                System.out.println("VN");
            }
            else{
                float kq = (-b) / a;
                System.out.println(String.format("%.2f", kq));
            }     
        sc.close();
    }
}
