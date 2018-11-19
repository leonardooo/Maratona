#include <bits/stdc++.h>

using namespace std;

int main() {
	
	int n;
	cin >> n;
	map<int, int> cnt;
	for (int i = 0; i < n; ++i) {
		int x;
		cin >> x;
		++cnt[x];
	}
	
	vector<int> cnts;
	for (auto it : cnt) {
		cnts.push_back(it.second);
	}
	sort(cnts.begin(), cnts.end());
	
	int ans = 0;
	for (int i = 1; i <= cnts.back(); ++i) {
		int pos = int(cnts.size()) - 1;
		int cur = i;
		int res = cur;

		while (cur % 2 == 0 && pos > 0) {
			cur /= 2;
			--pos;
			if (cnts[pos] < cur) break;
			res += cur;

            cout << "i=" << i << " pos=" << pos << " cnts[pos]=" << cnts[pos] << " cur=" << cur << " res=" << res << endl;

		}
		ans = max(ans, res);
        cout << "ans=" << ans << endl;
	}
	cout << ans << endl;
	
	return 0;
}