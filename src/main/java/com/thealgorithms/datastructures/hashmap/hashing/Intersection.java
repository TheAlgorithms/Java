package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Intersection} class provides a method to compute the intersection of two integer arrays.
 * <p>
 * This intersection includes duplicate values — meaning elements are included in the result
 * as many times as they appear in both arrays (i.e., multiset intersection).
 * </p>
 *
 * <p>
 * The algorithm uses a {@link java.util.HashMap} to count occurrences of elements in the first array,
 * then iterates through the second array to collect common elements based on these counts.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * int[] array1 = {1, 2, 2, 1};
 * int[] array2 = {2, 2};
 * List<Integer> result = Intersection.intersection(array1, array2); // result: [2, 2]
 * }</pre>
 * </p>
 *
 * <p>
 * Note: The order of elements in the returned list depends on the order in the second input array.
 * </p>
 */
public final class Intersection {

    private Intersection() {
        // Utility class; prevent instantiation
    }

    /**
     * Computes the intersection of two integer arrays, preserving element frequency.
     * For example, given [1,2,2,3] and [2,2,4], the result will be [2,2].
     *
     * Steps:
     * 1. Count the occurrences of each element in the first array using a map.
     * 2. Iterate over the second array and collect common elements.
     *
     * @param arr1 the first array of integers
     * @param arr2 the second array of integers
     * @return a list containing the intersection of the two arrays (with duplicates),
     *         or an empty list if either array is null or empty
     */
    public static List<Integer> intersection(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : arr2) {
            if (countMap.getOrDefault(num, 0) > 0) {
                result.add(num);
                countMap.computeIfPresent(num, (k, v) -> v - 1);
            }
        }

        return result;
    }
}
