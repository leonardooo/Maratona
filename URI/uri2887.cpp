#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Station {
    public:
        long n;
        vector<long> neighbors;
};

static vector<Station> stations;
static vector<long> euler_path;
static vector<long> level;
static vector<long> fai;
static vector<long> leafs;
static vector<long> leafs_index;
static long **lca_table;

void init_arrays(int n) {
    Station dummy;
    dummy.n = -1;
    //stations.resize(n+1);
    stations.push_back(dummy);
    for(long i = 1; i <= n; i++) {
        Station s;
        s.n = i;
        stations.push_back(s);
    }

    //euler_path.resize(2*n);
    //level.resize(n+1);
    //fai.resize(n+1);
    for(long i = 0; i <= n; i++) {
        level.push_back(-1);
        fai.push_back(-1);
    }
}

long lca(long a, long b) {
    long min_level = 100009;
    long lca = -1;
    
    long fai_a = fai[a];
    long fai_b = fai[b];
    if(fai[a] > fai[b]) {
        fai_a = fai[b];
        fai_b = fai[a];
    }

    for(long i = fai_a; i <= fai_b; i++) {
        if( level[ euler_path[i] ] < min_level ) {
            min_level = level[ euler_path[i] ];
            lca = euler_path[i];
        }
    }

    return lca;
}

long dist(long a, long b) {
    return level[a] + level[b] - 2 * level[ lca_table[ leafs_index[a] ][ leafs_index[b] ] ];
}

void dfs(long cur, long prev, long dep) {
    if( fai[cur] == -1 ) {
        fai[cur] = euler_path.size();
    }

    level[cur] = dep;
    euler_path.push_back( cur );

    for(long i = 0; i < stations[cur].neighbors.size(); i++) {
        if(stations[cur].neighbors[i] != prev) {
            dfs(stations[cur].neighbors[i], stations[cur].n, dep+1);
            euler_path.push_back( cur );
        }
    }
}


int main() {
    long n, q;

    cin >> n;
    cin >> q;

    init_arrays(n);

    for(long i = 0; i < n-1; i++) {
        long u, v;
        cin >> u;
        cin >> v;

        stations[u].neighbors.push_back(v);
        stations[v].neighbors.push_back(u);
    }

    long n_neig = 0;
    long station_max_neig = 0;
    leafs_index.resize(n+1);
    for(long i = 1; i <= n; i++) {
        if( stations[i].neighbors.size() > n_neig ) {
            n_neig = stations[i].neighbors.size();
            station_max_neig = i;
        }

        if( stations[i].neighbors.size() == 1 ) {
            leafs.push_back( i );
            leafs_index[i] = leafs.size()-1;
        }
    }

    dfs(station_max_neig,-1, 0);

    lca_table = new long*[leafs.size()];
    for(long i = 0; i < leafs.size(); i++) {
        lca_table[i] = new long[leafs.size()];
    }

    for(long i = 0; i < leafs.size()-1; i++) {
        for(long j = i+1; j < leafs.size(); j++) {
            lca_table[ i ][ j ] = lca(leafs[i], leafs[j]);
            lca_table[ j ][ i ] = lca_table[ i ][ j ];
        }
    }

    for(long i = 0; i < q; i++) {
        long a,b,c,d;
        cin >> a; cin >> b; cin >> c; cin >> d;

        long x = dist(a,b) + dist(c,d);
        long y = min( dist(a,c) + dist(b,d), dist(a,d) + dist(b,c) );

        if( y > x ) {
            cout << 0 << endl;
        } else {
            long res = (x-y)/2 + 1;
            cout << res << endl;
        }
    }
}
