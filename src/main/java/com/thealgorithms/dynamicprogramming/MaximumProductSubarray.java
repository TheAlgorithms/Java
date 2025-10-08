package com.thealgorithms.dynamicprogramming;

/**
 * The MaximumProductSubarray class implements the algorithm to find the
 * maximum product of a contiguous subarray within a given array of integers.
 *
 * <p>Given an array of integers (which may contain positive numbers, negative
 * numbers, and zeros), this algorithm finds the contiguous subarray that has
 * the largest product. The algorithm handles negative numbers efficiently by
 * tracking both maximum and minimum products, since a negative number can turn
 * a minimum product into a maximum product.</p>
 *
 * <p>This implementation uses a dynamic programming approach that runs in O(n)
 * time complexity and O(1) space complexity, making it highly efficient for
 * large arrays.</p>
 */
public final class MaximumProductSubarray {
    private MaximumProductSubarray() {
    }

    /**
     * Finds the maximum product of any contiguous subarray in the given array.
     *
     * @param nums an array of integers which may contain positive, negative,
     *             and zero values.
     * @return the maximum product of a contiguous subarray. Returns 0 if the
     *         array is empty.
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            currentMax = Math.max(nums[i], currentMax * nums[i]);
            currentMin = Math.min(nums[i], currentMin * nums[i]);

            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }

    /**
     * Finds the maximum product using a memoization approach with recursion.
     * This method explores all possible subarrays and stores intermediate results.
     *
     * @param nums an array of integers which may contain positive, negative,
     *             and zero values.
     * @return the maximum product of a contiguous subarray. Returns 0 if the
     *         array is empty.
     */
    public static int maxProductMemoized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                maxProduct = Math.max(maxProduct, calculateProduct(nums, memo, i, j));
            }
        }

        return maxProduct;
    }

    /**
     * A recursive helper method to calculate the product of elements from index
     * start to index end using memoization.
     *
     * @param nums the input array of integers.
     * @param memo the memoization table storing the results of subproblems.
     * @param start the starting index of the subarray.
     * @param end the ending index of the subarray.
     * @return the product of elements from start to end.
     */
    private static int calculateProduct(int[] nums, Integer[][] memo, int start, int end) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        if (start == end) {
            memo[start][end] = nums[start];
            return nums[start];
        }

        int product = calculateProduct(nums, memo, start, end - 1) * nums[end];
        memo[start][end] = product;
        return product;
    }
}