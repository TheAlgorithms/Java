package com.thealgorithms.sorts;

/**
 * The WaveSort algorithm sorts an array so that every alternate element is greater than its adjacent elements.
 */
public class WaveSort implements SortAlgorithm {
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
