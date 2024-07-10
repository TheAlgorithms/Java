package com.thealgorithms.others;

import java.util.HashSet;

/*
References: https://en.wikipedia.org/wiki/Streaming_algorithm
* In this model, the function of interest is computing over a fixed-size window in the stream. As the stream progresses,
* items from the end of the window are removed from consideration while new items from the stream take their place.
* @author Swarga-codes (https://github.com/Swarga-codes)
*/
public final class MaximumSumOfDistinctSubarraysWithLengthK {
    private MaximumSumOfDistinctSubarraysWithLengthK() {
    }
    /*
     * Returns the maximum sum of subarray of size K consisting of distinct
     * elements.
     *
     * @param k size of the subarray which should be considered from the given
     * array.
     *
     * @param nums is the array from which we would be finding the required
     * subarray.
     *
     * @return the maximum sum of distinct subarray of size K.
     */
    public static long maximumSubarraySum(int k, int... nums) {
        if (nums.length < k) {
            return 0;
        }
        long max = 0; // this will store the max sum which will be our result
        long s = 0; // this will store the sum of every k elements which can be used to compare with
                    // max
        HashSet<Integer> set = new HashSet<>(); // this can be used to store unique elements in our subarray
        // Looping through k elements to get the sum of first k elements
        for (int i = 0; i < k; i++) {
            s += nums[i];
            set.add(nums[i]);
        }
        // Checking if the first kth subarray contains unique elements or not if so then
        // we assign that to max
        if (set.size() == k) {
            max = s;
        }
        // Looping through the rest of the array to find different subarrays and also
        // utilising the sliding window algorithm to find the sum
        // in O(n) time complexity
        for (int i = 1; i < nums.length - k + 1; i++) {
            s = s - nums[i - 1];
            s = s + nums[i + k - 1];
            int j = i;
            boolean flag = false; // flag value which says that the subarray contains distinct elements
            while (j < i + k && set.size() < k) {
                if (nums[i - 1] == nums[j]) {
                    flag = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!flag) {
                set.remove(nums[i - 1]);
            }
            set.add(nums[i + k - 1]);
            // if the subarray contains distinct elements then we compare and update the max
            // value
            if (set.size() == k) {
                if (max < s) {
                    max = s;
                }
            }
        }
        return max; // the final maximum sum
    }
}
