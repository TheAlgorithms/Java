package dp;

import java.util.Arrays;

public class UnboundedKnapsack {

	static int unboundedknapsack(int[] wt, int[] val, int w, int i) {
		if(i>=wt.length || w==0)
			return 0;
		if(wt[i]<=w)
			return Math.max(val[i]+unboundedknapsack(wt, val, w-wt[i], i), unboundedknapsack(wt, val, w, i+1));
		return unboundedknapsack(wt, val, w, i+1);
	}
	
	static int unboundedKnapsackMemo(int[] wt, int[] val, int w, int i){
		if(dp[i][w]!=-1)
			return dp[i][w];
		if(i>=wt.length || w==0)
			return dp[i][w]=0;
		if(wt[i]<=w)
			return dp[i][w]=Math.max(val[i]+unboundedKnapsackMemo(wt, val, w-wt[i], i), unboundedKnapsackMemo(wt, val, w, i+1));
		return dp[i][w]=unboundedKnapsackMemo(wt, val, w, i+1);
	}
	
	static int unboundedKnapsackTopDown(int[] wt, int[] val, int w){
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if(i==0 || j==0)
					dp[i][j]=0;
				else if(wt[i-1]<=j)
					dp[i][j]=Math.max(val[i-1]+dp[i][j-wt[i-1]], dp[i-1][j]);
				else
					dp[i][j]=dp[i-1][j];
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}
	
	static int[][] dp;
	
	public static void main(String[] args) {
		int[] wt={1,2,3,4};
		int[] val={25,35,10,25};
		int w=8;
		dp=new int[wt.length+1][w+1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(unboundedknapsack(wt, val, w, 0));
		System.out.println(unboundedKnapsackMemo(wt, val, w, 0));
		System.out.println(unboundedKnapsackTopDown(wt, val, w));
	}
}
