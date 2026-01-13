import java.io.*;
import java.util.*;

public class J07021 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("DATA.in"));
        while (sc.hasNextLine()) {
            String s = sc.nextLine().strip();
            if (s.equals("END")) {
                sc.close();
                return;
            }
            String[] arr = s.split("\\s+");
            String result = "";
            for (String i : arr) {
                result += Character.toUpperCase(i.charAt(0)) + i.substring(1).toLowerCase() + " ";
            }
            System.out.println(result.strip());
        }
    }
}
