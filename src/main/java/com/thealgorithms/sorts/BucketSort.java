package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BucketSort class provides a method to sort an array of elements using the Bucket Sort algorithm
 * and implements the SortAlgorithm interface.
 */
public class BucketSort implements SortAlgorithm {

    // Constant that defines the divisor for determining the number of buckets
    private static final int BUCKET_DIVISOR = 10;

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        T min = findMin(array);
        T max = findMax(array);
        int numberOfBuckets = calculateNumberOfBuckets(array.length);

        List<List<T>> buckets = initializeBuckets(numberOfBuckets);
        distributeElementsIntoBuckets(array, buckets, min, max, numberOfBuckets);

        return concatenateBuckets(buckets, array);
    }

    /**
     * Calculates the number of buckets to use based on the size of the array.
     *
     * @param arrayLength the length of the array
     * @return the number of buckets
     */
    private int calculateNumberOfBuckets(final int arrayLength) {
        return Math.max(arrayLength / BUCKET_DIVISOR, 1);
    }

    /**
     * Initializes a list of empty buckets.
     *
     * @param numberOfBuckets the number of buckets to initialize
     * @param <T> the type of elements to be sorted
     * @return a list of empty buckets
     */
    private <T extends Comparable<T>> List<List<T>> initializeBuckets(int numberOfBuckets) {
        List<List<T>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        return buckets;
    }

    /**
     * Distributes elements from the array into the appropriate buckets.
     *
     * @param array the array of elements to distribute
     * @param buckets the list of buckets
     * @param min the minimum value in the array
     * @param max the maximum value in the array
     * @param numberOfBuckets the total number of buckets
     * @param <T> the type of elements in the array
     */
    private <T extends Comparable<T>> void distributeElementsIntoBuckets(T[] array, List<List<T>> buckets, final T min, final T max, final int numberOfBuckets) {
        for (final T element : array) {
            int bucketIndex = hash(element, min, max, numberOfBuckets);
            buckets.get(bucketIndex).add(element);
        }
    }

    /**
     * Concatenates the sorted buckets back into the original array.
     *
     * @param buckets the list of sorted buckets
     * @param array the original array
     * @param <T> the type of elements in the array
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] concatenateBuckets(Iterable<List<T>> buckets, T[] array) {
        int index = 0;
        for (List<T> bucket : buckets) {
            Collections.sort(bucket);
            for (T element : bucket) {
                array[index++] = element;
            }
        }
        return array;
    }

    /**
     * The method computes the index of the bucket in which a given element should be placed.
     * This is done by "normalizing" the element within the range of the array's minimum (min) and maximum (max) values,
     * and then mapping this normalized value to a specific bucket index.
     *
     * @param element the element of the array
     * @param min the minimum value in the array
     * @param max the maximum value in the array
     * @param numberOfBuckets the total number of buckets
     * @param <T> the type of elements in the array
     * @return the index of the bucket
     */
    private <T extends Comparable<T>> int hash(final T element, final T min, final T max, final int numberOfBuckets) {
        double range = max.compareTo(min);
        double normalizedValue = element.compareTo(min) / range;
        return (int) (normalizedValue * (numberOfBuckets - 1));
    }

    private <T extends Comparable<T>> T findMin(T[] array) {
        T min = array[0];
        for (T element : array) {
            if (SortUtils.less(element, min)) {
                min = element;
            }
        }
        return min;
    }

    private <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T element : array) {
            if (SortUtils.greater(element, max)) {
                max = element;
            }
        }
        return max;
    }
}
