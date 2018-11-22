import java.util.*;
import java.io.*;
public class Coins {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        long n = Long.parseLong(line[0]);
        long s = Long.parseLong(line[1]);
        long res = 0;

        while(s > 0) {
            //System.out.println("n=" + n + " s=" + s);
            while(n <= s) {
                s -= n;
                res++;
            }
            if(n > 1) n--;
            if(n > s) n = s;
        }

        System.out.println(res);
    }
}