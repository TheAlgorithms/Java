package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * BucketSort class provides a static method to sort an array of integers using the Bucket Sort algorithm.
 */
public final class BucketSort {
    private BucketSort() {
    }

    /**
     * Sorts the given array using the Bucket Sort algorithm.
     *
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] bucketSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
        int numberOfBuckets = max - min + 1;

        List<List<Integer>> buckets = initializeBuckets(numberOfBuckets);

        distributeElementsToBuckets(arr, buckets, min, numberOfBuckets);
        sortBuckets(buckets);

        return concatenateBuckets(buckets, arr);
    }

    /**
     * Initializes the buckets for sorting.
     *
     * @param numberOfBuckets the number of buckets to initialize
     * @return a list of empty buckets
     */
    private static List<List<Integer>> initializeBuckets(final int numberOfBuckets) {
        return IntStream.range(0, numberOfBuckets)
                .mapToObj(i -> new ArrayList<Integer>())
                .collect(Collectors.toList());
    }

    /**
     * Distributes the elements of the array into the appropriate buckets.
     *
     * @param arr the array to distribute
     * @param buckets the list of buckets
     * @param min the minimum value in the array
     * @param numberOfBuckets the total number of buckets
     */
    private static void distributeElementsToBuckets(int[] arr, List<List<Integer>> buckets, final int min, final int numberOfBuckets) {
        Arrays.stream(arr).forEach(value -> buckets.get(hash(value, min, numberOfBuckets)).add(value));
    }

    /**
     * Sorts each bucket individually.
     *
     * @param buckets the list of buckets to sort
     */
    private static void sortBuckets(List<List<Integer>> buckets) {
        buckets.forEach(Collections::sort);
    }

    /**
     * Concatenates the sorted buckets into the original array.
     *
     * @param buckets the list of sorted buckets
     * @param arr the original array
     * @return the sorted array
     */
    private static int[] concatenateBuckets(List<List<Integer>> buckets, int[] arr) {
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                arr[index++] = value;
            }
        }
        return arr;
    }

    /**
     * Computes the hash value to determine which bucket an element should be placed in.
     *
     * @param element the element of the array
     * @param min the minimum value in the array
     * @param numberOfBuckets the total number of buckets
     * @return the index of the bucket
     */
    private static int hash(final int element, final int min, final int numberOfBuckets) {
        return (element - min) / numberOfBuckets;
    }
}
