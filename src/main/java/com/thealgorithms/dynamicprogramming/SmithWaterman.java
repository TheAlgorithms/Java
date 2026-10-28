package com.thealgorithms.dynamicprogramming;

/**
 * Smith–Waterman algorithm for local sequence alignment.
 * Finds the highest scoring local alignment between substrings of two sequences.
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 */
public final class SmithWaterman {

    private SmithWaterman() {
        // Utility Class
    }

    /**
     * Computes the Smith–Waterman local alignment score between two strings.
     *
     * @param s1 first string
     * @param s2 second string
     * @param matchScore score for a match
     * @param mismatchPenalty penalty for mismatch (negative)
     * @param gapPenalty penalty for insertion/deletion (negative)
     * @return the maximum local alignment score
     */
    public static int align(String s1, String s2, int matchScore, int mismatchPenalty, int gapPenalty) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings must not be null.");
        }

        int n = s1.length();
        int m = s2.length();
        int maxScore = 0;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matchOrMismatch = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? matchScore : mismatchPenalty;

                dp[i][j] = Math.max(0,
                    Math.max(Math.max(dp[i - 1][j - 1] + matchOrMismatch, // match/mismatch
                                 dp[i - 1][j] + gapPenalty // deletion
                                 ),
                        dp[i][j - 1] + gapPenalty // insertion
                        ));

                if (dp[i][j] > maxScore) {
                    maxScore = dp[i][j];
                }
            }
        }

        return maxScore;
    }
}
