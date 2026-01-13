import java.util.*;
import java.io.*;

public class J07007 {
    public static void main (String args[]) throws IOException {
        Scanner sc = new Scanner(new File("VANBAN.in"));
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
