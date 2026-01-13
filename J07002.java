import java.util.*;
import java.io.*;

public class J07002 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(new File("DATA.in"));
        long sum = 0;
        while(sc.hasNext()){
            String s = sc.next();
            try{
                int n = Integer.parseInt(s);
                sum += n;
            }
            catch(NumberFormatException e){

            }
        }
        System.out.println(sum);
        sc.close();
    }
}
