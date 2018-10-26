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
            int x = (int) (ceil(log2(n))); 
    
            int max_size = 2 * (int) pow(2, x) - 1; 
            st = new int[max_size];
    
            constructSTUtil(arr, 0, n - 1, 0); 
        } 

    private:
        int *st;

        int minVal(int x, int y) { 
            return (x < y) ? x : y; 
        }

        int getMid(int s, int e) { 
            return s + (e - s) / 2; 
        }

        int RMQUtil(int ss, int se, int qs, int qe, int index) { 
            if (qs <= ss && qe >= se) {
                return st[index];
            }
    
            if (se < qs || ss > qe) 
                return INT_MAX;
    
            int mid = getMid(ss, se); 
            return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1), 
                    RMQUtil(mid + 1, se, qs, qe, 2 * index + 2)); 
        }

        int constructSTUtil(vector<int> arr, int ss, int se, int si) {
            if (ss == se) { 
                st[si] = arr[ss]; 
                return arr[ss]; 
            } 
    
            int mid = getMid(ss, se); 
            st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1), 
                    constructSTUtil(arr, mid + 1, se, si * 2 + 2)); 
            return st[si]; 
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
    stations.resize(n+1);
    for(int i = 1; i <= n; i++) {
        Station s;
        s.n = i;
        stations[i] = s;
    }

    level.resize(n+1);
    level[0] = INT_MAX;
    fai.resize(n+1);
    for(int i = 0; i < fai.size(); i++) {
        fai[i] = -1;
    }
}

int lca(int a, int b) {
    int fai_a = euler_path[fai[a]];
    int fai_b = euler_path[fai[b]];
    if(fai_a > fai_b) {
        fai_a = euler_path[fai[b]];
        fai_b = euler_path[fai[a]];
    }

//cout << "a=" << a << " b=" << b << endl;
//cout << "fai_a=" << fai_a << " fai_b=" << fai_b << endl;

    int lca = seg_tree.RMQ(fai.size(), fai_a, fai_b);

//cout << "lca=" << lca << endl;

    return lca;
}

long dist(int a, int b) {
    return level[a] + level[b] - 2 * level[ lca(a,b) ];
}

void dfs(int cur, int prev, int dep) {
    if( fai[cur] == -1 ) {
        fai[cur] = euler_path.size();
    }

    level[cur] = dep;
    euler_path.push_back(cur);

    for(long i = 0; i < stations[cur].neighbors.size(); i++) {
        if(stations[cur].neighbors[i] != prev) {
            dfs(stations[cur].neighbors[i], stations[cur].n, dep+1);
            euler_path.push_back(cur);
        }
    }
}

void print() {
    for(int i = 0; i < stations.size(); i++) {
        cout << stations[i].n << ",";
    }
    cout << endl;

    for(int i = 0; i < euler_path.size(); i++) {
        cout << euler_path[i] << ",";
    }
    cout << endl;

    for(int i = 0; i < level.size(); i++) {
        cout << level[i] << ",";
    }
    cout << endl;

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

        stations[u].neighbors.push_back(v);
        stations[v].neighbors.push_back(u);
    }

    int n_neig = 0;
    int station_max_neig = 0;
    for(int i = 1; i <= n; i++) {
        if( stations[i].neighbors.size() > n_neig ) {
            n_neig = stations[i].neighbors.size();
            station_max_neig = i;
        }
    }

    dfs(station_max_neig,-1, 0);
    seg_tree.constructST(level, level.size());

    print();

    for(int i = 0; i < q; i++) {
        int a,b,c,d;
        cin >> a; cin >> b; cin >> c; cin >> d;

        int x = dist(a,b) + dist(c,d);
        int y = min( dist(a,c) + dist(b,d), dist(a,d) + dist(b,c) );

        if( y > x ) {
            cout << 0 << endl;
        } else {
            int res = (x-y)/2 + 1;
            cout << res << endl;
        }
    }
    
}
