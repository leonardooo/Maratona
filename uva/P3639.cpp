#include <stdio.h>
#include <iostream>
#include <string>
#include <map>
#include <vector>

using namespace std;

const int MAX = 10000;
bool primes[MAX];
vector<int> all_primes_list;
map<int,int> all_primes_map;
int matrix[1061][1061];

bool neightbors(int n1, int n2) {
   char s1[5];
   char s2[5];
//   cout << "n1 n2 = " << n1 << " " << n2 << endl;     
   sprintf(s1, "%i", n1);
   sprintf(s2, "%i", n2);
//   cout << s1 << " " << s2 << endl;
//   cout << s1[0] << " " << s1[1] << " " << s1[2] << " " << s1[3] << endl;
   int cont = 0;
   for(int i = 0; i < 4; i++) {
      if( s1[i] != s2[i] ) cont++;
      if(cont > 1) return false;
   }
   return true;
}

void sieve() {
   primes[1] = true;
   primes[2] = true;
   primes[3] = true;
   int i,j;
   for(i = 5; i < MAX; i+=2) {
      for(j = 3; j*j <=i ; j+=2)
         if(i % j == 0) break;
      if(i % j != 0 ) primes[i] = true;
   }
}

void floydwarshall() {
   for(int i = 0; i < 1060; i++) {
      for(int j = i+1; j < 1061; j++) {
         matrix[i][j] = 10000;
         matrix[j][i] = 10000;
      }
   }

//   cout << "inicializacao" << endl;   

   for(int i = 0; i < 1061; i++) {
      matrix[i][i] = 0;
   }

//   cout << "zero pra eles mesmos" << endl;   

   int s1,s2;
   for(int i = 0; i < 1060; i++) {
      for(int j = i+1; j < 1061; j++) {
         s1 = all_primes_list[i];
         s2 = all_primes_list[j];
//         cout << s1 << " " << s2 << endl;
         if( neightbors(s1, s2) ) {
            matrix[i][j] = 1;
            matrix[j][i] = 1;
         }
      }
   }

//   cout << "vizinhos" << endl;   

   for(int k = 0; k < 1061; k++) {
      for(int i = 0; i < 1061; i++) {
         for(int j = 0; j < 1061; j++) {
            if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
               matrix[i][j] = matrix[i][k] + matrix[k][j];
            }
         }
      }
   }

//   cout << "floyd em si" << endl;   
}

int main() {

   sieve();

//   cout << "sieve" << endl;

   int count = 0;
   for(int i = 1001; i < MAX; i+=2) {
      if(primes[i]) {
         all_primes_map[i] = count;
         all_primes_list.push_back(i);
         count++;
      }
   }

//   cout << "all primes" << endl;   

   floydwarshall();

//   cout << "floyd" << endl;   

   int n,n1,n2;
   cin >> n;
   for(int i = 0; i < n; i++) {
      cin >> n1;
      cin >> n2;

      int result = matrix[ all_primes_map[n1] ][ all_primes_map[n2] ];
      if(result != 10000) {
         cout << result << endl;
      } else {
         cout << "Impossible" << endl;
      }
   }
}
