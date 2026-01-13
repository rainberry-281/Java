import java.util.*;

class DiemDanh{
    private String msv, name, lop, status;
    private int cc;
    public DiemDanh(String msv, String name, String lop){
        this.msv = msv;
        this.name = name;
        this.lop = lop;
    }
    public void setCC(String dd){
        int max = 10;
        for(int i=0; i<dd.length(); i++){
            if(dd.charAt(i) == 'v') max -= 2;
            else if(dd.charAt(i) == 'm') max -= 1;
            else max = max;
        }
        if(max < 0) max = 0;
        this.cc = max;
        if(max == 0){
            this.status = "KDDK";
        }
        else this.status = "";
    }
    public String toString(){
        if(this.status.isEmpty()){
            return String.format("%s %s %s %d", msv, name, lop, cc);
        }
        else return String.format("%s %s %s %d %s", msv, name, lop, cc, status);
    }
}

public class J05074{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        int n = t;
        ArrayList<DiemDanh> arr = new ArrayList<>();
        Map<String, DiemDanh> mp = new HashMap<>();
        while(t --> 0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            String lop = sc.nextLine();
            DiemDanh sv = new DiemDanh(code, name, lop);
            arr.add(sv);
            mp.put(code, sv);
        }
        while(n --> 0){
            String[] line = sc.nextLine().split("\\s+");
            String code = line[0];
            String dd = line[1];
            DiemDanh sv = mp.get(code);
            sv.setCC(dd);
        }
        for(DiemDanh i : arr){
            System.out.println(i);
        }
    }
}