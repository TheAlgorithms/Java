package com.thealgorithms.sorts;

public class CircleSort implements SortAlgorithm {

    /* This method implements the circle sort
     * @param array The array to be sorted
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }
        while (doSort(array, 0, array.length - 1)) {
        }
        return array;
    }

    /**
     * Recursively sorts the array in a circular manner by comparing elements
     * from the start and end of the current segment.
     *
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array to be sorted
     * @param left  The left boundary of the current segment being sorted
     * @param right The right boundary of the current segment being sorted
     * @return true if any elements were swapped during the sort; false otherwise
     */
    private <T extends Comparable<T>> boolean doSort(final T[] array, final int left, final int right) {
        boolean swapped = false;

        if (left == right) {
            return false;
        }

        int low = left;
        int high = right;

        while (low < high) {
            if (SortUtils.greater(array[low], array[high])) {
                SortUtils.swap(array, low, high);
                swapped = true;
            }
            low++;
            high--;
        }

        if (low == high && SortUtils.greater(array[low], array[high + 1])) {
            SortUtils.swap(array, low, high + 1);
            swapped = true;
        }

        final int mid = left + (right - left) / 2;
        final boolean leftHalfSwapped = doSort(array, left, mid);
        final boolean rightHalfSwapped = doSort(array, mid + 1, right);

        return swapped || leftHalfSwapped || rightHalfSwapped;
    }
}
