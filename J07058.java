import java.util.*;
import java.io.*;

class MonHoc implements Comparable <MonHoc> {
    private String mamon, tenmon, hinhthuc;
    public MonHoc(String mamon, String tenmon, String hinhthuc){
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.hinhthuc = hinhthuc;
    }
    public int compareTo(MonHoc that){
        return this.mamon.compareTo(that.mamon);
    }
    public String toString(){
        return String.format("%s %s %s", mamon, tenmon, hinhthuc);
    }
}

public class J07058{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("MONHOC.in"));
        int n = Integer.parseInt(sc.nextLine().trim());
        ArrayList<MonHoc> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            String mamon = sc.nextLine().trim();
            String tenmon = sc.nextLine().trim();
            String hinhthuc = sc.nextLine().trim();
            arr.add(new MonHoc(mamon, tenmon, hinhthuc));
        }
        Collections.sort(arr);
        for(MonHoc mh : arr){
            System.out.println(mh);
        }
        sc.close();
    }
}