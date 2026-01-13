import java.io.*;
import java.util.*;

public class J07030 {
    public static int total = 1000000;
    public static boolean[] arr = new boolean[total + 1];

    public static void Sang(){
        Arrays.fill(arr, true);
        arr[0] = arr[1] = false;
        for(int i=2; i*i<=total; i++){
            if(arr[i]){
                for(int j = i*i; j<=total; j+=i){
                    arr[j] = false;
                }
            }
        }
    }
    public static boolean snt(int n){
        if(n < 2) return false;
        for(int i=2; i*i<=n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        Sang();
        ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("DATA1.in"));
        ObjectInputStream o2 = new ObjectInputStream(new FileInputStream("DATA2.in"));
        ArrayList<Integer> arr1 = (ArrayList<Integer>) o1.readObject();
        ArrayList<Integer> arr2 = (ArrayList<Integer>) o2.readObject();
        HashSet<Integer> hs = new HashSet<>(arr2);
        TreeSet<Integer> ts = new TreeSet<>();
        for(int n : arr1){
            int m = total - n;
            if(n < m && arr[n] && arr[m] && hs.contains(m)){
                ts.add(n);
            }
        }
        for(int n : ts){
            System.out.println(n + " " + (total - n));
        }
    }
}
