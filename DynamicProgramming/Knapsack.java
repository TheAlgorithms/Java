package DynamicProgramming;

/** A DynamicProgramming based solution for 0-1 Knapsack problem */
public class Knapsack {

  private static int knapSack(int W, int wt[], int val[], int n) throws IllegalArgumentException {
    if (wt == null || val == null) throw new IllegalArgumentException();
    int i, w;
    int rv[][] = new int[n + 1][W + 1]; // rv means return value

    // Build table rv[][] in bottom up manner
    for (i = 0; i < n; i++) {
      for (w = 0; w <= W; w++) {
        if (wt[i] <= w) rv[i + 1][w] = Math.max(val[i] + rv[i][w - wt[i]], rv[i][w]);
        else rv[i + 1][w] = rv[i][w];
      }
    }

    return rv[n][W];
  }

  // Driver program to test above function
  public static void main(String args[]) {
    int val[] = new int[] {50, 100, 130};
    int wt[] = new int[] {10, 20, 40};
    int W = 50;
    System.out.println(knapSack(W, wt, val, val.length));
  }
}
