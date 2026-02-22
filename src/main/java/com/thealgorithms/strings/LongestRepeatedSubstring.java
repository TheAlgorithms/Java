package com.thealgorithms.strings;

/**
 * Finds the longest substring that occurs at least twice in a given string.
 *
 * <p>Uses the suffix array (via {@link SuffixArray}) and Kasai's algorithm
 * to build the LCP (Longest Common Prefix) array, then returns the substring
 * corresponding to the maximum LCP value.</p>
 *
 * <p>Time complexity: O(n log² n) for suffix array construction + O(n) for LCP.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_repeated_substring_problem">Longest repeated substring problem</a>
 * @see SuffixArray
 */
public final class LongestRepeatedSubstring {

    private LongestRepeatedSubstring() {
    }

    /**
     * Returns the longest substring that appears at least twice in the given text.
     *
     * @param text the input string
     * @return the longest repeated substring, or an empty string if none exists
     */
    public static String longestRepeatedSubstring(String text) {
        if (text == null || text.length() <= 1) {
            return "";
        }

        final int[] suffixArray = SuffixArray.buildSuffixArray(text);
        final int[] lcp = buildLcpArray(text, suffixArray);

        int maxLen = 0;
        int maxIdx = 0;
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > maxLen) {
                maxLen = lcp[i];
                maxIdx = suffixArray[i + 1];
            }
        }

        return text.substring(maxIdx, maxIdx + maxLen);
    }

    /**
     * Builds the LCP (Longest Common Prefix) array using Kasai's algorithm.
     *
     * <p>LCP[i] is the length of the longest common prefix between the suffixes
     * at positions suffixArray[i] and suffixArray[i+1] in sorted order.</p>
     *
     * @param text the original string
     * @param suffixArray the suffix array of the string
     * @return the LCP array of length n-1
     */
    static int[] buildLcpArray(String text, int[] suffixArray) {
        final int n = text.length();
        final int[] rank = new int[n];
        final int[] lcp = new int[n - 1];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }
            final int j = suffixArray[rank[i] + 1];
            while (i + k < n && j + k < n && text.charAt(i + k) == text.charAt(j + k)) {
                k++;
            }
            lcp[rank[i]] = k;
            if (k > 0) {
                k--;
            }
        }

        return lcp;
    }
}
