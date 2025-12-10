package com.thealgorithms.dynamicprogramming;

/**
 * Class for finding the longest palindromic substring within a given string.
 * <p>
 * A palindromic substring is a sequence of characters that reads the same backward as forward.
 * This class uses a dynamic programming approach to efficiently find the longest palindromic substring.
 *
 */
public final class LongestPalindromicSubstring {
    private LongestPalindromicSubstring() {
    }

    public static String lps(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        boolean[][] arr = new boolean[input.length()][input.length()];
        int start = 0;
        int end = 0;
        for (int g = 0; g < input.length(); g++) {
            for (int i = 0, j = g; j < input.length(); i++, j++) {
                if (g == 0) {
                    arr[i][j] = true;
                } else if (g == 1) {
                    arr[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    arr[i][j] = input.charAt(i) == input.charAt(j) && arr[i + 1][j - 1];
                }

                if (arr[i][j]) {
                    start = i;
                    end = j;
                }
            }
        }
        return input.substring(start, end + 1);
    }
}
