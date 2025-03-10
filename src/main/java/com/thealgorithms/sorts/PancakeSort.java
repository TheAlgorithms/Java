package com.thealgorithms.sorts;

/**
 * Implementation of pancake sort
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 */
public class PancakeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length < 2) {
            return array;
        }

        for (int currentSize = 0; currentSize < array.length; currentSize++) {
            int maxIndex = findMaxIndex(array, currentSize);
            SortUtils.flip(array, maxIndex, array.length - 1 - currentSize);
        }

        return array;
    }

    /**
     * Finds the index of the maximum element in the array up to the given size.
     *
     * @param array      the array to be searched
     * @param currentSize the current size of the unsorted portion of the array
     * @param <T>        the type of elements in the array
     * @return the index of the maximum element
     */
    private <T extends Comparable<T>> int findMaxIndex(T[] array, int currentSize) {
        T max = array[0];
        int maxIndex = 0;
        for (int i = 0; i < array.length - currentSize; i++) {
            if (SortUtils.less(max, array[i])) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
