package com.thealgorithms.sorts;

public class CircleSort implements SortAlgorithm {

    /* This method implements the circle sort
     * @param array The array to be sorted
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;
        if (n == 0) {
            return array;
        }
        while (doSort(array, 0, n - 1)) {
        }
        return array;
    }

    /* This method implements the cyclic sort recursive version
     * @param array The array to be sorted
     * @param the left boundary of the part currently being sorted
     * @param the right boundary of the part currently being sorted
     */
    private <T extends Comparable<T>> Boolean doSort(T[] array, int left, int right) {
        boolean swapped = false;

        if (left == right) {
            return Boolean.FALSE;
        }

        int low = left;
        int high = right;

        while (low < high) {
            if (array[low].compareTo(array[high]) > 0) {
                SortUtils.swap(array, low, high);
                swapped = true;
            }
            low++;
            high--;
        }

        if (low == high && array[low].compareTo(array[high + 1]) > 0) {
            SortUtils.swap(array, low, high + 1);
            swapped = true;
        }

        int mid = left + (right - left) / 2;
        Boolean leftHalf = doSort(array, left, mid);
        Boolean rightHalf = doSort(array, mid + 1, right);

        return swapped || leftHalf || rightHalf;
    }
}
