import java.io.*;
import java.util.*;
public class Cutting {

    static final int MAX = 2*((int)Math.pow(10,5)) + 1;
    static int[] array;
    static int[] dp;
    static int n, k;

    private static List<Integer> can(int count) {
        List<Integer> res = new LinkedList<Integer>();
        
        for(int i = 0; i < MAX; i++) {
            int y = dp[i] / count;
            while(y-- > 0) {
                res.add(i);
            }
        }

        while(res.size() > k) {
            res.remove(res.size()-1);
        }

        if(res.size() < k) {
            res.clear();
        }
        
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] linha = br.readLine().split(" ");
        n = Integer.parseInt(linha[0]);
        k = Integer.parseInt(linha[1]);
        linha = br.readLine().split(" ");

        array = new int[n];
        dp = new int[MAX];
        
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(linha[i]);
            dp[array[i]]++;
        }

        int l = 0;
        int r = n;
        while(l != r) {
            int mid = (l+r+1)/2; //System.out.println(l); System.out.println(r); System.out.println(mid);

            if(can(mid).size() == 0) {
                r = mid-1;
            } else {
                l = mid;
            }
        }
        
        List<Integer> res = can(l);

        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = res.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}