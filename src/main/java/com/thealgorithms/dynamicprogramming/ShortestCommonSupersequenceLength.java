package com.thealgorithms.dynamicprogramming;

// Java program to find length of the shortest supersequence
final class ShortestSuperSequence {
    private ShortestSuperSequence() {
    }

    // Function to find length of the
    // shortest supersequence of x and y.
    static int shortestSuperSequence(String x, String y) {
        int m = x.length();
        int n = y.length();

        // find lcs
        int l = lcs(x, y, m, n);

        // Result is sum of input string
        // lengths - length of lcs
        return (m + n - l);
    }

    // Returns length of LCS
    // for x[0..m - 1], y[0..n - 1]
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

    // Driver code
    public static void main(String[] args) {
        String x = "AGGTAB";
        String y = "GXTXAYB";

        System.out.println("Length of the shortest "
            + "supersequence is " + shortestSuperSequence(x, y));
    }
}
