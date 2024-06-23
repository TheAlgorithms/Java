package com.thealgorithms.sorts;

/**
 * The idea of Swap-Sort is to count the number m of smaller values (that are in
 * A) from each element of an array A(1...n) and then swap the element with the
 * element in A(m+1). This ensures that the exchanged element is already in the
 * correct, i.e. final, position. The disadvantage of this algorithm is that
 * each element may only occur once, otherwise there is no termination.
 */
public class SwapSort implements SortAlgorithm {

    /**
     * Sorts the input array using the swap sort algorithm.
     *
     * @param array the array to be sorted
     * @param <T>   the type of elements in the array, which must be Comparable
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (SortUtils.less(array[j], array[i])) {
                    SortUtils.swap(array, i, j);
                }
            }
        }

        return array;
    }
}
