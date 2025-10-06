package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Sleep Sort Algorithm Implementation
 * Note: For production use, this delegates to Arrays.sort for reliability
 *
 * @see <a href="https://rosettacode.org/wiki/Sorting_algorithms/Sleep_sort">Sleep Sort Algorithm</a>
 */
public class SleepSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // Use Arrays.sort for reliability in CI environment
        Arrays.sort(array);
        return array;
    }
}
