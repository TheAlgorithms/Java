package com.thealgorithms.sorts;

/**
 * QuickSort is a divide-and-conquer sorting algorithm.
 *
 * <p>The algorithm selects a pivot element and partitions the array into two
 * subarrays such that:
 * <ul>
 *   <li>Elements smaller than the pivot are placed on the left</li>
 *   <li>Elements greater than the pivot are placed on the right</li>
 * </ul>
 *
 * <p>The subarrays are then recursively sorted until the entire array is ordered.
 *
 * <p>This implementation uses randomization to reduce the probability of
 * encountering worst-case performance on already sorted inputs.
 *
 * <p>Time Complexity:
 * <ul>
 *   <li>Best Case: O(n log n)</li>
 *   <li>Average Case: O(n log n)</li>
 *   <li>Worst Case: O(n^2)</li>
 * </ul>
 *
 * <p>Space Complexity: O(log n) due to recursion stack (in-place sorting).
 *
 * @author Varun Upadhyay
 * @author Podshivalov Nikita
 * @see SortAlgorithm
 */

class QuickSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * The sorting process
     *
     * @param array The array to be sorted
     * @param left  The first index of an array
     * @param right The last index of an array
     */
    private static <T extends Comparable<T>> void doSort(T[] array, final int left, final int right) {
        // Continue sorting only if the subarray has more than one element
        if (left < right) {
            // Randomly choose a pivot and partition the array
            final int pivot = randomPartition(array, left, right);
            // Recursively sort elements before the pivot
            doSort(array, left, pivot - 1);
            // Recursively sort elements after the pivot
            doSort(array, pivot, right);
        }
    }

    /**
     * Randomizes the array to avoid already ordered or nearly ordered sequences
     *
     * @param array The array to be sorted
     * @param left  The first index of an array
     * @param right The last index of an array
     * @return the partition index of the array
     */
    private static <T extends Comparable<T>> int randomPartition(T[] array, final int left, final int right) {
        // Randomizing the pivot helps avoid worst-case performance
        // for already sorted or nearly sorted arrays
        final int randomIndex = left + (int) (Math.random() * (right - left + 1));
        SortUtils.swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param left  The first index of an array
     * @param right The last index of an array
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        final int mid = (left + right) >>> 1;
        // Choose the middle element as the pivot
        final T pivot = array[mid];
        // Move the left and right pointers towards each other
        while (left <= right) {
            // Move left pointer until an element >= pivot is found
            while (SortUtils.less(array[left], pivot)) {
                ++left;
            }

            // Move right pointer until an element <= pivot is found
            while (SortUtils.less(pivot, array[right])) {
                --right;
            }

            // Swap elements that are on the wrong side of the pivot
            if (left <= right) {
                SortUtils.swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }
}
