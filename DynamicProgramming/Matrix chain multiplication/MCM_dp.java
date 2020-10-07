// Java program to implement optimized matrix chain multiplication problem. 
// https://www.geeksforgeeks.org/matrix-chain-multiplication-a-on2-solution/?ref=rp
// https://www.youtube.com/watch?v=9uUVFNOT3_Y&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=35

import java.util.*; 
import java.lang.*; 
import java.io.*; 


class MCM_dp{ 
// Matrix Mi has dimension p[i-1] x p[i] for i = 1..n 
static int MatrixChainOrder(int p[], int n) 
{ 

	/* For simplicity of the program, one extra row and one extra column are 
	allocated in dp[][]. 0th row and 0th column of dp[][] are not used */
	int [][]dp=new int[n][n]; 

/* dp[i, j] = Minimum number of scalar multiplications needed to compute the matrix M[i]M[i+1]...M[j] 
				= M[i..j] where dimension of M[i] is p[i-1] x p[i] */
				
	// cost is zero when multiplying one matrix. 
	for (int i=1; i<n; i++) 
		dp[i][i] = 0; 

	// Simply following above recursive formula. 
	for (int L=1; L<n-1; L++) 
	for (int i=1; i<n-L; i++)	 
		dp[i][i+L] = Math.min(dp[i+1][i+L] + p[i-1]*p[i]*p[i+L], dp[i][i+L-1] + p[i-1]*p[i+L-1]*p[i+L]);	 
	
	return dp[1][n-1]; 
} 

// Driver code 
public static void main(String args[]) 
{ 
	int arr[] = {10, 20, 30, 40, 30} ; 
	int size = arr.length; 

	System.out.print("Minimum number of multiplications is "+ MatrixChainOrder(arr, size)); 

} 
} 
