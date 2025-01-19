package com.thealgorithms.sorts;

/**
 * Dark Sort algorithm implementation.
 *
 * Dark Sort uses a temporary array to count occurrences of elements and
 * reconstructs the sorted array based on the counts.
 */
class DarkSort {

    /**
     * Sorts the array using the Dark Sort algorithm.
     *
     * @param unsorted the array to be sorted
     * @return sorted array
     */
    public Integer[] sort(Integer[] unsorted) {
        if (unsorted == null || unsorted.length <= 1) {
            return unsorted;
        }

        int max = findMax(unsorted); // Find the maximum value in the array

        // Create a temporary array for counting occurrences
        int[] temp = new int[max + 1];

        // Count occurrences of each element
        for (int value : unsorted) {
            temp[value]++;
        }

        // Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                unsorted[index++] = i;
                temp[i]--;
            }
        }

        return unsorted;
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
