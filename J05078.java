import java.util.*;

class NhanVien {
    private String code, name, room;
    private int lcb, days, total;
    public NhanVien(String code, String name, int lcb, int days, String room){
        this.code = code;
        this.name = name;
        this.lcb = lcb * 1000;
        this.days = days;
        this.total = this.lcb * this.days * heso(this.code);
        this.room = room;
    }

    public int heso(String s){
        String nhom = s.substring(0, 1);
        int years = Integer.parseInt(s.substring(1, 3));
        if(years >= 16){
            if(nhom.equals("A")) return 20;
            else if(nhom.equals("B")) return 16;
            else if(nhom.equals("C")) return 14;
            else return 13;
        }
        else if(years >= 9){
            if(nhom.equals("A")) return 14;
            else if(nhom.equals("B")) return 13;
            else if(nhom.equals("C")) return 12;
            else return 11;
        }
        else if(years >= 4){
            if(nhom.equals("A")) return 12;
            else if(nhom.equals("B")) return 11;
            else if(nhom.equals("C")) return 10;
            else return 9;
        }
        else{
            if(nhom.equals("A")) return 10;
            else if(nhom.equals("B")) return 10;
            else if(nhom.equals("C")) return 9;
            else return 8;
        }
    }

    public String getRoom(){
        return room;
    }

    public String toString(){
        return String.format("%s %s %d", code, name, total);
    }
}

public class J05078 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        Map<String, String> mp = new HashMap<>();
        while(t --> 0){
            String[] line = sc.nextLine().split("\\s+", 2);
            String code = line[0];
            String room = line[1];
            mp.put(code, room);
        }
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<NhanVien> arr = new ArrayList<>();
        while(n --> 0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            int lcb = Integer.parseInt(sc.nextLine());
            int days = Integer.parseInt(sc.nextLine());
            String roomcode = code.substring(3);
            String room = mp.get(roomcode);
            arr.add(new NhanVien(code, name, lcb, days, room));
        }
        String rc = sc.nextLine();
        String room = mp.get(rc);
        System.out.printf("Bang luong phong %s:\n", room);
        for(NhanVien i : arr){
            if(i.getRoom().equals(room)){
                System.out.println(i);
            }
        }
    }
}

