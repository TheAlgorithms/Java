package com.thealgorithms.sorts;

/**
 * American Flag Sort Algorithm Implementation
 * This implementation uses a bucket-based approach that works with all Comparable types
 *
 * @see <a href="https://en.wikipedia.org/wiki/American_flag_sort">American Flag Sort Algorithm</a>
 */
public class AmericanFlagSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        
        // For generic comparable types, use a simplified bucket sort approach
        bucketSort(array, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void bucketSort(T[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        // Find min and max values for partitioning
        T min = array[start];
        T max = array[start];
        
        for (int i = start + 1; i <= end; i++) {
            if (SortUtils.less(array[i], min)) {
                min = array[i];
            }
            if (SortUtils.greater(array[i], max)) {
                max = array[i];
            }
        }

        // If all elements are equal, no need to sort
        if (min.compareTo(max) == 0) {
            return;
        }

        // Use a 3-way partitioning approach similar to American Flag Sort
        int length = end - start + 1;
        if (length < 10) {
            // For small arrays, use insertion sort
            insertionSort(array, start, end);
            return;
        }

        // Partition into buckets based on comparison with pivot
        T pivot = array[start + length / 2];
        
        int lt = start;  // less than pivot
        int gt = end;    // greater than pivot
        int i = start;   // current element
        
        while (i <= gt) {
            int cmp = array[i].compareTo(pivot);
            if (cmp < 0) {
                SortUtils.swap(array, lt++, i++);
            } else if (cmp > 0) {
                SortUtils.swap(array, i, gt--);
            } else {
                i++;
            }
        }
        
        // Recursively sort the partitions
        bucketSort(array, start, lt - 1);
        bucketSort(array, gt + 1, end);
    }

    private <T extends Comparable<T>> void insertionSort(T[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T key = array[i];
            int j = i - 1;
            
            while (j >= start && SortUtils.greater(array[j], key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
