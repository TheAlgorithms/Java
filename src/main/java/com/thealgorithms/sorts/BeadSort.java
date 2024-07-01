package com.thealgorithms.sorts;

import java.util.Arrays;

public class BeadSort {
    private enum BeadState { BEAD, EMPTY }

    /**
     * Sorts the given array using the BeadSort algorithm.
     *
     * @param array The array of non-negative integers to be sorted.
     * @return The sorted array.
     * @throws IllegalArgumentException If the array contains negative numbers.
     */
    public int[] sort(int[] array) {
        allInputsMustBeNonNegative(array);
        return extractSortedFromGrid(fillGrid(array));
    }

    private void allInputsMustBeNonNegative(final int[] array) {
        if (Arrays.stream(array).anyMatch(s -> s < 0)) {
            throw new IllegalArgumentException("BeadSort cannot sort negative numbers.");
        }
    }

    private BeadState[][] fillGrid(final int[] array) {
        final var maxValue = Arrays.stream(array).max().orElse(0);
        var grid = getEmptyGrid(array.length, maxValue);

        int[] count = new int[maxValue];
        for (int i = 0, arrayLength = array.length; i < arrayLength; i++) {
            int k = 0;
            for (int j = 0; j < array[i]; j++) {
                grid[count[maxValue - k - 1]++][k] = BeadState.BEAD;
                k++;
            }
        }
        return grid;
    }

    private BeadState[][] getEmptyGrid(final int arrayLength, final int maxValue) {
        BeadState[][] grid = new BeadState[arrayLength][maxValue];
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < maxValue; j++) {
                grid[i][j] = BeadState.EMPTY;
            }
        }

        return grid;
    }

    private int[] extractSortedFromGrid(final BeadState[][] grid) {
        int[] sorted = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int k = 0;
            for (int j = 0; j < grid[grid.length - 1 - i].length && grid[grid.length - 1 - i][j] == BeadState.BEAD; j++) {
                k++;
            }
            sorted[i] = k;
        }
        return sorted;
    }
}
