import java.util.*;
import java.io.*;

public class J07005 {
    public static void main (String args[]) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DATA.in"));
        ArrayList<Integer> arr = (ArrayList<Integer>) ois.readObject();
        ois.close();
        Map<Integer, Integer> mp = new TreeMap<>();
        for(int x : arr){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> en : mp.entrySet()){
            System.out.println(en.getKey() + " " + en.getValue());
        }
    }
}
