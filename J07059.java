import java.util.*;
import java.io.*;

class CaThi implements Comparable<CaThi> {
    private String maca, date, time;
    private int room, gio, phut;

    public CaThi(String maca, String date, String time, int room) {
        this.maca = maca;
        this.date = date;
        this.time = time;
        this.room = room;
        tachgio();
    }

    public void tachgio() {
        String[] arr = time.split(":");
        this.gio = Integer.parseInt(arr[0]);
        this.phut = Integer.parseInt(arr[1]);
    }

    public int compareTo(CaThi that) {
        if (!this.date.equals(that.date))
            return this.date.compareTo(that.date);
        if (this.gio != that.gio)
            return Integer.compare(this.gio, that.gio);
        return Integer.compare(this.phut, that.phut);
    }

    public String toString() {
        return String.format("%s %s %s %d", maca, date, time, room);
    }
}

public class J07059 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new File("CATHI.in"));
        ArrayList<CaThi> arr = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String maca = String.format("C%03d", i + 1);
            String date = sc.nextLine();
            String time = sc.nextLine();
            int room = Integer.parseInt(sc.nextLine());
            arr.add(new CaThi(maca, date, time, room));
        }
        Collections.sort(arr);
        for (CaThi ct : arr) {
            System.out.println(ct);
        }
    }
}
