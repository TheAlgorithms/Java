package com.thealgorithms.others;
/*
 * Manacher's algorithm is used to find the longest palindromic
 * substring in a given string with a time complexity of O(n),
 * where n is the length of the input string.
 * Manacher's algorithm is an efficient method for solving
 * this problem in linear time complexity, making it faster
 * than other brute-force approaches.
 * It uses a combination of dynamic programming and clever
 * techniques to identify palindromes within the string and
 * extend them efficiently.
 * Refer wikipedia about Manacher's Algorithm:
 * https://en.wikipedia.org/wiki/Longest_palindromic_substring
 */

public class ManachersAlgorithm {
    public static final int SIZE = 100000 + 1;
    public static int[] P = new int[SIZE * 2];

    // Transform S into new string Q with special characters inserted to avoid bound checking.
    public static String convertToNewString(String s) {
        StringBuilder newString = new StringBuilder("@");

        for (int i = 0; i < s.length(); i++) {
            newString.append("#").append(s.charAt(i));
        }

        newString.append("#$");
        return newString.toString();
    }

    public static String longestPalindromeSubstring(String s) {
        String Q = convertToNewString(s);
        int c = 0, r = 0; // current center, right limit

        for (int i = 1; i < Q.length() - 1; i++) {
            // find the corresponding letter in the palindrome substring
            int iMirror = c - (i - c);

            if (r > i) {
                P[i] = Math.min(r - i, P[iMirror]);
            }

            // expanding around center i
            while (Q.charAt(i + 1 + P[i]) == Q.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // Update c,r in case if the palindrome centered at i expands past r
            if (i + P[i] > r) {
                c = i; // next center = i
                r = i + P[i];
            }
        }

        // Find the longest palindrome length in p
        int maxPalindrome = 0;
        int centerIndex = 0;

        for (int i = 1; i < Q.length() - 1; i++) {
            if (P[i] > maxPalindrome) {
                maxPalindrome = P[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex - 1 - maxPalindrome) / 2, (centerIndex - 1 - maxPalindrome) / 2 + maxPalindrome);
    }
}
