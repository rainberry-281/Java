import java.util.*;

public class J03029 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            if (!line.endsWith(".") && !line.endsWith("!") && !line.endsWith("?")) {
                line = line + ".";
            }
            lines.add(line);
        }
        sc.close();

        String text = String.join(" ", lines);
        text = text.replaceAll("\\s+", " ");
        String[] parts = text.split("(?<=[.!?])");

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;
            char last = part.charAt(part.length() - 1);
            String body = part.substring(0, part.length() - 1).toLowerCase().trim();
            if (body.isEmpty()) continue;
            String[] words = body.split("\\s+");
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toUpperCase(words[0].charAt(0)))
              .append(words[0].substring(1));
            for (int i = 1; i < words.length; i++) {
                sb.append(" ").append(words[i]);
            }
            sb.append(last);
            System.out.println(sb.toString());
        }
    }
}
