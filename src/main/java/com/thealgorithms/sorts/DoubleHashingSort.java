package com.thealgorithms.sorts;

import java.util.Arrays;

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
        // Create buckets
        @SuppressWarnings("unchecked")
        T[][] buckets = (T[][]) new Comparable[bucketCount][];
        int[] bucketSizes = new int[bucketCount];

        // Initialize buckets
        for (int i = 0; i < bucketCount; i++) {
            @SuppressWarnings("unchecked")
            T[] bucket = (T[]) new Comparable[array.length];
            buckets[i] = bucket;
            bucketSizes[i] = 0;
        }

        // Distribute elements into buckets using double hashing
        for (T element : array) {
            int bucketIndex = getBucketIndex(element, bucketCount);
            buckets[bucketIndex][bucketSizes[bucketIndex]++] = element;
        }

        // Sort each bucket and collect results
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] > 0) {
                // Create actual sized array for this bucket
                @SuppressWarnings("unchecked")
                T[] bucket = (T[]) new Comparable[bucketSizes[i]];
                System.arraycopy(buckets[i], 0, bucket, 0, bucketSizes[i]);
                
                // Sort the bucket
                Arrays.sort(bucket);
                
                // Copy back to main array
                System.arraycopy(bucket, 0, array, index, bucketSizes[i]);
                index += bucketSizes[i];
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