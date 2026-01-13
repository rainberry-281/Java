import java.util.*;
import java.io.*;

public class J07004 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("DATA.in"));
        Map<Integer, Integer> mp = new TreeMap<>();
        while(sc.hasNextInt()){
            int x = sc.nextInt();
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        sc.close();
    }
}
