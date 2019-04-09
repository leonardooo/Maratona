import java.math.BigInteger;
import java.util.*;
public class Median {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String t = sc.nextLine();

        int[] sum = new int[k+1];
        for(int i = k; i > 0; i--) {
            sum[i] += toInt(s.charAt(i-1)) + toInt(t.charAt(i-1));
            sum[i-1] += sum[i] / 26;
            sum[i] %= 26;
        }

        for(int i = 0; i <= k; i++) {
            int rem = sum[i] % 2;
            sum[i] /= 2;
            if(i < k) sum[i+1] +=  rem * 26;
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <= k; i++) {
            sb.append(toChar(sum[i]));
        }

        System.out.println(sb.toString());

    }

    static int toInt(char c) {
        return ((int)c) - ((int)'a');
    }

    static char toChar(int d) {
        return (char)(d + ((int)'a'));
    }
}