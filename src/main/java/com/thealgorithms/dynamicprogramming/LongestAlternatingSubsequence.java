package com.thealgorithms.dynamicprogramming;

/**
 * Class for finding the length of the longest alternating subsequence in an array.
 *
 * <p>An alternating sequence is a sequence of numbers where the elements alternate
 * between increasing and decreasing. Specifically, a sequence is alternating if its elements
 * satisfy one of the following relations:
 *
 * <ul>
 *   <li>{@code x1 < x2 > x3 < x4 > x5 < ... < xn}</li>
 *   <li>{@code x1 > x2 < x3 > x4 < x5 > ... > xn}</li>
 * </ul>
 *
 * <p>This class provides a method to compute the length of the longest such subsequence
 * from a given array of integers.
 */
public final class LongestAlternatingSubsequence {
    private LongestAlternatingSubsequence() {
    }

    /**
     * Finds the length of the longest alternating subsequence in the given array.
     *
     * @param arr an array of integers where the longest alternating subsequence is to be found
     * @param n the length of the array {@code arr}
     * @return the length of the longest alternating subsequence
     *
     * <p>The method uses dynamic programming to solve the problem. It maintains a 2D array
     * {@code las} where:
     * <ul>
     *   <li>{@code las[i][0]} represents the length of the longest alternating subsequence
     *   ending at index {@code i} with the last element being greater than the previous element.</li>
     *   <li>{@code las[i][1]} represents the length of the longest alternating subsequence
     *   ending at index {@code i} with the last element being smaller than the previous element.</li>
     * </ul>
     *
     * <p>The method iterates through the array and updates the {@code las} array based on
     * whether the current element is greater or smaller than the previous elements.
     * The result is the maximum value found in the {@code las} array.
     */
    static int alternatingLength(int[] arr, int n) {
        int[][] las = new int[n][2]; // las = LongestAlternatingSubsequence

        // Initialize the dp array
        for (int i = 0; i < n; i++) {
            las[i][0] = 1;
            las[i][1] = 1;
        }

        int result = 1; // Initialize result

        // Compute values in a bottom-up manner
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If arr[i] is greater than arr[j], update las[i][0]
                if (arr[j] < arr[i] && las[i][0] < las[j][1] + 1) {
                    las[i][0] = las[j][1] + 1;
                }

                // If arr[i] is smaller than arr[j], update las[i][1]
                if (arr[j] > arr[i] && las[i][1] < las[j][0] + 1) {
                    las[i][1] = las[j][0] + 1;
                }
            }

            // Pick the maximum of both values at index i
            result = Math.max(result, Math.max(las[i][0], las[i][1]));
        }

        return result;
    }
}
