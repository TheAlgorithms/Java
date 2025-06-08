package com.thealgorithms.searches;

/**
 * Boyer-Moore string search algorithm.
 * Efficient algorithm for substring search.
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
 */
public class BoyerMoore {

    private final int radix; // Radix (number of possible characters)
    private final int[] right; // Bad character rule table
    private final String pattern;

    public BoyerMoore(String pat) {
        this.pattern = pat;
        this.radix = 256;
        this.right = new int[radix];

        for (int c = 0; c < radix; c++) {
            right[c] = -1;
        }

        for (int j = 0; j < pat.length(); j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String text) {
        if (pattern.isEmpty()) {
            return 0;
        }

        int m = pattern.length();
        int n = text.length();

        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                char txtChar = text.charAt(i + j);
                char patChar = pattern.charAt(j);
                if (patChar != txtChar) {
                    skip = Math.max(1, j - right[txtChar]);
                    break;
                }
            }
            if (skip == 0) {
                return i; // Match found
            }
        }

        return -1; // No match
    }

    public static int staticSearch(String text, String pattern) {
        return new BoyerMoore(pattern).search(text);
    }
}
