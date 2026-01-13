import java.util.*;

class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x) + (this.y - that.y) * (this.y - that.y));
    }

    public String toString() {
        return String.format("%.4f", distance(new Point(0, 0)));
    }
}

public class J04001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            double d = sc.nextDouble();
            Point x = new Point(a, b);
            Point y = new Point(c, d);
            System.out.printf("%.4f\n", x.distance(y));
        }
    }
}