package com.thealgorithms.sorts;

/**
 * The idea of Swap-Sort is to count the number m of smaller values (that are in
 * A) from each element of an array A(1...n) and then swap the element with the
 * element in A(m+1). This ensures that the exchanged element is already in the
 * correct, i.e. final, position. The disadvantage of this algorithm is that
 * each element may only occur once, otherwise there is no termination.
 */
public class SwapSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int index = 0;

        while (index < array.length - 1) {
            final int amountSmallerElements = this.getSmallerElementCount(array, index);

            if (amountSmallerElements > 0) {
                SortUtils.swap(array, index, index + amountSmallerElements);
            } else {
                index++;
            }
        }

        return array;
    }

    private <T extends Comparable<T>> int getSmallerElementCount(final T[] array, final int index) {
        int counter = 0;
        for (int i = index + 1; i < array.length; i++) {
            if (SortUtils.less(array[i], array[index])) {
                counter++;
            }
        }

        return counter;
    }
}
