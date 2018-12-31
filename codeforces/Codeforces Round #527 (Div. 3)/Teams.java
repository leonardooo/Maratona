import java.util.*;
public class Teams {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] stu = new int[n];
        for(int i = 0; i < n; i++) {
            stu[i] = sc.nextInt();
        }

        Arrays.sort(stu);

        int res = 0;
        for(int i = 0; i < n-1; i+=2) {
            res += stu[i+1] - stu[i];
        }

        System.out.println(res);
    }
}