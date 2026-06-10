package com.thealgorithms.strings;

/**
 * Longest Common Substring finds the longest string that is a 
 * contiguous substring of two input strings.
 * Example: "abcdef" and "zcdemf" → "cde"
 */
public final class LongestCommonSubstring {

    private LongestCommonSubstring() {
        // Utility class
    }

    /**
     * Finds the longest common substring of two strings.
     *
     * @param a First input string
     * @param b Second input string
     * @return The longest common substring, or empty string if none found
     */
    public static String longestCommonSubstring(final String a, final String b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        int maxLength = 0;
        int endIndex = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return a.substring(endIndex - maxLength, endIndex);
    }
}