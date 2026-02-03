package com.thealgorithms.strings;

/**
 * Longest Palindromic Substring (consolidated implementation).
 *
 * <p>Resolves duplicate implementations: single class providing both a brute-force and a
 * dynamic-programming solution. Previously duplicated in strings and dynamicprogramming packages.
 *
 * <p>A palindromic substring reads the same backward as forward. This class offers:
 * <ul>
 *   <li>{@link #longestPalindrome(String)} – brute-force check of all substrings (O(n^2) substrings, O(n) each).</li>
 *   <li>{@link #lps(String)} – DP table over gap length (O(n^2) time and space).</li>
 * </ul>
 */
public final class LongestPalindromicSubstring {
    private LongestPalindromicSubstring() {
    }

    /**
     * Finds the longest palindromic substring using brute force (check every substring).
     *
     * @param s the input string
     * @return the longest palindromic substring, or "" if null or empty
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String maxStr = "";
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j < s.length(); ++j) {
                if (isPalindrome(s, i, j) && (j - i + 1 > maxStr.length())) {
                    maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }

    /**
     * Finds the longest palindromic substring using dynamic programming.
     * Fills a table for all substring lengths; returns the longest palindrome found.
     *
     * @param input the input string
     * @return the longest palindromic substring, or the input if null/empty
     */
    public static String lps(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        int n = input.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int end = 0;

        // g = gap (length of substring - 1); i, j = left and right indices
        for (int g = 0; g < n; g++) {
            for (int i = 0, j = g; j < n; i++, j++) {
                if (g == 0) {
                    dp[i][j] = true;
                } else if (g == 1) {
                    dp[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    dp[i][j] = input.charAt(i) == input.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    start = i;
                    end = j;
                }
            }
        }
        return input.substring(start, end + 1);
    }

    private static boolean isPalindrome(String s, int lo, int hi) {
        int len = hi - lo + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(lo + i) != s.charAt(hi - i)) {
                return false;
            }
        }
        return true;
    }
}
