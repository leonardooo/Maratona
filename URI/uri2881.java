import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Show> shows = new ArrayList<Show>();

        int n = Integer.parseInt(br.readLine());

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
        int[] to = new int[shows.size()];
        Arrays.fill(to, -1);
        to[shows.size()-1] = shows.size();

        for(int i = 0; i < shows.size()-1; i++) {
            for(int j = i+1; j < shows.size(); j++) {
                if( shows.get(i).fim <= shows.get(j).ini ) {
                    to[i] = j;
                    break;
                }
            }
        }

        // System.out.println("Shows:");
        // System.out.println(shows);
        
        int[][] dp = new int[shows.size()+1][(1 << n)];
        // for(int i = 0; i < shows.size(); i++) {
        //     if(to[i] != -1) {
        //         dp[to[i]][1 << shows.get(i).palco] = shows.get(i).mus;
        //     }
        // }
    
        int maxj = (1 << n); //System.out.println(maxj + "\n");
        for(int j = 0; j < maxj; j++) {
            for(int i = 0; i < shows.size(); i++) {
                //if(dp[i][j] == 0) continue;

                dp[i+1][j] = Math.max( dp[i+1][j], dp[i][j] );

                if(to[i] != -1) {
                    dp[to[i]][j | (1 << shows.get(i).palco)] = Math.max( dp[to[i]][j | (1 << shows.get(i).palco)],
                                                                        dp[i][j] + shows.get(i).mus );
                }
            }
        }

        // for(int i = 0; i <= shows.size(); i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        int res = 0;
        for(int i = 0; i <= shows.size(); i++) {
            res = Math.max( res, dp[i][maxj-1] );
        }

        res = res == 0 ? -1 : res;
        System.out.println(res);


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