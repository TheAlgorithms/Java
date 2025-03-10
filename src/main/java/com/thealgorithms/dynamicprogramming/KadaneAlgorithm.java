package com.thealgorithms.dynamicprogramming;

/**
 * This class implements Kadane's Algorithm to find the maximum subarray sum
 * within a given array of integers. The algorithm efficiently computes the maximum
 * sum of a contiguous subarray in linear time.
 *
 * Author: <a href="https://github.com/siddhant2002">Siddhant Swarup Mallick</a>
 */
public final class KadaneAlgorithm {
    private KadaneAlgorithm() {
    }

    /**
     * Computes the maximum subarray sum using Kadane's Algorithm and checks
     * if it matches a predicted answer.
     *
     * @param a              The input array of integers for which the maximum
     *                       subarray sum is to be calculated.
     * @param predictedAnswer The expected maximum subarray sum to be verified
     *                       against the computed sum.
     * @return true if the computed maximum subarray sum equals the predicted
     *         answer, false otherwise.
     *
     * <p>Example:</p>
     * <pre>
     * Input: {89, 56, 98, 123, 26, 75, 12, 40, 39, 68, 91}
     * Output: true if the maximum subarray sum is equal to the
     *         predicted answer.
     * </pre>
     *
     * <p>Algorithmic Complexity:</p>
     * <ul>
     * <li>Time Complexity: O(n) - the algorithm iterates through the array once.</li>
     * <li>Auxiliary Space Complexity: O(1) - only a constant amount of additional space is used.</li>
     * </ul>
     */
    public static boolean maxSum(int[] a, int predictedAnswer) {
        int sum = a[0];
        int runningSum = 0;

        for (int k : a) {
            runningSum += k;
            sum = Math.max(sum, runningSum);
            if (runningSum < 0) {
                runningSum = 0;
            }
        }

        return sum == predictedAnswer;
    }
}
