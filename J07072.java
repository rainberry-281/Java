import java.util.*;
import java.io.*;

class DanhSach implements Comparable <DanhSach>{
    private String name, first, second, last;
    public DanhSach(String name){
        this.name = chuanhoa(name);
        tachten();
    }
    public String chuanhoa(String s){
        s = s.toLowerCase().trim();
        String[] arr = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(String str : arr){
            sb.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).append(" ");
        }
        String res = sb.toString().trim();
        return res;
    }
    public void tachten(){
        String[] arr = name.split("\\s+");
        this.first = arr[0];
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<arr.length - 1; i++){
            sb.append(arr[i]).append(" ");
        }
        this.second = sb.toString().trim();
        this.last = arr[arr.length - 1];
    }
    public int compareTo(DanhSach that) {
        if (!this.last.equals(that.last)) return this.last.compareTo(that.last);
        if (!this.first.equals(that.first)) return this.first.compareTo(that.first);
        return this.second.compareTo(that.second);
    }
    public String toString(){
        return String.format("%s", name);
    }
}

public class J07072 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(new File("DANHSACH.in"));
        ArrayList<DanhSach> arr = new ArrayList<>();
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(!s.trim().isEmpty()){
                arr.add(new DanhSach(s));
            }
        }
        Collections.sort(arr);
        for(DanhSach ds : arr){
            System.out.println(ds);
        }
    }
}
