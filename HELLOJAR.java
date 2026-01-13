import java.util.*;
import java.io.*;

public class HELLOJAR {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        Set<String> se = new TreeSet<>();
        while(sc.hasNext()){
            String s = sc.next().toLowerCase();
            se.add(s);
        }
        for(String s : se){
            System.out.println(s);
        }
    }
}
