package com.thealgorithms.sorts;

/**
 * Stalin Sort is a sorting algorithm that works by iterating through the array and 
 * maintaining a sorted portion of the array at the beginning. If a number is encountered 
 * that is smaller than the last number in the sorted portion, it is discarded (not added).
 * This algorithm is not stable and will not sort all elements correctly if there are 
 * elements that violate the sort order.
 * 
 * For more information, see: 
 * https://en.wikipedia.org/wiki/Stalin_sort
 */
public class StalinSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        T[] result = (T[]) new Comparable[array.length];
        int index = 0;
        result[index++] = array[0]; 

        for (int i = 1; i < array.length; i++) {
            if (SortUtils.less(result[index - 1], array[i])) {
                result[index++] = array[i];
            }
        }

        return trimArray(result, index);
    }

    private <T> T[] trimArray(T[] array, int length) {
        T[] trimmedArray = (T[]) new Comparable[length];
        System.arraycopy(array, 0, trimmedArray, 0, length);
        return trimmedArray;
    }
}
