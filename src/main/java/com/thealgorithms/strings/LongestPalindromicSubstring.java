package com.thealgorithms.strings;

final class LongestPalindromicSubstring {
    private LongestPalindromicSubstring() {
    }

    /**
     * Finds the longest palindromic substring in the given string.
     *
     * @param s the input string
     * @return the longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String maxStr = "";
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j < s.length(); ++j) {
                if (isValid(s, i, j) && (j - i + 1 > maxStr.length())) {
                    maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }

    private static boolean isValid(String s, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = 0; i < n / 2; ++i) {
            if (s.charAt(lo + i) != s.charAt(hi - i)) {
                return false;
            }
        }
        return true;
    }
}
