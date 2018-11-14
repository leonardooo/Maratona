#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <limits.h>

using namespace std;

class SegmentTreeRMQ {
    public:
        int RMQ(int n, int qs, int qe) {
            return RMQUtil(0, n - 1, qs, qe, 0); 
        }

        void constructST(vector<int> arr, int n) {
            level = arr;
            int x = (int) (ceil(log2(n))); 
    
            int max_size = 2 * (int) pow(2, x) - 1; 
            st.resize(max_size);
            for(int i = 0; i < st.size(); i++) {
                st[i] = -1;
            }
    
            constructSTUtil(0, n - 1, 0);
        }

        void print() {
            cout << "size=" << st.size() << " st_tree=";
            for(int i = 0; i < st.size(); i++) {
                cout << st[i] << ",";
            }
            cout << endl;
        } 

    private:
        vector<int> st;
        vector<int> level;

        int RMQUtil(int ss, int se, int qs, int qe, int index) { 
            if (qs <= ss && qe >= se) {
                return st[index];
            }
    
            if (se < qs || ss > qe) {
                return INT_MAX;
            }
    
            int mid = (ss + se)/2;
            int q1 = RMQUtil(ss, mid, qs, qe, 2*index+1);
            int q2 = RMQUtil(mid+1, se, qs, qe, 2*index+2);
  
            if (q1 == INT_MAX) return q2;
            else if (q2 == INT_MAX) return q1;
  
            return (level[q1] < level[q2]) ? q1 : q2;
        }

        void constructSTUtil(int ss, int se, int index) {
            if (ss == se) { 
                st[index] = ss; 
            } else {     
                int mid = (ss + se)/2;
                constructSTUtil(ss, mid, index*2+1);
                constructSTUtil(mid + 1, se, index*2+2);
                
                if (level[st[2*index+1]] < level[st[2*index+2]]) {
                    st[index] = st[2*index+1]; 
                } else {
                    st[index] = st[2*index+2];
                }
            }
        } 
};

class Station {
    public:
        int n;
        vector<int> neighbors;
};

static vector<Station> stations;
static vector<int> euler_path;
static vector<int> level;
static vector<int> fai;
static SegmentTreeRMQ seg_tree;

void init_arrays(int n) {
    stations.resize(n);
    for(int i = 0; i < n; i++) {
        Station s;
        s.n = i;
        stations[i] = s;
    }

    fai.resize(n);
    for(int i = 0; i < fai.size(); i++) {
        fai[i] = -1;
    }
}

int lca(int a, int b) {
    int fai_a = fai[a];
    int fai_b = fai[b];
    if(fai_a > fai_b) {
        fai_a = fai[b];
        fai_b = fai[a];
    }

// cout << "a=" << a << " b=" << b << endl;
// cout << "fai_a=" << fai_a << " fai_b=" << fai_b << endl;

    int rmq = seg_tree.RMQ(2*fai.size()-1, fai_a, fai_b);

// cout << "rmq=" << rmq << endl;

    //int lca = euler_path[rmq];

// cout << "lca=" << lca << endl;

    return rmq;
}

int dist(int a, int b) {
    // cout << "fai_a=" << fai[a] << " fai_b=" << fai[b] << endl;
    // cout << "level_a=" << level[fai[a]] << " level_b=" << level[fai[b]] << " level_lca_a_b=" << level[ fai[lca(a,b)] ] << endl;
    int dist = level[fai[a]] + level[fai[b]] - 2 * level[ lca(a,b) ];
    // cout << "dist=" << dist << endl;
    return dist;
}

void dfs(int cur, int prev, int dep) {
    if( fai[cur] == -1 ) {
        fai[cur] = euler_path.size();
    }

    euler_path.push_back(cur);
    level.push_back(dep);

    for(long i = 0; i < stations[cur].neighbors.size(); i++) {
        if(stations[cur].neighbors[i] != prev) {
            dfs(stations[cur].neighbors[i], stations[cur].n, dep+1);
            euler_path.push_back(cur);
            level.push_back(dep);
        }
    }
}

void print() {
    cout << "stations=";
    for(int i = 0; i < stations.size(); i++) {
        cout << stations[i].n << ",";
    }
    cout << endl;

    cout << "euler=";
    for(int i = 0; i < euler_path.size(); i++) {
        cout << euler_path[i] << ",";
    }
    cout << endl;

    cout << "level=";
    for(int i = 0; i < level.size(); i++) {
        cout << level[i] << ",";
    }
    cout << endl;

    cout << "fai=";
    for(int i = 0; i < fai.size(); i++) {
        cout << fai[i] << ",";
    }
    cout << endl;
}

int main() {
    int n, q;

    cin >> n;
    cin >> q;

    init_arrays(n);

    for(int i = 0; i < n-1; i++) {
        int u, v;
        cin >> u;
        cin >> v;

        u--;
        v--;

        stations[u].neighbors.push_back(v);
        stations[v].neighbors.push_back(u);
    }

    int n_neig = stations[0].neighbors.size();
    int station_max_neig = 0;
    for(int i = 1; i < n; i++) {
        if( stations[i].neighbors.size() > n_neig ) {
            n_neig = stations[i].neighbors.size();
            station_max_neig = i;
        }
    }

    dfs(station_max_neig,-1, 0);
    seg_tree.constructST(level, 2*n-1);

    // seg_tree.print();
    // print();

    for(int i = 0; i < q; i++) {
        int a,b,c,d;
        cin >> a; cin >> b; cin >> c; cin >> d;

        a--;
        b--;
        c--;
        d--;

        int x = dist(a,b) + dist(c,d);
        int y = min( dist(a,c) + dist(b,d), dist(a,d) + dist(b,c) );

        // cout << "dist_a_b=" << dist(a,b) << " dist_c_d=" << dist(c,d) << endl;
        // cout << "dist_a_c=" << dist(a,c) << " dist_b_d=" << dist(b,d) << endl;
        // cout << "dist_a_d=" << dist(a,d) << " dist_b_c=" << dist(b,c) << endl;

        if( y > x ) {
            cout << 0 << endl;
        } else {
            int res = (x-y)/2 + 1;
            cout << res << endl;
        }
    }
    
}
