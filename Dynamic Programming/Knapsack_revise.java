// A Dynamic Programming based solution for 0-1 Knapsack problem

public class Knapsack {
	static int[][] rv; // rv means return value

	// Print Knapsack's (value, weight) list.
	private static void printMaxValueOfKnapsack(int W, int[] wt, int[] val) {
		int i = rv.length - 1;
		int j = rv[0].length - 1;

		while (rv[i][j] != 0) {
			if (rv[i][j] == rv[i - 1][j])
				i--;
			else {
				int compareValue = rv[i][j] - val[i - 1];
				i--;
				for (int k = W; k > -1; k--) {
					if (rv[i][k] == compareValue) {
						j = k;
						System.out.println("(" + val[i] + ", " + wt[i] + ")");
						break;
					}
				}
			}
		}
	}

	// Print builded table.
	private static void printTable() {
		for (int i = 0; i < rv.length; i++) {
			for (int j = 0; j < rv[0].length; j++) {
				System.out.print("\t" + rv[i][j]);
			}
			System.out.println("");
		}
	}

	private static int knapSack(int W, int wt[], int val[], int n) {
		rv = new int[n + 1][W + 1]; 

		// Build table rv[][] in bottom up manner
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= W; w++) {
				if (i == 0 || w == 0)
					rv[i][w] = 0;
				else if (wt[i - 1] <= w)
					rv[i][w] = Math.max(val[i - 1] + rv[i - 1][w - wt[i - 1]], rv[i - 1][w]);
				else
					rv[i][w] = rv[i - 1][w];
			}
		}

		return rv[n][W];
	}
	
	// Driver program to test above function
	public static void main(String[] args) {
		int val[] = new int[] { 1, 6, 18, 22, 28 };
		int wt[] = new int[] { 1, 2, 5, 6, 7 };
		int W = 11;
		int n = val.length;

		System.out.println(knapSack(W, wt, val, n));
		printMaxValueOfKnapsack(W, wt, val);
		printTable();

	}
}