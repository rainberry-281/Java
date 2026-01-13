import java.util.*;
import java.math.*;

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

public class J04010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t --> 0){
            Double a = sc.nextDouble();
            Double b = sc.nextDouble();
            Double c = sc.nextDouble();
            Double d = sc.nextDouble();
            Double e = sc.nextDouble();
            Double f = sc.nextDouble();
            Point p1 = new Point(a, b);
            Point p2 = new Point(c, d);
            Point p3 = new Point(e, f);
            Double d1 = p1.distance(p2);
            Double d2 = p2.distance(p3);
            Double d3 = p3.distance(p1);
            if(d1 + d2 <= d3 || d2 + d3 <= d1 || d1 + d3 <= d2){
                System.out.println("INVALID");
            }
            else{
                Double p = (d1 + d2 + d3) / 2.0;
                Double area = Math.sqrt(p * (p - d1) * (p - d2) * (p - d3));
                Double r = (d1 * d2 * d3) / (4 * area);
                Double result = Math.PI * r * r;
                System.out.printf("%.3f\n", result);
            }
        }
    }
}
