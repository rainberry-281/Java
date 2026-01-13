import java.util.*;
import java.io.*;
import java.text.*;

class KhachHang implements Comparable<KhachHang> {
    private String code, name;
    private Long room;
    private Long days;
    private Long dichvu;

    public KhachHang(String code, String name, Long room, Long days, Long dichvu) {
        this.code = code;
        this.name = chuanhoaten(name);
        this.room = room;
        this.days = days;
        this.dichvu = dichvu;
    }

    public Long cash() {
        if (room >= 400)
            return 80L;
        else if (room >= 300)
            return 50L;
        else if (room >= 200)
            return 34L;
        else if (room >= 100)
            return 25L;
        return 0L;
    }

    public Long total() {
        return cash() * days + dichvu;
    }

    public String chuanhoaten(String s) {
        String[] arr = s.split("\\s+");
        String result = "";
        for (String i : arr) {
            result += Character.toUpperCase(i.charAt(0)) + i.substring(1).toLowerCase() + " ";
        }
        return result.trim();
    }

    public int compareTo(KhachHang that) {
        return -Long.compare(this.total(), that.total());
    }

    public String toString() {
        return String.format("%s %s %d %d %d", code, name, room, days, total());
    }

}

public class J07051 {
    public static String chuanhoangay(String s) {
        String[] arr = s.split("/");
        String result = "";
        if (arr[0].length() == 1)
            arr[0] = "0" + arr[0];
        if (arr[1].length() == 1)
            arr[1] = "0" + arr[1];
        result = arr[0] + "/" + arr[1] + "/" + arr[2];
        return result;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(new File("KHACHHANG.in"));
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        int t = Integer.parseInt(sc.nextLine().trim());
        ArrayList<KhachHang> arr = new ArrayList<>();
        int count = 1;
        while (t-- > 0) {
            String code = String.format("KH%02d", count++);
            String name = sc.nextLine().trim();
            Long room = Long.parseLong(sc.nextLine().trim());
            String in = sc.nextLine();
            String out = sc.nextLine();
            Date datein = spd.parse(chuanhoangay(in));
            Date dateout = spd.parse(chuanhoangay(out));
            Long days = (dateout.getTime() - datein.getTime()) / (1000 * 60 * 60 * 24) + 1;
            Long dvps = Long.parseLong(sc.nextLine().trim());
            arr.add(new KhachHang(code, name, room, days, dvps));
        }
        Collections.sort(arr);
        for (KhachHang kh : arr) {
            System.out.println(kh);
        }
    }
}
