package com.thealgorithms.dynamicprogramming;

/**
 * Longest Palindromic Subsequence algorithm.
 * A palindromic subsequence is a subsequence that reads the same forwards and backwards.
 * This implementation finds the longest such subsequence by computing the LCS of the
 * original string and its reverse.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_palindromic_subsequence">Wikipedia</a>
 */
public final class LongestPalindromicSubsequence {
    private LongestPalindromicSubsequence() {
    }

    /**
     * Returns the longest palindromic subsequence of the given string.
     *
     * @param original the input string
     * @return the longest palindromic subsequence
     * @throws IllegalArgumentException if the input string is null
     */
    public static String lps(String original) {
        if (original == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }
        String reverse = new StringBuilder(original).reverse().toString();
        return recursiveLPS(original, reverse);
    }

    private static String recursiveLPS(String original, String reverse) {
        if (original.isEmpty() || reverse.isEmpty()) {
            return "";
        }
        if (original.charAt(original.length() - 1) == reverse.charAt(reverse.length() - 1)) {
            String bestSubResult = recursiveLPS(original.substring(0, original.length() - 1), reverse.substring(0, reverse.length() - 1));
            return reverse.charAt(reverse.length() - 1) + bestSubResult;
        }
        String sub1 = recursiveLPS(original, reverse.substring(0, reverse.length() - 1));
        String sub2 = recursiveLPS(original.substring(0, original.length() - 1), reverse);
        return sub1.length() >= sub2.length() ? sub1 : sub2;
    }
}
