import java.util.*;

class SinhVien{
    private String code, name, lop, status;
    private int cc;
    public SinhVien(String code, String name, String lop){
        this.code = code;
        this.name = name;
        this.lop = lop;
    }

    public void setStatus(String dd){
        int max = 10;
        for (char i : dd.toCharArray()){
            if(i == 'v') max -= 2;
            if(i == 'm') max -= 1;
        }
        if(max < 0) max = 0;
        this.cc = max;
        if(this.cc == 0) this.status = "KDDK";
        else this.status = "";
    }

    public String getLop(){
        return lop;
    }

    public String toString(){
        if(this.status.isEmpty())
            return String.format("%s %s %s %d", code, name, lop, cc);
        else return String.format("%s %s %s %d %s", code, name, lop, cc, status);
    }
}

public class J05075 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        int n = t;
        ArrayList<SinhVien> arr = new ArrayList<>();
        Map<String, SinhVien> mp = new HashMap<>();
        while(t --> 0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            String lop = sc.nextLine();
            SinhVien a = new SinhVien(code, name, lop);
            arr.add(a);
            mp.put(code, a);
        }
        while(n --> 0){
            String[] line = sc.nextLine().split("\\s+");
            String code = line[0];
            String dd = line[1];
            SinhVien a = mp.get(code);
            a.setStatus(dd);
        }
        String lop = sc.nextLine();
        for(SinhVien sv : arr){
            if(sv.getLop().equals(lop)){
                System.out.println(sv);
            }
        }
    }
}
