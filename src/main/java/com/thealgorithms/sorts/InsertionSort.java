package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

import java.util.function.Function;

class InsertionSort implements SortAlgorithm {

    /**
     * Generic insertion sort algorithm in increasing order.
     *
     * @param array the array to be sorted.
     * @param <T>   the class of array.
     * @return sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    public <T extends Comparable<T>> T[] sort(T[] array, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(array[j], array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
        return array;
    }

    /**
     * Sentinel sort is a function which on the first step finds the minimal element in the provided
     * array and puts it to the zero position, such a trick gives us an ability to avoid redundant
     * comparisons like `j > 0` and swaps (we can move elements on position right, until we find
     * the right position for the chosen element) on further step.
     *
     * @param array the array to be sorted
     * @param <T>   Generic type which extends Comparable interface.
     * @return sorted array
     */
    public <T extends Comparable<T>> T[] sentinelSort(T[] array) {
        int minElemIndex = 0;
        int n = array.length;
        if (n < 1) return array;

        // put the smallest element to the 0 position as a sentinel, which will allow us to avoid
        // redundant comparisons like `j > 0` further
        for (int i = 1; i < n; i++)
            if (less(array[i], array[minElemIndex])) minElemIndex = i;
        swap(array, 0, minElemIndex);

        for (int i = 2; i < n; i++) {
            int j = i;
            T currentValue = array[i];
            while (less(currentValue, array[j - 1])) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = currentValue;
        }

        return array;
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        int size = 100_000;
        Double[] randomArray = SortUtilsRandomGenerator.generateArray(size);
        Double[] copyRandomArray = new Double[size];
        System.arraycopy(randomArray, 0, copyRandomArray, 0, size);

        InsertionSort insertionSort = new InsertionSort();
        double insertionTime = measureApproxExecTime(insertionSort::sort, randomArray);
        System.out.printf("Original insertion time: %5.2f  sec.\n", insertionTime);

        double insertionSentinelTime = measureApproxExecTime(insertionSort::sentinelSort, copyRandomArray);
        System.out.printf("Sentinel insertion time: %5.2f  sec.\n", insertionSentinelTime);

        // ~ 1.5 time sentinel sort is faster, then classical Insertion sort implementation.
        System.out.printf("Sentinel insertion is %f3.2 time faster than Original insertion sort\n", insertionTime / insertionSentinelTime);
    }

    private static double measureApproxExecTime(Function<Double[], Double[]> sortAlgorithm, Double[] randomArray) {
        long start = System.currentTimeMillis();
        sortAlgorithm.apply(randomArray);
        long end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }
}
