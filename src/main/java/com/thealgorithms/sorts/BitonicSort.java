package com.thealgorithms.sorts;

import java.util.Arrays;
import java.util.function.BiPredicate;

/**
 * BitonicSort class implements the SortAlgorithm interface using the bitonic sort technique.
 */
public class BitonicSort implements SortAlgorithm {
    private enum Direction {
        DESCENDING,
        ASCENDING,
    }

    /**
     * Sorts the given array using the Bitonic Sort algorithm.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be sorted
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        final int paddedSize = nextPowerOfTwo(array.length);
        T[] paddedArray = Arrays.copyOf(array, paddedSize);

        // Fill the padded part with a maximum value
        final T maxValue = max(array);
        Arrays.fill(paddedArray, array.length, paddedSize, maxValue);

        bitonicSort(paddedArray, 0, paddedSize, Direction.ASCENDING);
        return Arrays.copyOf(paddedArray, array.length);
    }

    private <T extends Comparable<T>> void bitonicSort(final T[] array, final int low, final int cnt, final Direction direction) {
        if (cnt > 1) {
            final int k = cnt / 2;

            // Sort first half in ascending order
            bitonicSort(array, low, k, Direction.ASCENDING);

            // Sort second half in descending order
            bitonicSort(array, low + k, cnt - k, Direction.DESCENDING);

            // Merge the whole sequence in ascending order
            bitonicMerge(array, low, cnt, direction);
        }
    }

    /**
     * Merges the bitonic sequence in the specified direction.
     *
     * @param <T> the type of elements in the array, which must be Comparable
     * @param array the array containing the bitonic sequence to be merged
     * @param low the starting index of the sequence to be merged
     * @param cnt the number of elements in the sequence to be merged
     * @param direction the direction of sorting
     */
    private <T extends Comparable<T>> void bitonicMerge(T[] array, int low, int cnt, Direction direction) {
        if (cnt > 1) {
            final int k = cnt / 2;

            final BiPredicate<T, T> areSorted = (direction == Direction.ASCENDING) ? (a, b) -> a.compareTo(b) < 0 : (a, b) -> a.compareTo(b) > 0;
            for (int i = low; i < low + k; i++) {
                if (!areSorted.test(array[i], array[i + k])) {
                    SortUtils.swap(array, i, i + k);
                }
            }

            bitonicMerge(array, low, k, direction);
            bitonicMerge(array, low + k, cnt - k, direction);
        }
    }

    /**
     * Finds the next power of two greater than or equal to the given number.
     *
     * @param n the number
     * @return the next power of two
     */
    private static int nextPowerOfTwo(int n) {
        int count = 0;

        // First n in the below condition is for the case where n is 0
        if ((n & (n - 1)) == 0) {
            return n;
        }

        while (n != 0) {
            n >>= 1;
            count += 1;
        }

        return 1 << count;
    }

    /**
     * Finds the maximum element in the given array.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be searched
     * @return the maximum element in the array
     * @throws IllegalArgumentException if the array is null or empty
     */
    private static <T extends Comparable<T>> T max(final T[] array) {
        return Arrays.stream(array).max(Comparable::compareTo).orElseThrow();
    }
}
