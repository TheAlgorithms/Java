package com.thealgorithms.dynamicprogramming;
class Solution {
 
     //T.C O(N*M*k)
     //S.C O(N*M*K)
     //just we need to store the path sum into sum parameter and when we reach at destination (n-1,m-1) just check is it divisible by k or not and count
       int cnt=0;
       int n,m,mod=(int)1e9+7;
    private int solve(int i,int j,int sum,int grid[][],int dp[][][],int k){
        if(i==n-1 && j==m-1) return (sum+grid[i][j])%k==0?1:0;
        if(i>=grid.length || j>=grid[0].length) return 0;
        if(dp[i][j][sum]!=-1) return dp[i][j][sum];
        int down = solve(i+1,j,(sum+grid[i][j])%k,grid,dp,k)%mod;
        int right = solve(i,j+1,(sum+grid[i][j])%k,grid,dp,k)%mod;
        return dp[i][j][sum]=(down+right)%mod;
    }
    public int numberOfPaths(int[][] grid, int k) {
        n=grid.length;
        m=grid[0].length;
        if(n==1) return IntStream.of(grid[0]).sum()%k==0?1:0;
        int dp[][][] = new int[n][m][k];
        for(int[][] a: dp){
            for(int[] p: a){
                Arrays.fill(p,-1);
            }  
        }
        return solve(0,0,0,grid,dp,k)%mod;
    }
}
