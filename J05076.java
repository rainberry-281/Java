import java.util.*;

class HangHoa{
    private String code, name, rank;
    private int gianhap, giaxuat;
    public HangHoa(String code, String name, String rank){
        this.code = code;
        this.name = name;
        this.rank = rank;
    }

    public String getCode(){
        return code;
    }

    public void getPrice(int nhap, int xuat, int price){
        this.gianhap = nhap * price;
        this.giaxuat = (int) (xuat * price * getRank());
    }
    public Double getRank(){
        if(this.rank.equals("A")) return 1.08;
        else if(this.rank.equals("B")) return 1.05;
        else return 1.02;
    }
    public String toString(){
        return String.format("%s %s %d %d", code, name, gianhap, giaxuat);
    }
}

public class J05076{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        ArrayList<HangHoa> arr = new ArrayList<>();
        while(t-->0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            String rank = sc.nextLine();
            arr.add(new HangHoa(code, name, rank));
        }
        int n = Integer.parseInt(sc.nextLine());
        while(n-->0){
            String[] line = sc.nextLine().split("\\s+");
            String code = line[0];
            int slnhap = Integer.parseInt(line[1]);
            int giamua = Integer.parseInt(line[2]);
            int slban = Integer.parseInt(line[3]);
            for(HangHoa i : arr){
                i.getPrice(slnhap, slban, giamua);
                if(i.getCode().equals(code)){
                    System.out.println(i);
                }
            }
        }
    }
}