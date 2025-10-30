package com.thealgorithms.dynamicprogramming;

public class TestKadane {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArraySum(arr)); // Expected: 6
    }

    private static int maxSubArraySum(int[] nums) {
        return KadanesAlgorithm.maxSubArraySum(nums);
    }
}
