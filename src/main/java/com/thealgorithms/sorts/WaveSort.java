package com.thealgorithms.sorts;

/**
 * The WaveSort algorithm sorts an array so that every alternate element is greater than its adjacent elements.
 */
public class WaveSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;

        for (int i = 0; i < n; i += 2) {
            if (i > 0 && SortUtils.less(array[i], array[i - 1])) {
                SortUtils.swap(array, i, i - 1);
            }

            if (i < n - 1 && SortUtils.less(array[i], array[i + 1])) {
                SortUtils.swap(array, i, i + 1);
            }
        }

        return array;
    }
}
