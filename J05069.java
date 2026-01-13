import java.util.*;

class CLB {
    private String name;
    private int price;

    public CLB(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

public class J05069 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, CLB> mp = new HashMap<>();
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            String code = sc.nextLine();
            String name = sc.nextLine();
            int price = Integer.parseInt(sc.nextLine());
            mp.put(code, new CLB(name, price));
        }
        int n = Integer.parseInt(sc.nextLine());
        while (n-- > 0) {
            String[] line = sc.nextLine().trim().split("\\s+");
            String code = line[0];
            int num = Integer.parseInt(line[1]);
            String clb = code.substring(1, 3);
            CLB club = mp.get(clb);
            int total = club.getPrice() * num;
            System.out.println(code + " " + club.getName() + " " + total);
        }
    }
}
