package com.thealgorithms.dynamicprogramming;

/**
 * Class to find the maximum sum of non-adjacent elements in an array. This
 * class contains two approaches: one with O(n) space complexity and another
 * with O(1) space optimization. For more information, refer to
 * https://takeuforward.org/data-structure/maximum-sum-of-non-adjacent-elements-dp-5/
 */
final class MaximumSumOfNonAdjacentElements {

    private MaximumSumOfNonAdjacentElements() {
    }

    /**
     * Approach 1: Uses a dynamic programming array to store the maximum sum at
     * each index. Time Complexity: O(n) - where n is the length of the input
     * array. Space Complexity: O(n) - due to the additional dp array.
     * @param arr The input array of integers.
     * @return The maximum sum of non-adjacent elements.
     */
    public static int getMaxSumApproach1(int[] arr) {
        if (arr.length == 0) {
            return 0; // Check for empty array
        }

        int n = arr.length;
        int[] dp = new int[n];

        // Base case: Maximum sum if only one element is present.
        dp[0] = arr[0];

        for (int ind = 1; ind < n; ind++) {

            // Case 1: Do not take the current element, carry forward the previous max
            // sum.
            int notTake = dp[ind - 1];

            // Case 2: Take the current element, add it to the max sum up to two
            // indices before.
            int take = arr[ind];
            if (ind > 1) {
                take += dp[ind - 2];
            }

            // Store the maximum of both choices in the dp array.
            dp[ind] = Math.max(take, notTake);
        }

        return dp[n - 1];
    }

    /**
     * Approach 2: Optimized space complexity approach using two variables instead
     * of an array. Time Complexity: O(n) - where n is the length of the input
     * array. Space Complexity: O(1) - as it only uses constant space for two
     * variables.
     * @param arr The input array of integers.
     * @return The maximum sum of non-adjacent elements.
     */
    public static int getMaxSumApproach2(int[] arr) {
        if (arr.length == 0) {
            return 0; // Check for empty array
        }

        int n = arr.length;

        // Two variables to keep track of previous two results:
        // prev1 = max sum up to the last element (n-1)
        // prev2 = max sum up to the element before last (n-2)

        int prev1 = arr[0]; // Base case: Maximum sum for the first element.
        int prev2 = 0;

        for (int ind = 1; ind < n; ind++) {
            // Case 1: Do not take the current element, keep the last max sum.
            int notTake = prev1;

            // Case 2: Take the current element and add it to the result from two
            // steps back.
            int take = arr[ind];
            if (ind > 1) {
                take += prev2;
            }

            // Calculate the current maximum sum and update previous values.
            int current = Math.max(take, notTake);

            // Shift prev1 and prev2 for the next iteration.
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
