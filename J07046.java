import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class KhachHang implements Comparable<KhachHang> {
    private String code, name, room;
    private Long time;

    public KhachHang(String code, String name, String room, Long time) {
        this.code = code;
        this.name = name;
        this.room = room;
        this.time = time;
    }

    public int compareTo(KhachHang that) {
        return -this.time.compareTo(that.time);
    }

    public String toString() {
        return String.format("%s %s %s %d", code, name, room, time);
    }
}

public class J07046 {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(new File("KHACH.in"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int t = Integer.parseInt(sc.nextLine());
        ArrayList<KhachHang> arr = new ArrayList<>();
        int count = 1;
        while (t-- > 0) {
            String code = String.format("KH%02d", count++);
            String name = sc.nextLine();
            String room = sc.nextLine();
            Date datein = sdf.parse(sc.nextLine());
            Date dateout = sdf.parse(sc.nextLine());
            Long days = (dateout.getTime() - datein.getTime()) / (1000 * 60 * 60 * 24);
            arr.add(new KhachHang(code, name, room, days));
        }
        Collections.sort(arr);
        for (KhachHang i : arr) {
            System.out.println(i);
        }
    }
}
