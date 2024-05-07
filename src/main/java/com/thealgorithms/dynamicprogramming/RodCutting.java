package com.thealgorithms.dynamicprogramming;

/**
 * A Dynamic Programming solution for the Rod cutting problem.
 * Returns the best obtainable price for a rod of length n and price[] as prices of different pieces.
 */
public final class RodCutting {
    private RodCutting() {
    }

    /**
     * This method calculates the maximum obtainable value for cutting a rod of length n
     * into different pieces, given the prices for each possible piece length.
     *
     * @param price An array representing the prices of different pieces, where price[i-1]
     *              represents the price of a piece of length i.
     * @param n     The length of the rod to be cut.
     * @return The maximum obtainable value.
     */
    public static int cutRod(int[] price, int n) {
        // Create an array to store the maximum obtainable values for each rod length.
        int[] val = new int[n + 1];
        val[0] = 0;

        // Calculate the maximum value for each rod length from 1 to n.
        for (int i = 1; i <= n; i++) {
            int maxVal = Integer.MIN_VALUE;
            // Try all possible ways to cut the rod and find the maximum value.
            for (int j = 1; j <= i; j++) {
                maxVal = Math.max(maxVal, price[j - 1] + val[i - j]);
            }
            // Store the maximum value for the current rod length.
            val[i] = maxVal;
        }

        // The final element of 'val' contains the maximum obtainable value for a rod of length 'n'.
        return val[n];
    }
}
