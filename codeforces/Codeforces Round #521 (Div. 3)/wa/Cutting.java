import java.io.*;
import java.util.*;
public class Cutting {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX = 2*((int)Math.pow(10,5)) + 1;

        String[] linha = br.readLine().split(" ");
        int n = Integer.parseInt(linha[0]);
        int k = Integer.parseInt(linha[1]);
        linha = br.readLine().split(" ");

        int[] array = new int[n];
        int[] dp = new int[MAX];
        
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(linha[i]);
            dp[array[i]]++;
        }

        int[] count = new int[dp.length];
        for(int i = 0 ; i < dp.length; i++) {
            count[ dp[i] ]++;
        }

        System.out.println(n);
        System.out.println(k);

        int rep = -1;
        for(int i = 1; i < count.length; i++) {
            if(count[i] >= k) {
                System.out.println(count[i]);
                rep = i;
                break;
            }
        }

        List<Integer> res = new LinkedList<Integer>();
        if(rep >= 0) {
            for(int i = 0; i < dp.length; i++) {
                if(dp[i] >= rep) {
                    res.add(i);
                    if(res.size() == k)
                        break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = res.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) sb.append(" ");
        }

        System.out.println(sb.toString());


    }
}