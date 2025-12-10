package com.thealgorithms.dynamicprogramming;

import java.util.stream.IntStream;

/**
 * Provides functions to calculate the Levenshtein distance between two strings.
 *
 * The Levenshtein distance is a measure of the similarity between two strings by calculating the minimum number of single-character
 * edits (insertions, deletions, or substitutions) required to change one string into the other.
 */
public final class LevenshteinDistance {
    private LevenshteinDistance() {
    }

    /**
     * Calculates the Levenshtein distance between two strings using a naive dynamic programming approach.
     *
     * This function computes the Levenshtein distance by constructing a dynamic programming matrix and iteratively filling it in.
     * It follows the standard top-to-bottom, left-to-right approach for filling in the matrix.
     *
     * @param string1 The first string.
     * @param string2 The second string.
     * @return The Levenshtein distance between the two input strings.
     *
     * Time complexity: O(nm),
     * Space complexity: O(nm),
     *
     * where n and m are lengths of `string1` and `string2`.
     *
     * Note that this implementation uses a straightforward dynamic programming approach without any space optimization.
     * It may consume more memory for larger input strings compared to the optimized version.
     */
    public static int naiveLevenshteinDistance(final String string1, final String string2) {
        int[][] distanceMatrix = IntStream.rangeClosed(0, string1.length()).mapToObj(i -> IntStream.rangeClosed(0, string2.length()).map(j -> (i == 0) ? j : (j == 0) ? i : 0).toArray()).toArray(int[][] ::new);

        IntStream.range(1, string1.length() + 1).forEach(i -> IntStream.range(1, string2.length() + 1).forEach(j -> {
            final int cost = (string1.charAt(i - 1) == string2.charAt(j - 1)) ? 0 : 1;
            distanceMatrix[i][j] = Math.min(distanceMatrix[i - 1][j - 1] + cost, Math.min(distanceMatrix[i][j - 1] + 1, distanceMatrix[i - 1][j] + 1));
        }));

        return distanceMatrix[string1.length()][string2.length()];
    }

    /**
     * Calculates the Levenshtein distance between two strings using an optimized dynamic programming approach.
     *
     * This edit distance is defined as 1 point per insertion, substitution, or deletion required to make the strings equal.
     *
     * @param string1 The first string.
     * @param string2 The second string.
     * @return The Levenshtein distance between the two input strings.
     *
     * Time complexity: O(nm),
     * Space complexity: O(n),
     *
     * where n and m are lengths of `string1` and `string2`.
     *
     * Note that this implementation utilizes an optimized dynamic programming approach, significantly reducing the space complexity from O(nm) to O(n), where n and m are the lengths of `string1` and `string2`.
     *
     * Additionally, it minimizes space usage by leveraging the shortest string horizontally and the longest string vertically in the computation matrix.
     */
    public static int optimizedLevenshteinDistance(final String string1, final String string2) {
        if (string1.isEmpty()) {
            return string2.length();
        }

        int[] previousDistance = IntStream.rangeClosed(0, string1.length()).toArray();

        for (int j = 1; j <= string2.length(); j++) {
            int prevSubstitutionCost = previousDistance[0];
            previousDistance[0] = j;

            for (int i = 1; i <= string1.length(); i++) {
                final int deletionCost = previousDistance[i] + 1;
                final int insertionCost = previousDistance[i - 1] + 1;
                final int substitutionCost = (string1.charAt(i - 1) == string2.charAt(j - 1)) ? prevSubstitutionCost : prevSubstitutionCost + 1;
                prevSubstitutionCost = previousDistance[i];
                previousDistance[i] = Math.min(deletionCost, Math.min(insertionCost, substitutionCost));
            }
        }

        return previousDistance[string1.length()];
    }
}
