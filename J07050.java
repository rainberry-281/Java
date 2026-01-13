import java.util.*;
import java.io.*;

class MatHang implements Comparable <MatHang>{
    private String mh, name, nhom;
    private double giamua, giaban;
    public MatHang(String mh, String name, String nhom, double giamua, double giaban){
        this.mh = mh;
        this.name = name;
        this.nhom = nhom;
        this.giamua = giamua;
        this.giaban = giaban;
    }
    public double loinhuan(){
        return this.giaban - this.giamua;
    }
    public int compareTo(MatHang that){
        return Double.compare(that.loinhuan(), this.loinhuan());
    }
    public String toString(){
        return String.format("%s %s %s %.2f", mh, name, nhom, loinhuan());
    }
}

public class J07050 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new File("MATHANG.in"));
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<MatHang> arr = new ArrayList<>();
        int count = 1;
        for(int i=0; i<n; i++){
            String mh = String.format("MH%02d", count++);
            String name = sc.nextLine().trim();
            String nhom = sc.nextLine().trim();
            double giamua = Double.parseDouble(sc.nextLine());
            double giaban = Double.parseDouble(sc.nextLine());
            arr.add(new MatHang(mh, name, nhom, giamua, giaban));
        }
        Collections.sort(arr);
        for(MatHang mh : arr){
            System.out.println(mh);
        }
    }
}
