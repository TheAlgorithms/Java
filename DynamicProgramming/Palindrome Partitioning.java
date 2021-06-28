public class Solution{ 
	// Returns the minimum number of cuts needed 
	// to partition a string such that every 
	// part is a palindrome 
	static int minPalPartion(String str) 
	{ 
		int n = str.length(); 
		int[][] C = new int[n][n]; 
		boolean[][] P = new boolean[n][n]; 

		int i, j, k, L;  
		for (i = 0; i < n; i++) { 
			P[i][i] = true; 
			C[i][i] = 0; 
		} 

		for (L = 2; L <= n; L++) { 
			for (i = 0; i < n - L + 1; i++) { 
				j = i + L - 1; 
				if (L == 2) 
					P[i][j] = (str.charAt(i) == str.charAt(j)); 
				else
					P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1]; 

				if (P[i][j] == true) 
					C[i][j] = 0; 
				else { 
					C[i][j] = Integer.MAX_VALUE; 
					for (k = i; k <= j - 1; k++) 
						C[i][j] = Integer.min(C[i][j], 
											C[i][k] + C[k + 1][j] + 1); 
				} 
			} 
		} 
		return C[0][n - 1]; 
	} 
} 

