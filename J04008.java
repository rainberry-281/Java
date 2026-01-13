import java.util.*;

class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x) + (this.y - that.y) * (this.y - that.y));
    }
}

public class J04008 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            double d = sc.nextDouble();
            double e = sc.nextDouble();
            double f = sc.nextDouble();
            Point p1 = new Point(a, b);
            Point p2 = new Point(c, d);
            Point p3 = new Point(e, f);
            double d1 = p1.distance(p2);
            double d2 = p2.distance(p3);
            double d3 = p1.distance(p3);
            if (d1 + d2 <= d3 || d1 + d3 <= d2 || d2 + d3 <= d1) {
                System.out.println("INVALID");
            } else {
                System.out.printf("%.3f\n", d1 + d2 + d3);
            }
        }
    }
}