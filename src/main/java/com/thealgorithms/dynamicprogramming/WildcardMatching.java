/**
 *
 * Author: Janmesh Singh
 * Github: https://github.com/janmeshjs

 * Problem Statement: To determine if the pattern matches the text.
 * The pattern can include two special wildcard characters:
 *       ' ? ': Matches any single character.
 *       ' * ': Matches zero or more of any character sequence.
 *
 * Use DP to return True if the pattern matches the entire text and False otherwise
 *
 */

package com.thealgorithms.dynamicprogramming;

public final class WildcardMatching {
    private WildcardMatching() {
    }

    public static boolean isMatch(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();

        // Create a DP table to store intermediate results
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: an empty pattern matches an empty text
        dp[0][0] = true;

        // Handle patterns starting with '*'
        for (int j = 1; j <= n; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char textChar = text.charAt(i - 1);
                char patternChar = pattern.charAt(j - 1);

                if (patternChar == textChar || patternChar == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (patternChar == '*') {
                    // '*' can match zero or more characters
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        // The result is in the bottom-right cell of the DP table
        return dp[m][n];
    }
}
