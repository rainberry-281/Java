import java.util.*;
import java.io.IOException;
import java.text.*;

class Ages implements Comparable<Ages> {
    private String name;
    private Long age;

    public Ages(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(Ages that) {
        return Long.compare(that.age, this.age);
    }

    public String toString() {
        return String.format("%s", name);
    }
}

public class J05031 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        int t = Integer.parseInt(sc.nextLine());
        ArrayList<Ages> arr = new ArrayList<>();
        while (t-- > 0) {
            String[] lines = sc.nextLine().split(" ");
            String name = lines[0];
            Date birth = sdf.parse(lines[1]);
            Long days = birth.getTime();
            arr.add(new Ages(name, days));
        }
        Collections.sort(arr);
        Ages a = arr.get(0);
        Ages b = arr.get(arr.size() - 1);
        System.out.println(a);
        System.out.println(b);
    }
}
