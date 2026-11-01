package com.thealgorithms.searches;

import java.util.HashMap;
import java.util.Map;

/**
 * Boyer-Moore string search algorithm.
 * Efficient algorithm for substring search.
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
 */
public class BoyerMoore {

    private final Map<Character, Integer> right; // Bad character rule table: last index of each pattern character
    private final String pattern;

    public BoyerMoore(String pat) {
        this.pattern = pat;
        // A map instead of a 256-entry array, so characters outside extended ASCII work too
        this.right = new HashMap<>();

        for (int j = 0; j < pat.length(); j++) {
            right.put(pat.charAt(j), j);
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
                    skip = Math.max(1, j - right.getOrDefault(txtChar, -1));
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
