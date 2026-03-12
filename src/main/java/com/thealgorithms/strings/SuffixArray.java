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
        int[] currentRank = new int[n];
        int[] nextRank = new int[n];

        // Initial ranking based on characters
        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
            currentRank[i] = text.charAt(i);
        }

        for (int prefixLength = 1; prefixLength < n; prefixLength *= 2) {
            sortSuffixArray(suffixArray, currentRank, prefixLength, n);

            // Re-rank
            nextRank[suffixArray[0]] = 0;
            for (int i = 1; i < n; i++) {
                int previousIndex = suffixArray[i - 1];
                int currentIndex = suffixArray[i];
                boolean sameRank = currentRank[previousIndex] == currentRank[currentIndex] && ((previousIndex + prefixLength < n ? currentRank[previousIndex + prefixLength] : -1) == (currentIndex + prefixLength < n ? currentRank[currentIndex + prefixLength] : -1));
                nextRank[currentIndex] = sameRank ? nextRank[previousIndex] : nextRank[previousIndex] + 1;
            }

            System.arraycopy(nextRank, 0, currentRank, 0, n);

            if (currentRank[suffixArray[n - 1]] == n - 1) {
                break;
            }
        }
        return Arrays.stream(suffixArray).mapToInt(Integer::intValue).toArray();
    }

    private static void sortSuffixArray(Integer[] suffixArray, int[] currentRank, int comparisonLength, int n) {
        // Comparator: first by rank, then by rank + comparisonLength
        Arrays.sort(suffixArray, (indexA, indexB) -> {
            if (currentRank[indexA] != currentRank[indexB]) {
                return Integer.compare(currentRank[indexA], currentRank[indexB]);
            }
            int rankAtOffsetA = (indexA + comparisonLength < n) ? currentRank[indexA + comparisonLength] : -1;
            int rankAtOffsetB = (indexB + comparisonLength < n) ? currentRank[indexB + comparisonLength] : -1;
            return Integer.compare(rankAtOffsetA, rankAtOffsetB);

        });
    }
}
