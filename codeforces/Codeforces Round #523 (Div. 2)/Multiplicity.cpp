#include <iostream>
#include <vector>
using namespace std;

int main() {
    const int MOD = 1e9+7;
    const int MAX = 1001000;

    vector<int> divs[MAX];
    for(int i = 1; i < MAX; i++) {
        for(int j = i; j < MAX; j+=i) {
            divs[j].push_back(j/i);
        }
    }

    int dp[MAX];
    dp[0] = 1;

    int n;
    cin >> n;

    for(int i = 0; i < n; i++) {
        int v;
        cin >> v;
        for(auto d: divs[v]) {
            dp[d] = dp[d]+dp[d-1];
            dp[d] %= MOD;
        }
    }

    int res = 0;
    for(int i = 1; i < MAX; i++) {
        res = (res + dp[i]) % MOD;
    }

    cout << res << endl;
}