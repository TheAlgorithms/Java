package com.thealgorithms.sorts;

/**
 * @author Amir Hassan (https://github.com/ahsNT)
 * @see SortAlgorithm
 */
public class StoogeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }
        sort(array, 0, array.length);
        return array;
    }

    public <T extends Comparable<T>> T[] sort(final T[] array, final int start, final int end) {
        if (SortUtils.less(array[end - 1], array[start])) {
            final T temp = array[start];
            array[start] = array[end - 1];
            array[end - 1] = temp;
        }

        final int length = end - start;
        if (length > 2) {
            int third = length / 3;
            sort(array, start, end - third);
            sort(array, start + third, end);
            sort(array, start, end - third);
        }
        return array;
    }
}
