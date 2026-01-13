import java.util.*;
import java.io.*;

class SanPham implements Comparable <SanPham>{
    private String msp, name, months;
    private int price;
    public SanPham(String msp, String name, int price, String months){
        this.msp = msp;
        this.name = name;
        this.price = price;
        this.months = months;
    }
    public int compareTo(SanPham that){
        if(this.price != that.price) return Integer.compare(that.price, this.price);
        return this.msp.compareTo(that.msp);
    }
    public String toString(){
        return String.format("%s %s %d %s", msp, name, price, months);
    }
}

public class J07048 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(new File("SANPHAM.in"));
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<SanPham> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            String msp = sc.nextLine();
            String name = sc.nextLine();
            int price = Integer.parseInt(sc.nextLine());
            String months = sc.nextLine();
            arr.add(new SanPham(msp, name, price, months));
        }
        Collections.sort(arr);
        for(SanPham sp : arr){
            System.out.println(sp);
        }
    }
}
