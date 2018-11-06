#include <bits/stdc++.h>

using namespace std;

struct show
{
    int i, f, o;

    bool operator<(const show &a) const
    {
        return f < a.f;
    }
};

vector<pair<int, int>> pd[(1 << 10) + 1];

												//3
												//4 1 10 100 20 30 90 40 50 95 80 100 90
												//1 40 50 13
												//2 9 29 231 30 40 525
   
int main()
{
    vector<vector<show>> shows;

    int n, m;
    
    cin >> n;

    shows.resize(n);

    vector<pair<int, int>> events;

    for(int i = 0; i < n; i++)
    {
        cin >> m;

        shows[i].resize(m);

        for(auto &it : shows[i])
            cin >> it.i >> it.f >> it.o, events.emplace_back(it.f, i);

        sort(shows[i].rbegin(), shows[i].rend());
    }

    sort(events.rbegin(), events.rend());

    int maxi = (1 << n);

    pd[0].emplace_back(0, 0);

    while(events.size())
    {
        int k = events.back().second;
        events.pop_back();

        for(int i = 1; i < maxi; i++)
        {
            if((i & (1 << k)) == 0)
                continue;
            
            auto &shw = shows[k].back();

            int r = -(1 << 30);

            if(pd[i].size())
                for(auto &it : pd[i])
                    if(it.first <= shw.i)
                        r = max(r, it.second + shw.o);
            
            if(pd[i ^ (1 << k)].size())
                for(auto &it : pd[i ^ (1 << k)])
                    if(it.first <= shw.i)
                        r = max(r, it.second + shw.o);

            if(r != -(1 << 30))
                pd[i].emplace_back(shw.f, r);
        }
        
        shows[k].pop_back();
    }
    
    int r = -(1 << 30);

    if(pd[maxi - 1].size())
        for(auto &it : pd[maxi - 1])
            r = max(r, it.second);

    cout << (r < 0 ? -1 : r) << endl;
}
