class Solution {
    void rotateMatrix(int a[][], int N) {
        int n=N-1;
        int start=0;
        int end=n;
        while(end>=start){
            for(int k=start;k<end;k++){
                int i=k;
                int j=start;
                int c=0;
                int x=a[i][j];
                int temp;
                while(c<4){
                   temp=a[N-1-j][i];
                    a[N-1-j][i]=x;
                    x=temp;
                    int u=i;
                    int v=N-1-j;
                    j=u;
                    i=v;
                    c++;
                }
            }
            start++;
            end--;
        }
    }
}
