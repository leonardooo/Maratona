import java.util.*;
public class Multiplicity {
    static final int MOD = (int)1e9+7;
    static final int MAX = 1000010;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List[] divs = allDivs();
        int[] dp = new int[MAX];
        dp[0] = 1;

        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int v = sc.nextInt();
            Iterator<Integer> it = divs[v].iterator();
            while(it.hasNext()) {
                int d = it.next();
                dp[d] = dp[d]+dp[d-1] < MOD ? dp[d]+dp[d-1] : dp[d]+dp[d-1] - MOD;
            }
        }

        int res = 0;
        for(int i = 1; i < MAX; i++) {
            res = (res + dp[i]) % MOD;
        }

        System.out.println(res);

    }

    private static List[] allDivs() {
        List<Integer>[] divs = new List[MAX];

        for(int i = 1; i < MAX; i++) {
            for(int j = i; j < MAX; j+=i) {
                if(divs[j] == null) divs[j] = new LinkedList<Integer>();
                divs[j].add(j/i);
            }
        }

        return divs;
    }
}