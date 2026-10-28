package com.thealgorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements an algorithm to count the number of continuous subarrays
 * whose sum equals a given value k.
 *
 * <p>
 * This algorithm uses the Prefix Sum technique combined with a HashMap
 * to achieve O(N) time complexity.
 * </p>
 *
 * <p>
 * Let prefixSum[i] be the sum of elements from index 0 to i.
 * A subarray (j + 1) to i has sum k if:
 *
 * <pre>
 * prefixSum[i] - prefixSum[j] = k
 * </pre>
 * </p>
 *
 * <p>
 * The HashMap stores the frequency of each prefix sum encountered so far.
 * </p>
 *
 * <p>
 * <strong>Time Complexity:</strong> O(N)<br>
 * <strong>Space Complexity:</strong> O(N)
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Prefix_sum">Prefix Sum (Wikipedia)</a>
 * @author Ruturaj Jadhav, <a href="https://github.com/ruturajjadhav07">ruturajjadhav07</a>
 */
public final class SubarraySumEqualsK {

    private SubarraySumEqualsK() {
        // Utility class; prevent instantiation
    }

    /**
     * Counts the number of subarrays whose sum equals k.
     *
     * @param nums The input integer array.
     * @param k    The target sum.
     * @return The number of continuous subarrays summing to k.
     * @throws IllegalArgumentException if nums is null.
     */
    public static int countSubarrays(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        Map<Long, Integer> prefixSumFrequency = new HashMap<>();
        prefixSumFrequency.put(0L, 1);

        long prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            long requiredSum = prefixSum - k;
            count += prefixSumFrequency.getOrDefault(requiredSum, 0);

            prefixSumFrequency.put(prefixSum, prefixSumFrequency.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
