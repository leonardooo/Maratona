import java.io.*;
import java.util.*;
public class Festival {

    static List<Show> shows;
    static int n;
    static int[] to;
    static int[][] dp;
    static boolean[][] vis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        shows = new ArrayList<Show>();

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String[] linha = br.readLine().split(" ");
            int m = Integer.parseInt(linha[0]);

            int index = 1;
            for(int j = 1; j <= m; j++) {
                Show s = new Show();
                s.ini = Integer.parseInt(linha[index++]);
                s.fim = Integer.parseInt(linha[index++]);
                s.mus = Integer.parseInt(linha[index++]);
                s.palco = i;

                shows.add(s);
            }
        }

        Collections.sort(shows);
        to = new int[shows.size()];
        to[shows.size()-1] = shows.size();

        for(int i = 0; i < shows.size()-1; i++) {
            to[i] = shows.size();
            for(int j = i+1; j < shows.size(); j++) {
                if( shows.get(i).fim <= shows.get(j).ini ) {
                    to[i] = j;
                    break;
                }
            }
        }

        dp = new int[shows.size()+1][(1 << n)];
        vis = new boolean[shows.size()+1][(1 << n)];
        int maxj = (1 << n);

        int res = maxProfit(0,0);

        res = res < 0 ? -1 : res;
        System.out.println(res);


    }

    static int maxProfit(int x, int y) {
        if( x > shows.size() -1 ) {
            if( y == (1 << n) - 1 ) return 0;
            else return -Integer.MAX_VALUE;
        } 

        if( vis[x][y] ) return dp[x][y];
        vis[x][y] = true;

        int sum = 0;

        sum += Math.max(
            maxProfit( x+1, y ),
            maxProfit( to[x], y | (1 << shows.get(x).palco) ) + shows.get(x).mus
        );

        dp[x][y] = sum;
        return dp[x][y];
    }
}

class Show implements Comparable {
    int ini,fim,mus, palco;

    public int compareTo(Object o) {
        Show s = (Show)o;
        return Integer.valueOf(ini).compareTo(Integer.valueOf(s.ini));
    }

    public String toString() {
        return ini + "-" + fim + "-" + mus + "-" + palco;
    }
}