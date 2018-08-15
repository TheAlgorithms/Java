package DynamicProgramming;

import java.util.Scanner;

public class MinimumSumPartition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    while(t-->0)
	    {
	        int n = s.nextInt();
	        int[] arr = new int[n];
	        int sum = 0;
	        for(int i=0;i<n;i++)
	        {
	            arr[i] = s.nextInt();
	            sum += arr[i];
	        }
	        boolean[][] dp = new boolean[sum+1][n+1];
	        for(int i=0;i<=sum;i++)
	            for(int j=0;j<=n;j++)
	            {
	                if(i==0)
	                    dp[i][j] = true;
	                else if(j==0)
	                    dp[i][j] = false;
	                else
	                {
	                    dp[i][j] = dp[i][j-1];
	                    if(i-arr[j-1]>=0)
	                        dp[i][j] = dp[i][j] || dp[i-arr[j-1]][j-1];
	                }
	            }
	        int diff = Integer.MAX_VALUE;
	        for(int i=sum/2;i>=0;i--)
	            if(dp[i][n]==true && diff>sum-2*i)
	                diff=sum-2*i;
	        System.out.println(diff);
	    }
	}
}