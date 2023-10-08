/**
 * @author Md Asif Joardar
 *
 * Description: The partition problem is a classic problem in computer science
 * that asks whether a given set can be partitioned into two subsets such that
 * the sum of elements in each subset is the same.
 *
 * Example:
 * Consider nums = {1, 2, 3}
 * We can split the array "nums" into two partitions, where each having a sum of 3.
 * nums1 = {1, 2}
 * nums2 = {3}
 *
 * The time complexity of the solution is O(n × sum) and requires O(n × sum) space
 */

package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

public class PartitionProblem {

    /**
     * Test if a set of integers can be partitioned into two subsets such that the sum of elements
     * in each subset is the same.
     *
     * @param nums the array contains integers.
     * @return {@code true} if two subset exists, otherwise {@code false}.
     */
    public static boolean partition(int[] nums) {
        // calculate the sum of all the elements in the array
        int sum = Arrays.stream(nums).sum();

        // it will return true if the sum is even and the array can be divided into two
        // subarrays/subset with equal sum. and here i reuse the SubsetSum class from dynamic
        // programming section to check if there is exists a subsetsum into nums[] array same as the
        // given sum
        return (sum & 1) == 0 && SubsetSum.subsetSum(nums, sum / 2);
    }
}
