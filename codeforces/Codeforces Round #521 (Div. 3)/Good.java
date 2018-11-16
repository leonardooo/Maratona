import java.io.*;
import java.util.*;
public class Good {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[1000001];

        int n = Integer.parseInt(br.readLine());
        long[] array = new long[n];
        String[] linha = br.readLine().split(" ");
        long total = 0;
        for(int i = 0; i < n; i++) {
            array[i] = Long.parseLong(linha[i]);
            total+= array[i];

            dp[ (int)array[i] ]++;
        }

        List<Integer> res = new LinkedList<Integer>();
        int count = 0;
        for(int i = 0; i < n; i++) {
            long sum = total - array[i];
            if( sum % 2 == 0 && (sum/2) < 1000001l) {
                if( array[i] == (sum/2) && dp[(int)sum/2] > 1 ) {
                    count++;
                    res.add(i+1);
                } else if( array[i] != (sum/2) && dp[(int)sum/2] > 0 ) {
                    count++;
                    res.add(i+1);
                }
            }
        }

        System.out.println(count);
        StringBuffer sb = new StringBuffer();
        if(count > 0) {
            Iterator<Integer> it = res.iterator();
            while(it.hasNext()) {
                sb.append(it.next());
                if(it.hasNext()) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

}