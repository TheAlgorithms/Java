package com.thealgorithms.dynamicprogramming;

/**
 * Class for Regular Expression Matching using Dynamic Programming.
 */
public class RegularExpressionMatching {

    /**
     * Determines if the input string matches the given pattern.
     *
     * @param s the input string
     * @param p the pattern string (may contain '.' and '*')
     * @return true if the string matches the pattern, false otherwise
     */
    public boolean isMatch(final String s, final String p) {
        final Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private boolean dp(final int i, final int j, final String s, final String p, final Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        final boolean ans;
        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            final boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Two options:
                // 1. Skip the pattern with '*'
                // 2. Use '*' to match one character
                ans = dp(i, j + 2, s, p, memo) || (firstMatch && dp(i + 1, j, s, p, memo));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        memo[i][j] = ans;
        return ans;
    }
}