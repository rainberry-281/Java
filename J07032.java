import java.io.*;
import java.util.*;

public class J07032 {
    public static boolean check(int a){
        String s = Integer.toString(a);
        int n = s.length();

        if(n <= 1 || n % 2 == 0) return false;

        int l = 0, r = n - 1;
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)) return false;
            if((s.charAt(l) - '0') % 2 == 0) return false;
            l++;
            r--;
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        ObjectInputStream obs1 = new ObjectInputStream(new FileInputStream("DATA1.in"));
        ObjectInputStream obs2 = new ObjectInputStream(new FileInputStream("DATA2.in"));
        ArrayList<Integer> arr1 = (ArrayList<Integer>) obs1.readObject();
        ArrayList<Integer> arr2 = (ArrayList<Integer>) obs2.readObject();
        Map<Integer, Integer> mp = new HashMap<>();
        for(Integer i : arr1){
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }
        for(Integer i : arr2){
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }
        HashSet<Integer> hs1 = new HashSet<>(arr1);
        HashSet<Integer> hs2 = new HashSet<>(arr2);

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i : hs1){
            if(hs2.contains(i) && check(i)){
                tm.put(i, mp.get(i));
            }
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> me : tm.entrySet()){
            System.out.println(me.getKey() + " " + me.getValue());
            count++;
            if(count == 10) break;
        }
    }
}
