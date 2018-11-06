#include <iostream>
using namespace std;
int main() {
	int maxi = 8;
	int k = 1;
	for(int i = 1; i < maxi; i++) {
		int p = (i & (1 << k));
		cout << "i=" << i << " p=" << p << " 1<<k=" << (1<<k) << endl;
            	if(p == 0) {
                	continue;
		} else {
			cout << "else" << endl;
		}
	}
}
