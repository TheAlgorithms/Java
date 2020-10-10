/*
Any programmer worth his salt would be familiar with the famous Longest Common Subsequence problem. Mancunian was asked to solve the same by an incompetent programmer. As expected, he solved it in a flash. To complicate matters, a twist was introduced in the problem.

In addition to the two sequences, an additional parameter k was introduced. A k-ordered LCS is defined to be the LCS of two sequences if you are allowed to change at most k elements in the first sequence to any value you wish to. Can you help Mancunian solve this version of the classical problem?

Input:
The first line contains three integers N, M and k, denoting the lengths of the first and second sequences and the value of the provided parameter respectively.
The second line contains N integers denoting the elements of the first sequence.
The third line contains M integers denoting the elements of the second sequence.

Output:
Print the answer in a new line.

Constraints:
1 <= N, M <= 2000
1 <= k <= 5
1 <= element in any sequence <= 109
*/

SOLUTION JAVA:
import java.util.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[m];
        for (int i = 0; i <n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        
        }
        int strg[][] = new int[n+1][m+1];
        for (int i = 1; i <n+1; i++) {
            for (int j = 1; j <m+1 ; j++) {
                if (a[i-1] == b[j-1]){
                    strg[i][j] = strg[i-1][j-1] + 1;
                }
                else{
                    strg[i][j] = Math.max(strg[i-1][j],strg[i][j-1]);
                }
            }
        }
        int maxchange = Math.min(Math.min(n,m)-strg[n][m],k);
        System.out.println(maxchange+strg[n][m]);
 
    }
}

SOLUTION PYTHON:
def lcs(n,m,a,b):
    arr=[[0]*(m+1) for _ in range(n+1)]
    for i in range(n):
        for j in range(m):
            if(a[i]==b[j]):
                arr[i+1][j+1]=arr[i][j]+1
            else:
                arr[i+1][j+1]=max(arr[i+1][j],arr[i][j+1])
    return arr[-1][-1]
if __name__=="__main__":
    n,m,k=map(int,input().split())
    a=list(map(int,input().split()))
    b=list(map(int,input().split()))
    x=m-len(b)
    for _ in range(x):
        c=int(input())
        b.append(c)
    print(lcs(n,m,a,b)+k)