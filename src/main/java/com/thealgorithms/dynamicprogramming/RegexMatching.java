package com.thealgorithms.dynamicprogramming;

/**
 * Given a text and wildcard pattern implement a wildcard pattern matching
 * algorithm that finds if wildcard is matched with text. The matching should
 * cover the entire text ?-> matches single characters *-> match the sequence of
 * characters
 *
 * For calculation of Time and Space Complexity. Let N be length of src and M be length of pat
 *
 * Memoization vs Tabulation : https://www.geeksforgeeks.org/tabulation-vs-memoization/
 * Question Link : https://practice.geeksforgeeks.org/problems/wildcard-pattern-matching/1
 */
public final class RegexMatching {
    private RegexMatching() {
    }

    /**
     * Method 1: Determines if the given source string matches the given pattern using a recursive approach.
     * This method directly applies recursion to check if the source string matches the pattern, considering
     * the wildcards '?' and '*'.
     *
     * Time Complexity: O(2^(N+M)), where N is the length of the source string and M is the length of the pattern.
     * Space Complexity: O(N + M) due to the recursion stack.
     *
     * @param src The source string to be matched against the pattern.
     * @param pat The pattern containing wildcards ('*' matches a sequence of characters, '?' matches a single character).
     * @return {@code true} if the source string matches the pattern, {@code false} otherwise.
     */
    public static boolean regexRecursion(String src, String pat) {
        if (src.length() == 0 && pat.length() == 0) {
            return true;
        }
        if (src.length() != 0 && pat.length() == 0) {
            return false;
        }
        if (src.length() == 0 && pat.length() != 0) {
            for (int i = 0; i < pat.length(); i++) {
                if (pat.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        char chs = src.charAt(0);
        char chp = pat.charAt(0);

        String ros = src.substring(1);
        String rop = pat.substring(1);

        boolean ans;
        if (chs == chp || chp == '?') {
            ans = regexRecursion(ros, rop);
        } else if (chp == '*') {
            boolean blank = regexRecursion(src, rop);
            boolean multiple = regexRecursion(ros, pat);
            ans = blank || multiple;
        } else {
            ans = false;
        }
        return ans;
    }

    /**
     * Method 2: Determines if the given source string matches the given pattern using recursion.
     * This method utilizes a virtual index for both the source string and the pattern to manage the recursion.
     *
     * Time Complexity: O(2^(N+M)) where N is the length of the source string and M is the length of the pattern.
     * Space Complexity: O(N + M) due to the recursion stack.
     *
     * @param src The source string to be matched against the pattern.
     * @param pat The pattern containing wildcards ('*' matches a sequence of characters, '?' matches a single character).
     * @param svidx The current index in the source string.
     * @param pvidx The current index in the pattern.
     * @return {@code true} if the source string matches the pattern, {@code false} otherwise.
     */
    static boolean regexRecursion(String src, String pat, int svidx, int pvidx) {
        if (src.length() == svidx && pat.length() == pvidx) {
            return true;
        }
        if (src.length() != svidx && pat.length() == pvidx) {
            return false;
        }
        if (src.length() == svidx && pat.length() != pvidx) {
            for (int i = pvidx; i < pat.length(); i++) {
                if (pat.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        char chs = src.charAt(svidx);
        char chp = pat.charAt(pvidx);

        boolean ans;
        if (chs == chp || chp == '?') {
            ans = regexRecursion(src, pat, svidx + 1, pvidx + 1);
        } else if (chp == '*') {
            boolean blank = regexRecursion(src, pat, svidx, pvidx + 1);
            boolean multiple = regexRecursion(src, pat, svidx + 1, pvidx);
            ans = blank || multiple;
        } else {
            ans = false;
        }
        return ans;
    }

    /**
     * Method 3: Determines if the given source string matches the given pattern using top-down dynamic programming (memoization).
     * This method utilizes memoization to store intermediate results, reducing redundant computations and improving efficiency.
     *
     * Time Complexity: O(N * M), where N is the length of the source string and M is the length of the pattern.
     * Space Complexity: O(N * M) for the memoization table, plus additional space for the recursion stack.
     *
     * @param src The source string to be matched against the pattern.
     * @param pat The pattern containing wildcards ('*' matches a sequence of characters, '?' matches a single character).
     * @param svidx The current index in the source string.
     * @param pvidx The current index in the pattern.
     * @param strg A 2D array used for memoization to store the results of subproblems.
     * @return {@code true} if the source string matches the pattern, {@code false} otherwise.
     */
    public static boolean regexRecursion(String src, String pat, int svidx, int pvidx, int[][] strg) {
        if (src.length() == svidx && pat.length() == pvidx) {
            return true;
        }
        if (src.length() != svidx && pat.length() == pvidx) {
            return false;
        }
        if (src.length() == svidx && pat.length() != pvidx) {
            for (int i = pvidx; i < pat.length(); i++) {
                if (pat.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (strg[svidx][pvidx] != 0) {
            return strg[svidx][pvidx] != 1;
        }
        char chs = src.charAt(svidx);
        char chp = pat.charAt(pvidx);

        boolean ans;
        if (chs == chp || chp == '?') {
            ans = regexRecursion(src, pat, svidx + 1, pvidx + 1, strg);
        } else if (chp == '*') {
            boolean blank = regexRecursion(src, pat, svidx, pvidx + 1, strg);
            boolean multiple = regexRecursion(src, pat, svidx + 1, pvidx, strg);
            ans = blank || multiple;
        } else {
            ans = false;
        }
        strg[svidx][pvidx] = ans ? 2 : 1;
        return ans;
    }

    /**
     * Method 4: Determines if the given source string matches the given pattern using bottom-up dynamic programming (tabulation).
     * This method builds a solution iteratively by filling out a table, where each cell represents whether a substring
     * of the source string matches a substring of the pattern.
     *
     * Time Complexity: O(N * M), where N is the length of the source string and M is the length of the pattern.
     * Space Complexity: O(N * M) for the table used in the tabulation process.
     *
     * @param src The source string to be matched against the pattern.
     * @param pat The pattern containing wildcards ('*' matches a sequence of characters, '?' matches a single character).
     * @return {@code true} if the source string matches the pattern, {@code false} otherwise.
     */
    static boolean regexBU(String src, String pat) {
        boolean[][] strg = new boolean[src.length() + 1][pat.length() + 1];
        strg[src.length()][pat.length()] = true;
        for (int row = src.length(); row >= 0; row--) {
            for (int col = pat.length() - 1; col >= 0; col--) {
                if (row == src.length()) {
                    if (pat.charAt(col) == '*') {
                        strg[row][col] = strg[row][col + 1];
                    } else {
                        strg[row][col] = false;
                    }
                } else {
                    char chs = src.charAt(row);
                    char chp = pat.charAt(col);

                    boolean ans;
                    if (chs == chp || chp == '?') {
                        ans = strg[row + 1][col + 1];
                    } else if (chp == '*') {
                        boolean blank = strg[row][col + 1];
                        boolean multiple = strg[row + 1][col];
                        ans = blank || multiple;
                    } else {
                        ans = false;
                    }
                    strg[row][col] = ans;
                }
            }
        }
        return strg[0][0];
    }
}
