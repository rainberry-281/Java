import java.util.*;
import java.text.*;

class VanDongVien implements Comparable<VanDongVien> {
    private String code, name;
    private Date birth, start, finish;
    private long thucte, ut, result;
    public int rank;

    public VanDongVien(String code, String name, String birth, String start, String finish) throws ParseException {
        this.code = code;
        this.name = name;
        SimpleDateFormat years = new SimpleDateFormat("dd/MM/yyyy");
        this.birth = years.parse(birth);
        SimpleDateFormat hours = new SimpleDateFormat("HH:mm:ss");
        this.start = hours.parse(start);
        this.finish = hours.parse(finish);
        this.thucte = this.finish.getTime() - this.start.getTime();
        this.ut = priority();
        this.result = this.thucte - this.ut;
    }

    public Long priority() {
        Calendar c = Calendar.getInstance();
        c.setTime(birth);
        int years = c.get(Calendar.YEAR);
        int ages = 2021 - years;
        if (ages >= 32)
            return 3000L;
        else if (ages >= 25)
            return 2000L;
        else if (ages >= 18)
            return 1000L;
        return 0L;
    }

    public String msToTime(Long s) {
        Long hours = s / (3600L * 1000);
        Long mins = (s / (60 * 1000L)) % 60;
        Long sec = (s / 1000L) % 60;
        return String.format("%02d:%02d:%02d", hours, mins, sec);
    }

    public int compareTo(VanDongVien that) {
        return Long.compare(this.result, that.result);
    }

    public Long getResult() {
        return result;
    }

    public void setRank(int r) {
        this.rank = r;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %d", code, name, msToTime(thucte), msToTime(ut), msToTime(result), rank);
    }
}

public class J05055 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        ArrayList<VanDongVien> arr = new ArrayList<>();
        int count = 1;
        while (t-- > 0) {
            String code = String.format("VDV%02d", count++);
            String name = sc.nextLine();
            String birth = sc.nextLine();
            String start = sc.nextLine();
            String finish = sc.nextLine();
            arr.add(new VanDongVien(code, name, birth, start, finish));
        }
        ArrayList<VanDongVien> rankVDV = new ArrayList<>(arr);
        Collections.sort(rankVDV);
        int rank = 1;
        rankVDV.get(0).setRank(rank);
        for (int i = 1; i < rankVDV.size(); i++) {
            if (!rankVDV.get(i).getResult().equals(rankVDV.get(i - 1).getResult())) {
                rank++;
            }
            rankVDV.get(i).setRank(rank);
        }
        for (VanDongVien i : arr) {
            System.out.println(i);
        }
    }
}
