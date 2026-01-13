import java.util.*;
import java.io.*;

class MonHoc implements Comparable <MonHoc> {
    private String mamon, name;
    private int stc;
    public MonHoc (String mamon, String name, int stc) {
        this.mamon = mamon;
        this.name = name;
        this.stc = stc;
    }
    public int compareTo(MonHoc that){
        return this.name.compareTo(that.name);
    }
    public String toString(){
        return String.format("%s %s %d", mamon, name, stc);
    }
}

public class J07034 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(new File("MONHOC.in"));
        ArrayList<MonHoc> arr = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        for(int i=0; i<n; i++){
            String mamon = sc.nextLine();
            String name = sc.nextLine();
            int stc = Integer.parseInt(sc.nextLine());
            arr.add(new MonHoc(mamon, name, stc));
        }
        Collections.sort(arr);
        for(MonHoc mh : arr){
            System.out.println(mh);
        }
    }
}
