package com.thealgorithms.strings;

/**
 * Kasai's Algorithm for constructing the Longest Common Prefix (LCP) array.
 *
 * <p>
 * The LCP array stores the lengths of the longest common prefixes between
 * lexicographically adjacent suffixes of a string. Kasai's algorithm computes
 * this array in O(N) time given the string and its suffix array.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/LCP_array">LCP array - Wikipedia</a>
 */
public final class KasaiAlgorithm {

    private KasaiAlgorithm() {
    }

    /**
     * Computes the LCP array using Kasai's algorithm.
     *
     * @param text      the original string
     * @param suffixArr the suffix array of the string
     * @return the LCP array of length N, where LCP[i] is the length of the longest
     *         common prefix of the suffixes indexed by suffixArr[i] and suffixArr[i+1].
     *         The last element LCP[N-1] is always 0.
     * @throws IllegalArgumentException if text or suffixArr is null, or their lengths differ
     */
    public static int[] kasai(String text, int[] suffixArr) {
        if (text == null || suffixArr == null) {
            throw new IllegalArgumentException("Text and suffix array must not be null.");
        }
        int n = text.length();
        if (suffixArr.length != n) {
            throw new IllegalArgumentException("Suffix array length must match text length.");
        }
        if (n == 0) {
            return new int[0];
        }

        // Compute the inverse suffix array
        // invSuff[i] stores the index of the suffix text.substring(i) in the suffix array
        int[] invSuff = new int[n];
        for (int i = 0; i < n; i++) {
            if (suffixArr[i] < 0 || suffixArr[i] >= n) {
                throw new IllegalArgumentException("Suffix array contains out-of-bounds index.");
            }
            invSuff[suffixArr[i]] = i;
        }

        int[] lcp = new int[n];
        int k = 0; // Length of the longest common prefix

        for (int i = 0; i < n; i++) {
            // Suffix at index i has not a next suffix in suffix array
            int rank = invSuff[i];
            if (rank == n - 1) {
                k = 0;
                continue;
            }

            int nextSuffixIndex = suffixArr[rank + 1];

            // Directly match characters to find LCP
            while (i + k < n && nextSuffixIndex + k < n && text.charAt(i + k) == text.charAt(nextSuffixIndex + k)) {
                k++;
            }

            lcp[rank] = k;

            // Delete the starting character from the string
            if (k > 0) {
                k--;
            }
        }

        return lcp;
    }
}
