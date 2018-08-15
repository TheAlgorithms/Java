import java.util.Scanner;

/**
    Author : MAYUR VAID
    A Dynamic Programming based solution for Minimum sum partition problem In Java
**/

/**Problem statement is, given a set of integers, partition the set into two subsets, such that the absolute difference
   between there sums is minimum. Each element of set can belong to only one subset and each element of set should belong
   to atleast one subset.
**/

/**The problem can be solved using dynamic programming when the sum of the elements is not too big. We can create a 2D array
   dp[n+1][sum+1] where n is number of elements in given set and sum is sum of all elements. We can construct the solution in
   bottom up manner. To find Minimum sum difference, w have to find j such that Min{sum - j*2 , dp[n][j]  == 1} where j varies
   from 0 to sum/2. The idea is, sum of subset is j and it should be closest to sum/2, i.e., 2*j should be closest to sum.
**/

public class MinimumSumPartition {

	public static int func(int n, int[] arr)
	{
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
	        return diff;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
	    	int n = 4;
	        int[] arr = {1, 6, 11, 5};
	        int diff = func(n,arr);
		System.out.println(diff);
	    }
	}
}
