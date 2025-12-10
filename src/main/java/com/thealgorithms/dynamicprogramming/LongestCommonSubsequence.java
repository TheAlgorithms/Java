package com.thealgorithms.dynamicprogramming;

/**
 * This class implements the Longest Common Subsequence (LCS) problem.
 * The LCS of two sequences is the longest sequence that appears in both
 * sequences
 * in the same order, but not necessarily consecutively.
 *
 * This implementation uses dynamic programming to find the LCS of two strings.
 */
final class LongestCommonSubsequence {

    private LongestCommonSubsequence() {
    }

    /**
     * Returns the Longest Common Subsequence (LCS) of two given strings.
     *
     * @param str1 The first string.
     * @param str2 The second string.
     * @return The LCS of the two strings, or null if one of the strings is null.
     */
    public static String getLCS(String str1, String str2) {
        // If either string is null, return null as LCS can't be computed.
        if (str1 == null || str2 == null) {
            return null;
        }
        // If either string is empty, return an empty string as LCS.
        if (str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        // Convert the strings into arrays of characters
        String[] arr1 = str1.split("");
        String[] arr2 = str2.split("");

        // lcsMatrix[i][j] = LCS(first i characters of str1, first j characters of str2)
        int[][] lcsMatrix = new int[arr1.length + 1][arr2.length + 1];

        // Base Case: Fill the LCS matrix 0th row & 0th column with 0s
        // as LCS of any string with an empty string is 0.
        for (int i = 0; i < arr1.length + 1; i++) {
            lcsMatrix[i][0] = 0;
        }
        for (int j = 1; j < arr2.length + 1; j++) {
            lcsMatrix[0][j] = 0;
        }

        // Build the LCS matrix by comparing characters of str1 & str2
        for (int i = 1; i < arr1.length + 1; i++) {
            for (int j = 1; j < arr2.length + 1; j++) {
                // If characters match, the LCS increases by 1
                if (arr1[i - 1].equals(arr2[j - 1])) {
                    lcsMatrix[i][j] = lcsMatrix[i - 1][j - 1] + 1;
                } else {
                    // Otherwise, take the maximum of the left or above values
                    lcsMatrix[i][j] = Math.max(lcsMatrix[i - 1][j], lcsMatrix[i][j - 1]);
                }
            }
        }

        // Call helper function to reconstruct the LCS from the matrix
        return lcsString(str1, str2, lcsMatrix);
    }

    /**
     * Reconstructs the LCS string from the LCS matrix.
     *
     * @param str1      The first string.
     * @param str2      The second string.
     * @param lcsMatrix The matrix storing the lengths of LCSs
     *                  of substrings of str1 and str2.
     * @return The LCS string.
     */
    public static String lcsString(String str1, String str2, int[][] lcsMatrix) {
        StringBuilder lcs = new StringBuilder(); // Hold the LCS characters.
        int i = str1.length(); // Start from the end of str1.
        int j = str2.length(); // Start from the end of str2.

        // Trace back through the LCS matrix to reconstruct the LCS
        while (i > 0 && j > 0) {
            // If characters match, add to the LCS and move diagonally in the matrix
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (lcsMatrix[i - 1][j] > lcsMatrix[i][j - 1]) {
                // If the value above is larger, move up
                i--;
            } else {
                // If the value to the left is larger, move left
                j--;
            }
        }

        return lcs.reverse().toString(); // LCS built in reverse, so reverse it back
    }
}
