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
       // Check for an empty array, return 0 as no elements to sum
        if (arr.length == 0) {
            return 0;
        }
        // If there's only one element, return that element as the result
        if (arr.length == 1) {
            return arr[0];
        }

        // Initialize two variables to store the maximum sums:
        int prev1 = arr[0]; // Maximum sum up to the first element.
        int prev2 = 0;      // Base case for the element before the first.

        // Iterate over the array starting from the second element.
        for (int ind = 1; ind < arr.length; ind++) {
            // Calculate the sum if taking the current element:
            // Include arr[ind] + prev2 (if index > 1)
            int take = arr[ind] + (ind > 1 ? prev2 : 0);

            // Calculate the sum if not taking the current element:
            // Take the sum as it is up to the previous element.
            int notTake = prev1;

            // Determine the maximum of taking or not taking the current element.
            int current = Math.max(take, notTake);

            // Update the values for the next iteration:
            prev2 = prev1;   // Shift prev2 to the last element's sum.
            prev1 = current; // Update prev1 with the current maximum sum.
        }

        return prev1;
    }
}
