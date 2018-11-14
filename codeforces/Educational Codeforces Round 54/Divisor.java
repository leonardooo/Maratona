import java.io.*;
import java.util.*;
public class Divisor {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long count = 0;
        if( n % 2 != 0 ) {
            n -= solve(n);
            count++;
        }

        long res = count + n / 2;

        System.out.println(res);

    }

    private static long solve(long n) {
        for(long i = 2; i*i <= n; i++) {
            if( n % i == 0 ) {
                return i;
            }
        }

        return n;
    }
}