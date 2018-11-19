import java.io.*;
import java.util.*;
public class Thematic {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] linha = br.readLine().split(" ");
        Map<Integer,Integer> mapa = new HashMap<Integer,Integer>();
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(linha[i]);

            if(mapa.get(num) == null) {
                mapa.put(num, 1);
            } else {
                mapa.put(num, mapa.get(num)+1);
            }
        }

        List<Integer> qnts = new ArrayList<Integer>(mapa.values());
        Collections.sort(qnts);

        int ans = 0;
        for(int i = 1; i <= qnts.get(qnts.size()-1); i++) {
            int pos = qnts.size()-1;
            int cur = i;
            int res = cur;
            
            while(cur % 2 == 0 && pos > 0) {
                cur = cur / 2;
                pos--;

                if(qnts.get(pos) < cur)
                    break;
                
                res = res + cur;
            }

            ans = Math.max(ans, res);
        }

        System.out.println(ans);
        
    }
}