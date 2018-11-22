import java.util.*;
import java.io.*;
public class Views {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        long m = Long.parseLong(line[1]);
        
        line = br.readLine().split(" ");
        long[] array = new long[n];
        long sum = 0;
        for(int i = 0; i < n; i++) {
            array[i] = Long.parseLong(line[i]);
            sum += array[i];
        }

        Arrays.sort(array);

        long cur = 0;
        for(int i = 0; i < n; i++) {
            if(array[i] <= cur) continue;
            if(cur < m) cur++;
        }

        long res = sum - array[n-1];
        res -= n - cur;

        System.out.println(res);

    }
}