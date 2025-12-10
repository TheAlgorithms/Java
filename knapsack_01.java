import java.util.*;
class knapsack_01{

    static int solve(int[] wt,int[] val,int w){
        int n=wt.length;
	int[][] dp=new int[n+1][w+1];
	for(int i=0;i<=n;i++){
           for(int j=0;j<=w;j++){
               if(i==0 || j==0){
                  dp[i][j]=0;
	       }
	       else if(wt[i-1]<=j){
                  dp[i][j]=Math.max(val[i-1]+dp[i-1][j-wt[i-1]],dp[i-1][j]);
	       }
	       else{
                  dp[i][j]=dp[i-1][j];
	       }
	   }
	}
        return dp[n][w];
    }
    public static void main(String[] args){
       int[] wt={1,2,3,4,5,6,7};
       // given a weight array 

       int[] val={12,21,13,15,19,23,25};
       // given a value array,containing values of ith weight 
       // of weight array

       int w=15;
       // given the capacity of knapsack

       System.out.println(solve(wt,val,w));
    }
}
