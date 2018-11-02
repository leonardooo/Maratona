#include <iostream>
#include <math.h>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

class Circle {
    public:
        int n;
        double x,y,r;

        vector<Circle> inside;

        bool contains(Circle o) {
            double d = sqrt( pow(x - o.x, 2) + pow(y - o.y, 2) );
            return r > (d + o.r);
        }

        bool operator <(const Circle& rhs) {
            return r < rhs.r;
        }
};

static int res = 0;
static vector<Circle> circles;

void dfs(Circle maior, Circle menor) {
    if( maior.contains(menor) ) {
        return;
    }

    res++;

    if(res > circles.size()) {
        return;
    }

    for(int i = 0; i < menor.inside.size(); i++) {
        dfs(maior, menor.inside[i]);

        if(res > circles.size()) {
            return;
        }
    }
}

int main() {
    int n;

    cin >> n;

    circles.resize(n);
    for(int i = 0; i < n; i++) {
        double x,y,r;
        cin >> x >> y >> r;

        Circle c;
        c.n = i;
        c.x = x;
        c.y = y;
        c.r = r;

        circles[i] = c;
    }

    sort(circles.begin(), circles.end());

    set<int> forest;
    set<int>::reverse_iterator it;
    forest.insert(0);

    for(int i = 1; i < circles.size(); i++) {
        for(auto it = forest.rbegin(); it != forest.rend(); it++) {

            dfs( circles[i], circles[*it] );

            if(res > circles.size()) {
                break;
            }

            if( circles[i].contains( circles[*it] ) ) {
                circles[i].inside.push_back(circles[*it]);
                forest.erase(--(it.base()));
            }
        }

        forest.insert(i);

        if(res > circles.size()) {
            break;
        }
    }

    if(res > circles.size()) { 
        cout << "greater" << endl;
    } else {
        cout << (res*2) << endl;
    }
}