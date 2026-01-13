import java.util.*;

class LopHocPhan implements Comparable<LopHocPhan>{
    private String code, name, group, teacher;
    public LopHocPhan(String code, String name, String group, String teacher){
        this.code = code;
        this.name = name;
        this.group = group;
        this.teacher = teacher;
    }
    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
    public int compareTo(LopHocPhan that){
        return Integer.parseInt(this.group) - Integer.parseInt(that.group);
    }
    public String toString(){
        return String.format("%s %s", group, teacher);
    }
}

public class J05079 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        ArrayList<LopHocPhan> arr = new ArrayList<>();
        Map<String, String> mp = new HashMap<>();
        while(t --> 0){
            String code = sc.nextLine();
            String name = sc.nextLine();
            String group = sc.nextLine();
            String teacher = sc.nextLine();
            arr.add(new LopHocPhan(code, name, group, teacher));
            mp.put(code, name);
        }
        Collections.sort(arr);
        int n = Integer.parseInt(sc.nextLine());
        while(n --> 0){
            String code = sc.nextLine();
            String name = mp.get(code);
            System.out.printf("Danh sach nhom lop mon %s:\n", name);
            for (LopHocPhan i : arr){
                if(i.getCode().equals(code)){
                    System.out.println(i);
                }
            }
        }
    }
}
