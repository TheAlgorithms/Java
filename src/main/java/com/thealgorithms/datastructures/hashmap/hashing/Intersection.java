package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Intersection} class provides a method to compute the intersection of two integer arrays.
 * The intersection is defined as the set of common elements present in both arrays.
 * <p>
 * This class utilizes a HashMap to efficiently count occurrences of elements in the first array,
 * allowing for an efficient lookup of common elements in the second array.
 * </p>
 *
 * <p>
 * Example:
 * <pre>
 * int[] array1 = {1, 2, 2, 1};
 * int[] array2 = {2, 2};
 * List<Integer> result = Intersection.intersection(array1, array2); // result will contain [2, 2]
 * </pre>
 * </p>
 *
 * <p>
 * Note: The order of the returned list may vary since it depends on the order of elements
 * in the input arrays.
 * </p>
 */
public final class Intersection {

    /**
     * Computes the intersection of two integer arrays.
     * Steps:
     * 1. Count the occurrences of each element in the first array using a HashMap.
     * 2. Iterate over the second array and check if the element is present in the HashMap.
     * If it is, add it to the result list and decrement the count in the HashMap.
     * 3. Return the result list containing the intersection of the two arrays.
     *
     * @param arr1 the first array of integers
     * @param arr2 the second array of integers
     * @return a list containing the intersection of the two arrays, or an empty list if either array is null or empty
     */
    public static List<Integer> intersection(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> cnt = new HashMap<>(16);
        for (int v : arr1) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int v : arr2) {
            if (cnt.containsKey(v) && cnt.get(v) > 0) {
                res.add(v);
                cnt.put(v, cnt.get(v) - 1);
            }
        }
        return res;
    }

    private Intersection() {
    }
}
