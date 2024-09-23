package com.thealgorithms.strings;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm
 */
public final class Manacher {

    // Private constructor to prevent instantiation
    private Manacher() {
    }

    /**
     * Test code for Manacher's Algorithm
     */
    public static void main(String[] args) {
        assert longestPalindrome("babad").equals("bab") || longestPalindrome("babad").equals("aba");
        assert longestPalindrome("cbbd").equals("bb");
        assert longestPalindrome("a").equals("a");
        assert longestPalindrome("ac").equals("a") || longestPalindrome("ac").equals("c");
    }

    /**
     * Finds the longest palindromic substring using Manacher's Algorithm
     *
     * @param s The input string
     * @return The longest palindromic substring in {@code s}
     */
    public static String longestPalindrome(String s) {
        // Preprocess the string to avoid even-length palindrome issues
        String processedString = preprocess(s);
        int n = processedString.length();
        int[] p = new int[n]; // Array to store the radius of palindromes

        // Separate variable declarations into individual statements
        int center = 0;
        int rightBoundary = 0;
        int maxLen = 0;
        int centerIndex = 0;

        // Iterate over the preprocessed string to calculate the palindrome radii
        for (int i = 1; i < n - 1; i++) {
            // Mirror the current index i to find its corresponding mirrored index
            int mirror = 2 * center - i;

            // If the current index is within the right boundary, mirror the palindrome radius
            if (i < rightBoundary) {
                p[i] = Math.min(rightBoundary - i, p[mirror]);
            }

            // Try to expand the palindrome centered at i
            while (processedString.charAt(i + 1 + p[i]) == processedString.charAt(i - 1 - p[i])) {
                p[i]++;
            }

            // Update center and right boundary if palindrome expands beyond current right boundary
            if (i + p[i] > rightBoundary) {
                center = i;
                rightBoundary = i + p[i];
            }

            // Track the maximum length and center index of the longest palindrome found so far
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        // Extract the longest palindrome from the original string
        int start = (centerIndex - maxLen) / 2; // Get the starting index in the original string
        return s.substring(start, start + maxLen);
    }

    /**
     * Preprocesses the input string by inserting a special character ('#') between each character
     * and adding '^' at the start and '$' at the end to avoid boundary conditions.
     *
     * @param s The original string
     * @return The preprocessed string with additional characters
     */
    private static String preprocess(String s) {
        if (s.isEmpty()) {
            return "^$";
        }
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append("#$");
        return sb.toString();
    }
}
