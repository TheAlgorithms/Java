#include<bits/stdc++.h>
using namespace std;
int main(){
    string s;
    cin>>s;
    int n=s.length();
    string copy;
    int j=0;
    for(int i=n-1;i>=0;i--){
        copy[j]=s[i];
        j++;
    }
    int start=0,end=0;
    for(int i=0;i<n;i++){
        if(copy[i]== ' '){
            end=i;
            for(int j=start;j<end;j++){
                cout<<copy[j];
            }
            start=i+1;
        }
    }
    return 0;
}
