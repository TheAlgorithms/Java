package com.thealgorithms.maths;

/**
 * The Bell numbers count the number of partitions of a set.
 * The n-th Bell number is the number of ways a set of n elements can be partitioned
 * into nonempty subsets.
 *
 * <p>
 * This implementation uses the Bell Triangle (Aitken's array) method.
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 * </p>
 *
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 * @see <a href="https://en.wikipedia.org/wiki/Bell_number">Bell Number (Wikipedia)</a>
 */
public final class BellNumbers {

    private BellNumbers() {
    }

    /**
     * Calculates the n-th Bell number using the Bell Triangle.
     *
     * @param n the index of the Bell number (must be non-negative)
     * @return the n-th Bell number
     * @throws IllegalArgumentException if n is negative or n > 25
     */
    public static long compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n == 0) {
            return 1;
        }
        if (n > 25) {
            throw new IllegalArgumentException("n must be <= 25. For larger n, use BigInteger implementation.");
        }

        // We use a 2D array to visualize the Bell Triangle
        long[][] bellTriangle = new long[n + 1][n + 1];

        // Base case: The triangle starts with 1
        bellTriangle[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            // Rule 1: The first number in a new row is the LAST number of the previous row
            bellTriangle[i][0] = bellTriangle[i - 1][i - 1];

            // Rule 2: Fill the rest of the row by adding the previous neighbor and the upper-left neighbor
            for (int j = 1; j <= i; j++) {
                bellTriangle[i][j] = bellTriangle[i][j - 1] + bellTriangle[i - 1][j - 1];
            }
        }

        // The Bell number B_n is the first number in the n-th row
        return bellTriangle[n][0];
    }
}
