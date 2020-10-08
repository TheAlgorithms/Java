
#include <bits/stdc++.h>

using namespace std;

// Complete the caesarCipher function below.
string caesarCipher(string s, int k) {
 int n=s.length();
    string a;
    for(int i=0;i<n;i++){
            if(!isalpha(s[i])){
                a+=s[i];
                cout<<s[i];
            }
            else{
                unsigned char res = s[i]+k;
                if(isupper(s[i])&& res>90){
                    res=res-26;
                    a+=res;
                    
                }
                else if(isupper(s[i])){
                    a+=res;
                }
                else if(islower(s[i])&& res>122){
                    res=res-26;
                    a+=res;
                    
                }
                else if(islower(s[i])){
                    a+=res;
                }
            }
        }
        
        return a;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string s;
    getline(cin, s);

    int k;
    cin >> k;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string result = caesarCipher(s, k);

    fout << result << "\n";

    fout.close();

    return 0;
}

