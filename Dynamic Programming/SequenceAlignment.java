public class SequenceAlignment {
	
	private static int sequence_alignment(int m, int n, char[] x, char[] y, int gap) {
		int[][] M = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			M[i][0] = i;
		for (int j = 0; j <= n; j++)
			M[0][j] = j;

		// Build table M[][] in bottom up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int min = Math.min(match(i - 1, j - 1, x, y) + M[i - 1][j - 1], gap + M[i - 1][j]);
				M[i][j] = Math.min(min, gap + M[i][j - 1]);
			}
		}
		return M[m][n];
	}

	// Function to check if x[i] and y[j] are equal
	private static int match(int i, int j, char[] x, char[] y) {
		int mismatchCost = 2;
		if (x[i] == y[j])
			return 0;
		return mismatchCost;
	}

	// Driver program to test above function
	public static void main(String[] args) {
		int gapCost = 1; // Gap penalty
		String str_1 = "ocurrance";
		String str_2 = "occurrence";
		char[] arr_str_1 = str_1.toCharArray();
		char[] arr_str_2 = str_2.toCharArray();
		System.out.println(sequence_alignment(str_1.length(), str_2.length(), arr_str_1, arr_str_2, gapCost));

	}

}
