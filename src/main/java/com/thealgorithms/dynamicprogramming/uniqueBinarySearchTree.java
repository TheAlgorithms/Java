package com.thealgorithms.dynamicprogramming;
class uniqueBinarySearchTree {
    
    public long nthCatalan(int n) {
        long dp[] = new long[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=0;
            for(int j=0;j<i;j++){
                dp[i]=dp[i]+(dp[j]*(dp[i-j-1]));
            }
        }
        return dp[n]; 
   }
        
    public int numTrees(int n) {
       long ans = nthCatalan(n);
        return (int)ans;
    }
    //T.C-O(N*N)
    //S.c-O(N)
}
