package com.thealgorithms.sorts;
import java.util.Arrays;

/**
 * SpreadSort is a highly efficient sorting algorithm suitable for large datasets.
 * It distributes elements into buckets and recursively sorts these buckets.
 * This implementation is generic and can sort any array of elements that extend Comparable.
 */
public class SpreadSort implements SortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    private static final int INITIAL_BUCKET_CAPACITY = 16;
    private static final int MIN_BUCKETS = 2;

    /**
     * Sorts an array using the SpreadSort algorithm.
     *
     * @param array the array to be sorted
     * @param <T>   the type of elements in the array
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }
        spreadSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Internal method to sort an array segment using the SpreadSort algorithm.
     *
     * @param array the array to be sorted
     * @param left  the left boundary of the segment
     * @param right the right boundary of the segment
     * @param <T>   the type of elements in the array
     */
    private <T extends Comparable<T>> void spreadSort(final T[] array, final int left, final int right) {
        if (left >= right) {
            return;
        }

        // Base case for small segments
        if (right - left < INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        T min = findMin(array, left, right);
        T max = findMax(array, left, right);

        if (min.equals(max)) {
            return; // All elements are the same
        }

        int numBuckets = calculateNumBuckets(right - left + 1);
        final Bucket<T>[] buckets = createBuckets(numBuckets);

        distributeElements(array, left, right, min, max, numBuckets, buckets);
        collectElements(array, left, buckets);
    }

    /**
     * Finds the minimum element in the specified segment of the array.
     *
     * @param array the array to search
     * @param left  the left boundary of the segment
     * @param right the right boundary of the segment
     * @param <T>   the type of elements in the array
     * @return the minimum element
     */
    private <T extends Comparable<T>> T findMin(final T[] array, final int left, final int right) {
        T min = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (SortUtils.less(array[i], min)) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Finds the maximum element in the specified segment of the array.
     *
     * @param array the array to search
     * @param left  the left boundary of the segment
     * @param right the right boundary of the segment
     * @param <T>   the type of elements in the array
     * @return the maximum element
     */
    private <T extends Comparable<T>> T findMax(final T[] array, final int left, final int right) {
        T max = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (SortUtils.greater(array[i], max)) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Calculates the number of buckets needed based on the size of the segment.
     *
     * @param segmentSize the size of the segment
     * @return the number of buckets
     */
    private int calculateNumBuckets(final int segmentSize) {
        int numBuckets = segmentSize / INSERTION_SORT_THRESHOLD;
        return Math.max(numBuckets, MIN_BUCKETS);
    }

    /**
     * Creates an array of buckets.
     *
     * @param numBuckets the number of buckets to create
     * @param <T>        the type of elements in the buckets
     * @return an array of buckets
     */
    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> Bucket<T>[] createBuckets(final int numBuckets) {
        final Bucket<T>[] buckets = new Bucket[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new Bucket<>();
        }
        return buckets;
    }

    /**
     * Distributes elements of the array segment into buckets.
     *
     * @param array      the array to be sorted
     * @param left       the left boundary of the segment
     * @param right      the right boundary of the segment
     * @param min        the minimum element in the segment
     * @param max        the maximum element in the segment
     * @param numBuckets the number of buckets
     * @param buckets    the array of buckets
     * @param <T>        the type of elements in the array
     */
    private <T extends Comparable<T>> void distributeElements(final T[] array, final int left, final int right, final T min, final T max, final int numBuckets, final Bucket<T>[] buckets) {
        final double range = max.compareTo(min);
        for (int i = left; i <= right; i++) {
            int bucketIndex = (int) ((array[i].compareTo(min) * numBuckets) / (range + 1));
            buckets[bucketIndex].add(array[i]);
        }
    }

    /**
     * Collects elements from the buckets back into the array.
     *
     * @param array   the array to be sorted
     * @param left    the left boundary of the segment
     * @param buckets the array of buckets
     * @param <T>     the type of elements in the array
     */
    private <T extends Comparable<T>> void collectElements(final T[] array, final int left, final Bucket<T>[] buckets) {
        int index = left;
        for (Bucket<T> bucket : buckets) {
            if (bucket.size() > 0) {
                T[] bucketArray = bucket.toArray();
                spreadSort(bucketArray, 0, bucketArray.length - 1);
                for (T element : bucketArray) {
                    array[index++] = element;
                }
            }
        }
    }

    /**
     * Insertion sort implementation for small segments.
     *
     * @param array the array to be sorted
     * @param left  the left boundary of the segment
     * @param right the right boundary of the segment
     * @param <T>   the type of elements in the array
     */
    private <T extends Comparable<T>> void insertionSort(final T[] array, final int left, final int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= left && SortUtils.greater(array[j], key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Bucket class to hold elements during sorting.
     *
     * @param <T> the type of elements in the bucket
     */
    private static class Bucket<T extends Comparable<T>> {
        private T[] elements;
        private int size;

        /**
         * Constructs a new bucket with initial capacity.
         */
        @SuppressWarnings("unchecked")
        Bucket() {
            elements = (T[]) new Comparable[INITIAL_BUCKET_CAPACITY];
            size = 0;
        }

        /**
         * Adds an element to the bucket.
         *
         * @param element the element to add
         */
        void add(T element) {
            if (size == elements.length) {
                elements = Arrays.copyOf(elements, size * 2);
            }
            elements[size++] = element;
        }

        /**
         * Returns the number of elements in the bucket.
         *
         * @return the size of the bucket
         */
        int size() {
            return size;
        }

        /**
         * Returns an array containing all elements in the bucket.
         *
         * @return an array containing all elements in the bucket
         */
        @SuppressWarnings("unchecked")
        T[] toArray() {
            return Arrays.copyOf(elements, size);
        }
    }
}
