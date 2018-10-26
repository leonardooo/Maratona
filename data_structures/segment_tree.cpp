#include <iostream>
#include <math.h>
#include <limits.h>

using namespace std;

class SegmentTreeRMQ {
    public:
        int RMQ(int n, int qs, int qe) {
            return RMQUtil(0, n - 1, qs, qe, 0); 
        }

        void constructST(int arr[], int n) { 
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
            if (qs <= ss && qe >= se) 
                return st[index]; 
    
            if (se < qs || ss > qe) 
                return INT_MAX;
    
            int mid = getMid(ss, se); 
            return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1), 
                    RMQUtil(mid + 1, se, qs, qe, 2 * index + 2)); 
        }

        int constructSTUtil(int arr[], int ss, int se, int si) {
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

int main() {
    SegmentTreeRMQ st;

    int arr[] = {10,2,7,4,6,5,3,8,9,1};
    int n = sizeof(arr)/sizeof(arr[0]);
    st.constructST(arr, n);

    cout << st.RMQ(n, 2, 6) << endl;
}