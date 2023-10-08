package com.thealgorithms.maths;

public final class MoveNumberAtLast {

    private MoveNumberAtLast() {
    }

    /**
     * @brief Move number at the last from input array
     * @param nums ineger array
     * @return Array of integer with target number at the last  {@code nums}
     */

    public static int[] moveNumber(int[] nums, int target) {
        int i = 0;
        int j = 0;

        while (i <= j && j < nums.length) {
            if (nums[j] != target) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
            j++;
        }
        return nums;
    }
}
