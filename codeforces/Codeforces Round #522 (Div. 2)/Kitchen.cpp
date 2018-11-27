#include <iostream>
using namespace std;

int main() {
    int n,k;
    cin >> n;
    cin >> k;

    int count[101];
    for(int i = 0; i < 101; i++) {
        count[i] = 0;
    }

    int a[n];
    for(int i = 0; i < n; i++) {
        cin >> a[i];
        count[a[i]]++;
    }

    int max = -1;
    int typ = 0;
    for(int i = 0; i < 101; i++) {
        if(max < count[i]) {
            max = count[i];
        }
        if(count[i] > 0) {
            typ++;
        }
    }

    int ans = max / k;
    int res = max % k;
    if(res > 0) ans++;

    //cout << "max=" << max << " typ=" << typ << " ans=" << ans << endl;

    ans = (ans*k*typ)-n;
    cout << ans << endl;
}