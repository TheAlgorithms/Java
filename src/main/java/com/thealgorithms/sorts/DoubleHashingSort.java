package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Double Hashing Sort Algorithm Implementation
 * 
 * Double Hashing Sort uses a hybrid approach combining hashing with traditional sorting.
 * It creates hash buckets using double hashing technique to distribute elements
 * and then sorts each bucket individually.
 *
 * Time Complexity:
 * - Best Case: O(n) when elements are uniformly distributed
 * - Average Case: O(n log n)
 * - Worst Case: O(n²) when all elements hash to same bucket
 *
 * Space Complexity: O(n) for the auxiliary buckets
 *
 * @author TheAlgorithms Team
 * @see <a href="https://en.wikipedia.org/wiki/Hash_function">Hash Function</a>
 */
public class DoubleHashingSort implements SortAlgorithm {

    private static final int DEFAULT_BUCKET_COUNT = 10;

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        int bucketCount = Math.min(array.length, DEFAULT_BUCKET_COUNT);
        return doubleHashingSort(array, bucketCount);
    }

    /**
     * Sorts array using double hashing technique
     *
     * @param array the array to be sorted
     * @param bucketCount number of buckets to use
     * @return sorted array
     */
    private <T extends Comparable<T>> T[] doubleHashingSort(T[] array, int bucketCount) {
        // Create buckets using ArrayList to avoid generic array issues
        List<List<T>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute elements into buckets using double hashing
        for (T element : array) {
            int bucketIndex = getBucketIndex(element, bucketCount);
            buckets.get(bucketIndex).add(element);
        }

        // Sort each bucket and collect results
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            List<T> bucket = buckets.get(i);
            if (!bucket.isEmpty()) {
                // Sort the bucket directly using Collections.sort
                bucket.sort(null);
                
                // Copy sorted elements back to main array
                for (T element : bucket) {
                    array[index++] = element;
                }
            }
        }

        return array;
    }

    /**
     * Calculates bucket index using double hashing technique
     *
     * @param element the element to hash
     * @param bucketCount number of available buckets
     * @return bucket index
     */
    private <T extends Comparable<T>> int getBucketIndex(T element, int bucketCount) {
        if (element == null) {
            return 0;
        }

        // Primary hash function
        int hash1 = Math.abs(element.hashCode()) % bucketCount;

        // Secondary hash function (must be odd and different from bucket count)
        int hash2 = 7 - (Math.abs(element.hashCode()) % 7);

        // Double hashing formula: (hash1 + i * hash2) % bucketCount
        // For simplicity, we use i = 1 here, but could be extended for collision resolution
        return (hash1 + hash2) % bucketCount;
    }
}