package com.thealgorithms.dynamicprogramming;

/**
 * A class that provides a solution to the abbreviation problem.
 *
 * Problem: Given two strings, `a` and `b`, determine if string `a` can be
 * transformed into string `b` by performing the following operations:
 * 1. Capitalize zero or more of `a`'s lowercase letters (i.e., convert them to uppercase).
 * 2. Delete any of the remaining lowercase letters from `a`.
 *
 * The task is to determine whether it is possible to make string `a` equal to string `b`.
 *
 * @author Hardvan
 */
public final class Abbreviation {
    private Abbreviation() {
    }

    /**
     * Determines if string `a` can be transformed into string `b` by capitalizing
     * some of its lowercase letters and deleting the rest.
     *
     * @param a The input string which may contain both uppercase and lowercase letters.
     * @param b The target string containing only uppercase letters.
     * @return {@code true} if string `a` can be transformed into string `b`,
     *         {@code false} otherwise.
     *
     * Time Complexity: O(n * m) where n = length of string `a` and m = length of string `b`.
     * Space Complexity: O(n * m) due to the dynamic programming table.
     */
    public static boolean abbr(String a, String b) {
        int n = a.length();
        int m = b.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j]) {
                    // Case 1: If the current characters match (or can be capitalized to match)
                    if (j < m && Character.toUpperCase(a.charAt(i)) == b.charAt(j)) {
                        dp[i + 1][j + 1] = true;
                    }
                    // Case 2: If the character in `a` is lowercase, we can skip it
                    if (Character.isLowerCase(a.charAt(i))) {
                        dp[i + 1][j] = true;
                    }
                }
            }
        }

        return dp[n][m];
    }
}
