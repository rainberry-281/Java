import java.util.*;

class Point{
    private double x, y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point that){
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}

public class J04009 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t --> 0) {
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
            double d3 = p3.distance(p1);
            if(d1 + d2 <= d3 || d2 + d3 <= d1 || d1 + d3 <= d2) {
                System.out.println("INVALID");
            }
            else{
                double p = (d1 + d2 + d3) / 2.0;
                double s = Math.sqrt(p * (p-d1) * (p-d2) * (p-d3));
                System.out.printf("%.2f\n", s);
            }
        }
        sc.close();
    }
}
