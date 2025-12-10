package com.thealgorithms.strings;

import java.util.Arrays;

/**
 * Suffix Array implementation in Java.
 * Builds an array of indices that represent all suffixes of a string in sorted order.
 * Wikipedia Reference: https://en.wikipedia.org/wiki/Suffix_array
 * Author: Nithin U.
 * Github: https://github.com/NithinU2802
 */

public final class SuffixArray {

    private SuffixArray() {
    }

    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] suffixArray = new Integer[n];
        int[] rank = new int[n];
        int[] tempRank = new int[n];

        // Initial ranking based on characters
        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
            rank[i] = text.charAt(i);
        }

        for (int k = 1; k < n; k *= 2) {
            final int step = k;

            // Comparator: first by rank, then by rank + step
            Arrays.sort(suffixArray, (a, b) -> {
                if (rank[a] != rank[b]) {
                    return Integer.compare(rank[a], rank[b]);
                }
                int ra = (a + step < n) ? rank[a + step] : -1;
                int rb = (b + step < n) ? rank[b + step] : -1;
                return Integer.compare(ra, rb);
            });

            // Re-rank
            tempRank[suffixArray[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = suffixArray[i - 1];
                int curr = suffixArray[i];
                boolean sameRank = rank[prev] == rank[curr] && ((prev + step < n ? rank[prev + step] : -1) == (curr + step < n ? rank[curr + step] : -1));
                tempRank[curr] = sameRank ? tempRank[prev] : tempRank[prev] + 1;
            }

            System.arraycopy(tempRank, 0, rank, 0, n);

            if (rank[suffixArray[n - 1]] == n - 1) {
                break;
            }
        }
        return Arrays.stream(suffixArray).mapToInt(Integer::intValue).toArray();
    }
}
