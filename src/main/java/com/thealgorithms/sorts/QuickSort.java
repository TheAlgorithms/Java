package com.thealgorithms.sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class QuickSort implements SortAlgorithm {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted Sorts the array in increasing order
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * The sorting process
     *
     * @param left The first index of an array
     * @param right The last index of an array
     * @param array The array to be sorted
     */
    private static <T extends Comparable<T>> void doSort(T[] array, final int left, final int right) {
        if (left < right) {
            final int pivot = randomPartition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    /**
     * Randomize the array to avoid the basically ordered sequences
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array
     * @return the partition index of the array
     */
    private static <T extends Comparable<T>> int randomPartition(T[] array, final int left, final int right) {
        final int randomIndex = left + (int) (Math.random() * (right - left + 1));
        SortUtils.swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array Finds the partition index of an
     * array
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        final int mid = (left + right) >>> 1;
        final T pivot = array[mid];

        while (left <= right) {
            while (SortUtils.less(array[left], pivot)) {
                ++left;
            }
            while (SortUtils.less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                SortUtils.swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }
}
