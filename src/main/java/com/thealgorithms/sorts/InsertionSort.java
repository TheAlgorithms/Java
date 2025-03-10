package com.thealgorithms.sorts;

class InsertionSort implements SortAlgorithm {

    /**
     * Sorts the given array using the standard Insertion Sort algorithm.
     *
     * @param array The array to be sorted
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    /**
     * Sorts a subarray of the given array using the standard Insertion Sort algorithm.
     *
     * @param array The array to be sorted
     * @param lo    The starting index of the subarray
     * @param hi    The ending index of the subarray (exclusive)
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] array, final int lo, final int hi) {
        if (array == null || lo >= hi) {
            return array;
        }

        for (int i = lo + 1; i < hi; i++) {
            final T key = array[i];
            int j = i - 1;
            while (j >= lo && SortUtils.less(key, array[j])) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }

        return array;
    }

    /**
     * Sentinel sort is a function which on the first step finds the minimal element in the provided
     * array and puts it to the zero position, such a trick gives us an ability to avoid redundant
     * comparisons like `j > 0` and swaps (we can move elements on position right, until we find
     * the right position for the chosen element) on further step.
     *
     * @param array The array to be sorted
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The sorted array
     */
    public <T extends Comparable<T>> T[] sentinelSort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        final int minElemIndex = findMinIndex(array);
        SortUtils.swap(array, 0, minElemIndex);

        for (int i = 2; i < array.length; i++) {
            final T currentValue = array[i];
            int j = i;
            while (j > 0 && SortUtils.less(currentValue, array[j - 1])) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = currentValue;
        }

        return array;
    }

    /**
     * Finds the index of the minimum element in the array.
     *
     * @param array The array to be searched
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The index of the minimum element
     */
    private <T extends Comparable<T>> int findMinIndex(final T[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (SortUtils.less(array[i], array[minIndex])) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
