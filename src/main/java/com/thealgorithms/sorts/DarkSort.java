package com.thealgorithms.sorts;

/**
 * Dark Sort algorithm implementation.
 *
 * Dark Sort uses a temporary array to count occurrences of elements and
 * reconstructs the sorted array based on the counts.
 *
 * @see SortAlgorithm
 */
class DarkSort implements SortAlgorithm {

    /**
     * Sorts the array using the Dark Sort algorithm.
     *
     * @param unsorted the array to be sorted
     * @param <T> Comparable class
     * @return sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if (unsorted == null || unsorted.length <= 1) {
            return unsorted;
        }

        // Dark Sort works only for integers, so we cast and check
        if (!(unsorted instanceof Integer[])) {
            throw new IllegalArgumentException("Dark Sort only supports Integer arrays.");
        }

        Integer[] arr = (Integer[]) unsorted;
        int max = findMax(arr); // Find the maximum value in the array

        // Create a temporary array for counting occurrences
        int[] temp = new int[max + 1];

        // Count occurrences of each element
        for (int value : arr) {
            temp[value]++;
        }

        // Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                arr[index++] = i;
                temp[i]--;
            }
        }

        return (T[]) arr;
    }

    /**
     * Helper method to find the maximum value in an array.
     *
     * @param arr the array
     * @return the maximum value
     */
    private int findMax(Integer[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
