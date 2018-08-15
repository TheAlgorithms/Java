package DynamicProgramming;

import java.util.Scanner;

public class MaximumSizeSquareSubMatrixWithAll1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		int[][] arr = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				arr[i][j] = s.nextInt();
		int[][] dp = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				if(i==0 || j==0)
					dp[i][j] = arr[i][j];
				else if(arr[i][j]==0)
					dp[i][j] = 0;
				else
					dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
			}
		int maxval = 0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(maxval<dp[i][j])
					maxval = dp[i][j];
		System.out.println(maxval);
	}

}
