import java.util.*;
import java.io.File;
import java.io.IOException;
import java.text.*;

class XetTuyen {
    private String code, name, rank;
    private Long age;
    private Double lt, th, extra, tb;

    public XetTuyen(String code, String name, Long age, Double lt, Double th) {
        this.code = code;
        this.name = chuanhoa(name);
        this.age = age;
        this.lt = lt;
        this.th = th;
        this.extra = thuong(this.lt, this.th);
        this.tb = (double) ((lt + th) / 2 + extra);
        if (this.tb > 10)
            this.tb = 10.0;
        this.tb = (double) Math.round(this.tb);
        this.rank = setRank();
    }

    public String chuanhoa(String s) {
        String[] arr = s.split("\\s+");
        String result = "";
        for (String i : arr) {
            result += Character.toUpperCase(i.charAt(0)) + i.substring(1).toLowerCase() + " ";
        }
        return result.trim();
    }

    public Double thuong(Double lt, Double th) {
        if (lt >= 8 && th >= 8)
            return 1.0;
        else if (lt >= 7.5 && th >= 7.5)
            return 0.5;
        return 0.0;
    }

    public String setRank() {
        if (tb >= 9)
            rank = "Xuat sac";
        else if (tb >= 8)
            rank = "Gioi";
        else if (tb >= 7)
            rank = "Kha";
        else if (tb >= 5)
            rank = "Trung binh";
        else
            rank = "Truot";
        return rank;
    }

    public Double getTB() {
        if (tb > 10)
            return 10.0;
        else {
            return tb;
        }
    }

    public String toString() {
        return String.format("%s %s %d %d %s", code, name, age, Math.round(tb), rank);
    }
}

public class J07053 {
    public static void main(String[] args) throws ParseException, IOException {
        Scanner sc = new Scanner(new File("XETTUYEN.in"));
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        int t = Integer.parseInt(sc.nextLine().trim());
        ArrayList<XetTuyen> arr = new ArrayList<>();
        int count = 1;
        while (t-- > 0) {
            String code = String.format("PH%02d", count++);
            String name = sc.nextLine().trim();
            String birth = sc.nextLine().trim();
            String[] a = birth.split("/");
            Long age = 2021 - Long.parseLong(a[2]);
            Double lt = Double.parseDouble(sc.nextLine().trim());
            Double th = Double.parseDouble(sc.nextLine().trim());
            arr.add(new XetTuyen(code, name, age, lt, th));
        }
        for (XetTuyen i : arr) {
            System.out.println(i);
        }
    }
}
