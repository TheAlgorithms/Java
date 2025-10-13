package com.thealgorithms.strings;

/**
 * Computes the Levenshtein distance (edit distance) between two strings.
 * Time: O(n*m), Space: O(min(n, m)) if optimized, here using full DP for simplicity.
 */
public final class LevenshteinDistance {
    private LevenshteinDistance() {}

    /**
     * Compute the edit distance between s and t.
     *
     * @param s first string (may be null)
     * @param t second string (may be null)
     * @return minimum number of single-character edits (insertions, deletions or substitutions)
     */
    public static int compute(final String s, final String t) {
        if (s == null) return t == null ? 0 : t.length();
        if (t == null) return s.length();
        final int n = s.length();
        final int m = t.length();

        if (n == 0) return m;
        if (m == 0) return n;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            char cs = s.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char ct = t.charAt(j - 1);
                int cost = (cs == ct) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1,    // deletion
                                              dp[i][j - 1] + 1),   // insertion
                                              dp[i - 1][j - 1] + cost); // substitution
            }
        }

        return dp[n][m];
    }
}
