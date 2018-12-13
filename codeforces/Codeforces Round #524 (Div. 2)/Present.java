import java.util.*;
public class Present {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            long a = sc.nextLong();
            long b = sc.nextLong();

            long res = f(b) - f(a-1);
            System.out.println(res);
        }
    }

    static long f(long n) {
        if(n % 2 == 0) {
            return n/2;
        } else {
            return f(n-1) - n;
        }
    }
}