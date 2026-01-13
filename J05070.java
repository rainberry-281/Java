import java.util.*;

class VanDau implements Comparable<VanDau>{
    private String code, name;
    public Long total;
    public VanDau(String code, String name, Long total){
        this.code = code;
        this.name = name;
        this.total = total;
    }
    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
    public Long getTotal(){
        return total;
    }
    public int compareTo(VanDau that) {
        if (!this.total.equals(that.total)) {
            return Long.compare(that.total, this.total);
        }
        return this.name.compareTo(that.name);
    }
    public String toString(){
        return String.format("%s %s %d", code, name, total);
    }
}

public class J05070 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        Map<String, String[]> mp = new HashMap<>();
        while(t --> 0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            String price = sc.nextLine();
            mp.put(code, new String[]{name, price});
        }
        ArrayList<VanDau> arr = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        while(n --> 0){
            String[] line = sc.nextLine().split("\\s+");
            String code = line[0].substring(1, 3);
            Long num = Long.parseLong(line[1]);
            String[] info = mp.get(code);
            String name = info[0];
            Long price = Long.parseLong(info[1]);
            Long total = price * num;
            arr.add(new VanDau(line[0], name, total));
        }
        Collections.sort(arr);
        for(VanDau i : arr){
            System.out.println(i);
        }
    }
}
