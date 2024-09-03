package com.thealgorithms.dynamicprogramming;

/**
 * Class that provides methods to calculate the length of the shortest
 * supersequence of two given strings. The shortest supersequence is the smallest string
 * that contains both given strings as subsequences.
 */
final class ShortestCommonSupersequenceLength {
    private ShortestCommonSupersequenceLength() {
    }

    /**
     * Finds the length of the shortest supersequence of two given strings.
     * The shortest supersequence is defined as the smallest string that contains both
     * given strings as subsequences.
     *
     * @param x The first input string.
     * @param y The second input string.
     * @return The length of the shortest supersequence of the two strings.
     */
    static int shortestSuperSequence(String x, String y) {
        int m = x.length();
        int n = y.length();

        // find lcs
        int l = lcs(x, y, m, n);

        // Result is sum of input string
        // lengths - length of lcs
        return m + n - l;
    }

    /**
     * Calculates the length of the longest common subsequence (LCS) between two strings.
     * The LCS is the longest sequence that can be derived from both strings by deleting some
     * (or none) of the characters without changing the order of the remaining characters.
     *
     * @param x The first input string.
     * @param y The second input string.
     * @param m The length of the first input string.
     * @param n The length of the second input string.
     * @return The length of the longest common subsequence of the two strings.
     */
    static int lcs(String x, String y, int m, int n) {
        int[][] lN = new int[m + 1][n + 1];
        int i;
        int j;

        // Following steps build lN[m + 1][n + 1]
        // in bottom up fashion. Note that
        // lN[i][j] contains length of lNCS
        // of x[0..i - 1]and y[0..j - 1]
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    lN[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    lN[i][j] = lN[i - 1][j - 1] + 1;
                } else {
                    lN[i][j] = Math.max(lN[i - 1][j], lN[i][j - 1]);
                }
            }
        }

        // lN[m][n] contains length of LCS
        // for x[0..n - 1] and y[0..m - 1]
        return lN[m][n];
    }
}
