import java.util.*;

class ThucTap implements Comparable<ThucTap> {
    private String stt, code, name, lop, email, dn;

    public ThucTap(String stt, String code, String name, String lop, String email, String dn) {
        this.stt = stt;
        this.code = code;
        this.name = name;
        this.lop = lop;
        this.email = email;
        this.dn = dn;
    }

    public String getDN() {
        return dn;
    }

    public int compareTo(ThucTap that) {
        return this.name.compareTo(that.name);
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s", stt, code, name, lop, email, dn);
    }
}

public class J05034 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine().trim());
        int count = 1;
        ArrayList<ThucTap> arr = new ArrayList<>();
        while (t-- > 0) {
            String stt = String.format("%d", count++);
            String code = sc.nextLine().trim();
            String name = sc.nextLine().trim();
            String lop = sc.nextLine().trim();
            String email = sc.nextLine().trim();
            String dn = sc.nextLine().trim();
            arr.add(new ThucTap(stt, code, name, lop, email, dn));
        }
        Collections.sort(arr);
        int n = Integer.parseInt(sc.nextLine().trim());
        while (n-- > 0) {
            String dn = sc.nextLine().trim();
            for (ThucTap i : arr) {
                if (i.getDN().equals(dn))
                    System.out.println(i);
            }
        }
    }
}
