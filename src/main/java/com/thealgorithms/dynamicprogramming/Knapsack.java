package com.thealgorithms.dynamicprogramming;

/**
 * A DynamicProgramming based solution for 0-1 Knapsack problem
 */
public class Knapsack {

    private static int knapSack(int W, int wt[], int val[], int n)
        throws IllegalArgumentException {
        if (wt == null || val == null) {
            throw new IllegalArgumentException();
        }
        int i, w;
        int rv[][] = new int[n + 1][W + 1]; // rv means return value

        // Build table rv[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    rv[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    rv[i][w] =
                        Math.max(
                            val[i - 1] + rv[i - 1][w - wt[i - 1]],
                            rv[i - 1][w]
                        );
                } else {
                    rv[i][w] = rv[i - 1][w];
                }
            }
        }

        return rv[n][W];
    }

    /**
     * At any point we just require previous row data. So, we can optimize the size of array
     * Time Complexity is O(n*W)
     * space complexity is O(2*W) => O(W)
     */
    private static int knapSackMemoryOptimize(int W, int wt[], int val[], int n)
            throws IllegalArgumentException {
        if (wt == null || val == null) {
            throw new IllegalArgumentException();
        }
        int i, w;
        int rv[][] = new int[2][W + 1]; // rv means return value

        // Build table rv[][] in bottom up manner
        for (i = 0; i <=n; i++) {
            for (w = 0; w <= W; w++) {
                if (i==0 || w == 0) {
                    rv[i%2][w] = 0;
                } else if (wt[i - 1] <= w) {
                    rv[i%2][w] =
                            Math.max(
                                    val[i - 1] + rv[(i - 1)%2][w - wt[i - 1]],
                                    rv[(i - 1)%2][w]
                            );
                } else {
                    rv[i%2][w] = rv[(i - 1)%2][w];
                }
            }
        }

        return rv[n%2][W];
    }
    // Driver program to test above function
    public static void main(String args[]) {
        int val[] = new int[] { 50, 100, 130 };
        int wt[] = new int[] { 10, 20, 40 };
        int W = 50;
        System.out.println(knapSack(W, wt, val, val.length));
        System.out.println(knapSackMemoryOptimize(W, wt, val, val.length));
    }
}
