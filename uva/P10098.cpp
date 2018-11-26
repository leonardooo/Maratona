#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

string sort(string word, int length) {
        for(int j = 0; j < length-1; j++) {
                for(int k = j+1; k < length; k++) {
                        if( word[j] > word[k] ) {
                                char temp = word[j];
                                word[j] = word[k];
                                word[k] = temp;
                        }
                }
        }
        return word;
}

int main() {

        int n;
        cin >> n;

        for(int i = 0; i < n; i++) {
                string word;
                cin >> word;
                int length = word.length();

                word = sort(word,length);

                cout << word << endl;
                while(next_permutation( word.begin(), word.end() ))
                        cout << word << endl;
                cout << endl;
        }

}