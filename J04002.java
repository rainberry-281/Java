import java.util.*;

class Rectange {
    private int dai, rong;
    private String color;

    public Rectange(int dai, int rong, String color) {
        this.dai = dai;
        this.rong = rong;
        this.color = color.trim().toLowerCase();
        this.color = Character.toUpperCase(this.color.charAt(0)) + this.color.substring(1);
    }

    public int getArea() {
        return dai * rong;
    }

    public int getPerimeter() {
        return (dai + rong) * 2;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return String.format("%d %d %s", getPerimeter(), getArea(), getColor());
    }
}

public class J04002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dai = sc.nextInt();
        int rong = sc.nextInt();
        String color = sc.nextLine();
        Rectange rec = new Rectange(dai, rong, color);
        System.err.println(rec);
    }
}