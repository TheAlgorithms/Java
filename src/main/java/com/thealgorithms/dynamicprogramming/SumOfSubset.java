package com.thealgorithms.dynamicprogramming;

/**
 * A utility class that contains the Sum of Subset problem solution using
 * recursion.
 *
 * The Sum of Subset problem determines whether a subset of elements from a
 * given array sums up to a specific target value.
 *
 * Wikipedia: https://en.wikipedia.org/wiki/Subset_sum_problem
 */
public final class SumOfSubset {

    private SumOfSubset() {
    }

    /**
     * Determines if there exists a subset of elements in the array `arr` that
     * adds up to the given `key` value using recursion.
     *
     * @param arr The array of integers.
     * @param num The index of the current element being considered.
     * @param key The target sum we are trying to achieve.
     * @return true if a subset of `arr` adds up to `key`, false otherwise.
     *
     *         This is a recursive solution that checks for two possibilities at
     *         each step:
     *         1. Include the current element in the subset and check if the
     *         remaining elements can sum up to the remaining target.
     *         2. Exclude the current element and check if the remaining elements
     *         can sum up to the target without this element.
     */
    public static boolean subsetSum(int[] arr, int num, int key) {
        if (key == 0) {
            return true;
        }
        if (num < 0 || key < 0) {
            return false;
        }

        boolean include = subsetSum(arr, num - 1, key - arr[num]);
        boolean exclude = subsetSum(arr, num - 1, key);
        return include || exclude;
    }
}
