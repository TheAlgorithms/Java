
import java.io.*;
import java.util.*;

class matrixChainMemorization
{

static int[][] dp = new int[100][100];

// Function for matrix chain multiplication
static int matrixChainMemoised(int[] p, int i, int j)
{
	if (i == j)
	{
	return 0;
	}
	if (dp[i][j] != -1)
	{
	return dp[i][j];
	}
	dp[i][j] = Integer.MAX_VALUE;
	for (int k = i; k < j; k++)
	{
	dp[i][j] = Math.min(
		dp[i][j], matrixChainMemoised(p, i, k)
		+ matrixChainMemoised(p, k + 1, j)
		+ p[i - 1] * p[k] * p[j]);
	}
	return dp[i][j];
}

static int MatrixChainOrder(int[] p, int n)
{
	int i = 1, j = n - 1;
	return matrixChainMemoised(p, i, j);
}

// Driver Code
public static void main (String[] args)
{

	int arr[] = { 1, 2, 3, 4 };
	int n= arr.length;

	for (int[] row : dp)
	Arrays.fill(row, -1);

	System.out.println("Minimum number of multiplications is " + MatrixChainOrder(arr, n));
}
}

// This code is contributed by Harmanjeet singh Kahlon
