import java.io.*;
import java.util.*;
public class Pictures {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int x = Integer.parseInt(line[2]);

        line = br.readLine().split(" ");
        long[] a = new long[n];
        for(int i = 0; i < n; i++) {
            a[i] = Long.parseLong(line[i]);
        }

        long[][] dp = new long[n+1][x+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= x; j++) {
                dp[i][j] = -Long.MAX_VALUE;
            }
        }

        dp[0][x] = 0;
        for(int i = 1; i <=n; i++) {
            for(int j = 0; j < x; j++) {
                for(int p = 1; p <= k; p++) {
                    if(i - p < 0) break;
                    if( dp[i-p][j+1] == -Long.MAX_VALUE ) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-p][j+1] + a[i-1]);
                }
            }
        }

        //print(dp);

        long res = -Long.MAX_VALUE;
        for (int i = n - k + 1; i <= n; i++) {
            res = Math.max(res, maxElement(dp[i]));
        }

        if (res == -Long.MAX_VALUE)
            res = -1;

        System.out.println(res);
    }

    private static long maxElement(long[] array) {
        long res = array[0];
        for(int i = 1; i < array.length; i++) {
            res = Math.max(res, array[i]);
        }

        return res;
    }

    private static void print(long[][] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}