package com.thealgorithms.dynamicprogramming;

/**
 * Implementation of the Longest Increasing Subsequence (LIS) problem using
 * an O(n log n) dynamic programming solution enhanced with binary search.
 *
 * @author Vusal Huseynov (https://github.com/huseynovvusal)
 */
public final class LongestIncreasingSubsequenceNLogN {
    private LongestIncreasingSubsequenceNLogN() {
    }

    /**
     * Finds the index of the smallest element in the array that is greater than
     * or equal to the target using binary search. The search is restricted to
     * the first `size` elements of the array.
     *
     * @param arr    The array to search in (assumed to be sorted up to `size`).
     * @param size   The number of valid elements in the array.
     * @param target The target value to find the lower bound for.
     * @return The index of the lower bound.
     */
    private static int lowerBound(int[] arr, int target, int size) {
        int l = 0;
        int r = size;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (target > arr[mid]) {
                // Move right if target is greater than mid element
                l = mid + 1;
            } else {
                // Move left if target is less than or equal to mid element
                r = mid;
            }
        }

        // Return the index where the target can be inserted
        return l;
    }

    /**
     * Calculates the length of the Longest Increasing Subsequence (LIS) in the given array.
     *
     * @param arr The input array of integers.
     * @return The length of the LIS.
     */
    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0; // Return 0 for empty or null arrays
        }

        // tails[i] - the smallest end element of an increasing subsequence of length i+1
        int[] tails = new int[arr.length];
        // size - the length of the longest increasing subsequence found so far
        int size = 0;

        for (int x : arr) {
            // Find the position to replace or extend the subsequence
            int index = lowerBound(tails, x, size);

            // Update the tails array with the current element
            tails[index] = x;

            // If the element extends the subsequence, increase the size
            if (index == size) {
                size++;
            }
        }

        // Return the length of the LIS
        return size;
    }
}
