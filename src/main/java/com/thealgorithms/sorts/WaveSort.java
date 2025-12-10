package com.thealgorithms.sorts;

/**
 * The WaveSort algorithm sorts an array so that every alternate element is greater than its adjacent elements.
 * This implementation also provides a method to check if an array is wave sorted.
 */
public class WaveSort implements SortAlgorithm {
    /**
     * Sorts the given array such that every alternate element is greater than its adjacent elements.
     *
     * @param array The array to be sorted.
     * @param <T> The type of elements in the array, which must be Comparable.
     * @return The sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 0; i < array.length; i += 2) {
            if (i > 0 && SortUtils.less(array[i], array[i - 1])) {
                SortUtils.swap(array, i, i - 1);
            }
            if (i < array.length - 1 && SortUtils.less(array[i], array[i + 1])) {
                SortUtils.swap(array, i, i + 1);
            }
        }
        return array;
    }

    /**
     * Checks if the given array is wave sorted. An array is wave sorted if every alternate element is greater than its adjacent elements.
     *
     * @param array The array to check.
     * @param <T> The type of elements in the array, which must be Comparable.
     * @return true if the array is wave sorted, false otherwise.
     */
    public <T extends Comparable<T>> boolean isWaveSorted(T[] array) {
        for (int i = 0; i < array.length; i += 2) {
            if (i > 0 && SortUtils.less(array[i], array[i - 1])) {
                return false;
            }
            if (i < array.length - 1 && SortUtils.less(array[i], array[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
