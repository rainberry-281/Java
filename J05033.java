import java.util.*;

class Time implements Comparable<Time> {
    private int gio, phut, giay;

    public Time(int gio, int phut, int giay) {
        this.gio = gio;
        this.phut = phut;
        this.giay = giay;
    }

    public int compareTo(Time that) {
        if (this.gio != that.gio)
            return this.gio - that.gio;
        if (this.phut != that.phut)
            return this.phut - that.phut;
        return this.giay - that.giay;
    }

    public String toString() {
        return String.format("%d %d %d", gio, phut, giay);
    }
}

public class J05033 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int gio = sc.nextInt();
            int phut = sc.nextInt();
            int giay = sc.nextInt();
            arr.add(new Time(gio, phut, giay));
        }
        Collections.sort(arr);
        for (Time i : arr) {
            System.out.println(i);
        }
    }
}