package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * BeadSort cannot sort negative numbers, characters, or strings.
 * It only works for non-negative integers.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bead_sort">BeadSort Algorithm (Wikipedia)</a>
 */
public class BeadSort {
    /**
     * Sorts the given array using the BeadSort algorithm.
     *
     * @param array The array of non-negative integers to be sorted.
     * @return The sorted array.
     * @throws IllegalArgumentException If the array contains negative numbers.
     */
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        if (Arrays.stream(array).anyMatch(s -> s < 0)) {
            throw new IllegalArgumentException("BeadSort cannot sort negative numbers.");
        }

        int[] sorted = new int[array.length];
        int max = Arrays.stream(array).max().orElse(0);

        char[][] grid = new char[array.length][max];
        int[] count = new int[max];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < max; j++) {
                grid[i][j] = '-';
            }
        }

        for (int i = 0, arrayLength = array.length; i < arrayLength; i++) {
            int k = 0;
            for (int j = 0; j < array[i]; j++) {
                grid[count[max - k - 1]++][k] = '*';
                k++;
            }
        }

        for (int i = 0; i < array.length; i++) {
            int k = 0;
            for (int j = 0; j < max && grid[array.length - 1 - i][j] == '*'; j++) {
                k++;
            }
            sorted[i] = k;
        }
        return sorted;
    }
}
