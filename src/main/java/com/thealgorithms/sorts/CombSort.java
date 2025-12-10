package com.thealgorithms.sorts;

/**
 * Comb Sort algorithm implementation
 *
 * <p>
 * Best-case performance O(n * log(n)) Worst-case performance O(n ^ 2)
 * Worst-case space complexity O(1)
 *
 * <p>
 * Comb sort improves on bubble sort.
 *
 * @author Sandeep Roy (https://github.com/sandeeproy99)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see BubbleSort
 * @see SortAlgorithm
 */
class CombSort implements SortAlgorithm {
    private static final double SHRINK_FACTOR = 1.3;

    /**
     * Method to find the next gap
     *
     * @param gap the current gap
     * @return the next gap value
     */
    private int getNextGap(int gap) {
        gap = (int) (gap / SHRINK_FACTOR);
        return Math.max(gap, 1);
    }

    /**
     * Method to sort the array using CombSort
     *
     * @param arr the array to be sorted
     * @param <T> the type of elements in the array
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int gap = arr.length;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = getNextGap(gap);
            swapped = performSwaps(arr, gap);
        }

        return arr;
    }

    /**
     * Method to perform the swapping of elements in the array based on the current gap
     *
     * @param arr the array to be sorted
     * @param gap the current gap
     * @param <T> the type of elements in the array
     * @return true if a swap occurred, false otherwise
     */
    private <T extends Comparable<T>> boolean performSwaps(final T[] arr, final int gap) {
        boolean swapped = false;

        for (int i = 0; i < arr.length - gap; i++) {
            if (SortUtils.less(arr[i + gap], arr[i])) {
                SortUtils.swap(arr, i, i + gap);
                swapped = true;
            }
        }

        return swapped;
    }
}
