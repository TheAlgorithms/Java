import java.util.Scanner;

/**
    Author : MAYUR VAID
    A Dynamic Programming based solution for Maximum Size Square Sub Matrix problem In Java
**/

/**A matrix of 0s and 1s is given. The idea of the algorithm is to construct an auxiliary size matrix dp[][]
   in which each entry dp[i][j] represents size of the square sub-matrix with all 1s including arr[i][j] where
   arr[i][j] is the rightmost and bottommost entry in sub-matrix.
**/

public class MaximumSizeSquareSubMatrixWithAll1s {
	
	public static int func(int n, int m, int[][] arr)
	{
		int[][] dp = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				//Copy first row and first column as it is.
				if(i==0 || j==0)
					dp[i][j] = arr[i][j];
				else if(arr[i][j]==0)	//Sub matrix at point (i,j) cannot be made.
					dp[i][j] = 0;
				else
					dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
			}
		//Find maximum size.
		int maxval = 0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(maxval<dp[i][j])
					maxval = dp[i][j];
		return maxval;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = 6;
		int m = 5;
		int[][] arr = {
				{0  1  1  0  1},
				{1  1  0  1  0},
				{0  1  1  1  0},
				{1  1  2  2  0},
				{1  2  2  3  1},
				{0  0  0  0  0}
			      };
		int maxval = func(n,m,arr);
		System.out.println(maxval);
	}

}
