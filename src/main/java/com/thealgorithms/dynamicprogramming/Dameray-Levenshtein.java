// Java code to calculate optimal string alignment distance
public class Main {
	public static int optimalStringAlignmentDistance(String s1, String s2) {
		// Create a table to store the results of subproblems
		int[][] dp = new int[s1.length()+1][s2.length()+1];

		// Initialize the table
		for (int i = 0; i <= s1.length(); i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= s2.length(); j++) {
			dp[0][j] = j;
		}

		// Populate the table using dynamic programming
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
				}
			}
		}

		// Return the edit distance
		return dp[s1.length()][s2.length()];
	}

	public static void main(String[] args) {
		System.out.println(optimalStringAlignmentDistance("Hello", "Hello There"));
	}
}

