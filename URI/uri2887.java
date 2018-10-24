import java.io.*;
import java.util.*;
class Main {
    static Station[] stations;
    static List<Station> eulerPath;
    static int[] level;
    static int[] fai;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] linha = br.readLine().split(" ");
        int n = Integer.parseInt(linha[0]);
        int q = Integer.parseInt(linha[1]);

        stations = new Station[n+1];
        for(int i = 1; i <= n; i++) {
            stations[i] = new Station(i);
        }

        for(int i = 0; i < n-1; i++) {
            linha = br.readLine().split(" ");
            int u = Integer.parseInt(linha[0]);
            int v = Integer.parseInt(linha[1]);

            stations[u].neighbors.add(stations[v]);
            stations[v].neighbors.add(stations[u]);
        }

        int n_neig = -1;
        int station_max_neig = -1;
        for(int i = 1; i <= n; i++) {
            if( stations[i].neighbors.size() > n_neig ) {
                n_neig = stations[i].neighbors.size();
                station_max_neig = i;
            }
        }

        eulerPath = new LinkedList<Station>();
        level = new int[n+1];
        fai = new int[n+1];
        for(int i = 0; i <= n; i++) {
            fai[i] = -1;
        }

        dfs(station_max_neig,-1, 0);

        // System.out.println(eulerPath);
        // System.out.println(Arrays.toString(level));
        // System.out.println(Arrays.toString(fai));

        for(int i = 0; i < q; i++) {
            linha = br.readLine().split(" ");
            int a = Integer.parseInt(linha[0]);
            int b = Integer.parseInt(linha[1]);
            int c = Integer.parseInt(linha[2]);
            int d = Integer.parseInt(linha[3]);

            int x = dist(a,b) + dist(c,d);
            int y = Math.min( dist(a,c) + dist(b,d), dist(a,d) + dist(b,c) );

            if(y > x) {
                System.out.println(0);
            } else {
                int res = (x-y)/2 + 1;
                System.out.println(res);
            }

            // let X = dist(a, b) + dist(c, d).
            // let Y = min( dist(a, c) + dist(b, d), dist(a, d) + dist(b, c) ).
            // If Y > X, then answer is 0. Otherwise, answer will be (X-Y)/2 + 1.
        
        }

    }

    static int dist(int a, int b) {
        return level[a] + level[b] - 2 * level[ lca(a,b) ];
    }

    static int lca(int a, int b) {
        int min_level = Integer.MAX_VALUE;
        int lca = -1;
        
        int fai_a = fai[a];
        int fai_b = fai[b];
        if(fai[a] > fai[b]) {
            fai_a = fai[b];
            fai_b = fai[a];
        }

        for(int i = fai_a; i <= fai_b; i++) {
            if( level[ eulerPath.get(i).n ] < min_level ) {
                min_level = level[ eulerPath.get(i).n ];
                lca = eulerPath.get(i).n;
            }
        }

        return lca;
    }

    static void dfs(int cur, int prev, int dep) {
        if(fai[cur] == -1) {
            fai[cur] = eulerPath.size();
        }
        level[cur] = dep;
        eulerPath.add( stations[cur] );
        for(int i = 0; i < stations[cur].neighbors.size(); i++) {
            if(stations[cur].neighbors.get(i).n != prev) {
                dfs(stations[cur].neighbors.get(i).n, stations[cur].n, dep+1);
                eulerPath.add( stations[cur] );
            }
        }
        // eulerPath.add( stations[cur] );
    }
}

class Station {
    int n;
    LinkedList<Station> neighbors = new LinkedList<Station>();
    public Station(int n) {
        this.n = n;
    }
    public String toString() {
        return String.valueOf(n);
    }
}