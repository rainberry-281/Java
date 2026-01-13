import java.util.*;

public class J03010 {
    public static String chuanhoa(String s) {
        s = s.trim().toLowerCase();
        String[] arr = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[arr.length - 1]);
        for (int i=0; i<arr.length - 1; i++){
            sb.append(arr[i].charAt(0));
        }
        return String.format("%s",sb);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        HashMap<String, Integer> hmp = new HashMap<>();
        while(t --> 0){
            String s = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            if(hmp.containsKey(chuanhoa(s))){
                int stt = hmp.get(chuanhoa(s));
                System.out.println(chuanhoa(s) + stt + "@ptit.edu.vn");
                hmp.put(chuanhoa(s), ++stt);
            }
            else{
                System.out.println(chuanhoa(s) + "@ptit.edu.vn");
                hmp.put(chuanhoa(s), 2);
            }
        }
    }
}