#include <iostream>
#include <math.h>
#include <vector>
#include <algorithm>

using namespace std;

class Circle {
    public:
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

    // cout << maior.x << "_" << maior.y << "_" << maior.r << " - " << menor.x << "_" << menor.y << "_" << menor.r << endl;

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

    int tr = 0;

    circles.resize(n);
    for(int i = 0; i < n; i++) {
        double x,y,r;
        cin >> x >> y >> r;

        Circle c;
        c.x = x;
        c.y = y;
        c.r = r;

        circles[i] = c;
    }

    sort(circles.begin(), circles.end());
    
    // for(int i = circles.size()-1; i >= 0; i--) {
    //     cout << "Circle " << i << " " << circles[i].x << "_" << circles[i].y << "_" << circles[i].r << endl;
    // }

    // for(int i = circles.size()-1; i > 0; i--) {
    //     for(int j = i-1; j >= 0; j--) {
    //         if(! circles[i].contains( circles[j] )) {
    //             cout << "Circle " << i << " interceps Circle " << j << endl;
    //             tr++;

    //             if(tr > circles.size()) {
    //                 break;
    //             }
    //         }
    //     }
    //     if(tr > circles.size()) {
    //         break;
    //     }
    // }

    // if(tr > circles.size()) {
    //     cout << "greater" << endl;
    // } else {
    //     cout << (2*tr) << endl;
    // }

    vector<Circle> forest;
    vector<Circle>::reverse_iterator it;
    forest.push_back(circles[0]);

    for(int i = 1; i < circles.size(); i++) {
        for(auto it = forest.rbegin(); it != forest.rend(); it++) {

            // cout << circles[i].r << " - " << (*it).r << endl;

            dfs( circles[i], *it );

            if(res > circles.size()) {
                break;
            }

            if( circles[i].contains( *it ) ) {
                circles[i].inside.push_back(*it);
                forest.erase(--(it.base()));
            }
        }

        forest.push_back(circles[i]);

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