package com.thealgorithms.searches;

/**
 * Implementation of Jump Search algorithm.
 *
 * Time Complexity: O(√n)
 * Space Complexity: O(1)
 *
 * Reference: https://en.wikipedia.org/wiki/Jump_search
 */
public class JumpSearch {

    // Prevent instantiation
    private JumpSearch() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
        }

        for (int i = prev; i < Math.min(step, n); i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static int find(Integer[] arr, Integer target) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        return jumpSearch(array, target);
    }
}
