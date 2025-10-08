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
        // Prevent instantiation
    }

    /**
     * Finds the maximum product of any contiguous subarray in the given array.
     *
     * @param nums an array of integers which may contain positive, negative,
     *             and zero values.
     * @return the maximum product of a contiguous subarray. Returns 0 if the
     *         array is null or empty.
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long maxProduct = nums[0];
        long currentMax = nums[0];
        long currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Swap currentMax and currentMin if current number is negative
            if (nums[i] < 0) {
                long temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            // Update currentMax and currentMin
            currentMax = Math.max(nums[i], currentMax * nums[i]);
            currentMin = Math.min(nums[i], currentMin * nums[i]);

            // Update global max product
            maxProduct = Math.max(maxProduct, currentMax);
        }

        return (int) maxProduct;
    }
}
