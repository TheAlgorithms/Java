/**
 * Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find the maximum subarray sum */
package com.thealgorithms.dynamicprogramming;

public final class KadaneAlgorithm {
    private KadaneAlgorithm() {
    }

    public static boolean maxSum(int[] a, int predictedAnswer) {
        int sum = a[0];
        int runningSum = 0;
        for (int k : a) {
            runningSum = runningSum + k;
            // running sum of all the indexs are stored
            sum = Math.max(sum, runningSum);
            // the max is stored inorder to the get the maximum sum
            if (runningSum < 0) runningSum = 0;
            // if running sum is negative then it is initialized to zero
        }
        // for-each loop is used to iterate over the array and find the maximum subarray sum
        return sum == predictedAnswer;
        // It returns true if sum and predicted answer matches
        // The predicted answer is the answer itself. So it always return true
    }
    /**
     * OUTPUT :
     * Input - {89,56,98,123,26,75,12,40,39,68,91}
     * Output: it returns either true or false
     * 1st approach Time Complexity : O(n)
     * Auxiliary Space Complexity : O(1)
     */
}
