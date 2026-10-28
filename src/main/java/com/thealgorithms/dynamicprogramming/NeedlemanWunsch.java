package com.thealgorithms.dynamicprogramming;

/**
 * The Needleman–Wunsch algorithm performs global sequence alignment between two strings.
 * It computes the optimal alignment score using dynamic programming,
 * given a scoring scheme for matches, mismatches, and gaps.
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 */
public final class NeedlemanWunsch {

    private NeedlemanWunsch() {
        // Utility Class
    }

    /**
     * Computes the Needleman–Wunsch global alignment score between two strings.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @param matchScore score for a character match
     * @param mismatchPenalty penalty for a mismatch (should be negative)
     * @param gapPenalty penalty for inserting a gap (should be negative)
     * @return the optimal alignment score
     */
    public static int align(String s1, String s2, int matchScore, int mismatchPenalty, int gapPenalty) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings must not be null.");
        }

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Initialize gap penalties for first row and column
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i * gapPenalty;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j * gapPenalty;
        }

        // Fill the DP matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matchOrMismatch = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? matchScore : mismatchPenalty;

                dp[i][j] = Math.max(Math.max(dp[i - 1][j - 1] + matchOrMismatch, // match/mismatch
                                        dp[i - 1][j] + gapPenalty // deletion (gap in s2)
                                        ),
                    dp[i][j - 1] + gapPenalty // insertion (gap in s1)
                );
            }
        }

        return dp[n][m];
    }
}
