package com.thealgorithms.others;

public class PaintersProblem {
    // function to calculate sum between two indices
	// in array
	static int sum(int arr[], int start, int end)
	{
		int result = 0;
		for (int i = start; i <= end; i++)
			result += arr[i];
		return result;
	}

	// bottom up tabular dp
	static int findMaxVal(int arr[], int n, int k)
	{
		// initialize table
		int dp[][] = new int[k + 1][n + 1];

		// base cases
		// k=1
		for (int i = 1; i <= n; i++)
			dp[1][i] = sum(arr, 0, i - 1);

		// n=1
		for (int i = 1; i <= k; i++)
			dp[i][1] = arr[0];

		// 2 to k partitions
		for (int i = 2; i <= k; i++) { // 2 to n boards
			for (int j = 2; j <= n; j++) {

				// track minimum
				int best = Integer.MAX_VALUE;

				// i-1 th separator before position
				// arr[k=1..j]
				for (int a = 1; a <= j; a++)
					best = Math.min(
						best, Math.max(dp[i - 1][a],
									sum(arr, a, j - 1)));

				dp[i][j] = best;
			}
		}

		// returning the calculated value
		return dp[k][n];
	}

	public static void main(String args[])
	{
		int arr[] = { 10, 20, 60, 50, 30, 40 };

		// Calculate size of array.
		int n = arr.length;
		int k = 3;
		System.out.println(findMaxVal(arr, n, k));
	}
}
