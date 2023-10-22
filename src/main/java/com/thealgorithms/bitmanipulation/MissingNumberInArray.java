package com.thealgorithms.bitmanipulation;

/**
 * To Find the Missing Number In An Array Using Bit Manipulation
 * @author Yash Jain (https://github.com/Yashjain1602)
 */

public class MissingNumberInArray {
    public static int findMissingNumber(int[] nums) {
        int n = nums.length;
        int temp1 = nums[0];
        int temp2 = 1;
        for (int i = 1; i < n; i++) {
            temp1 ^= nums[i];
        }
        for (int i = 1; i < n; i++) {
            temp2 ^= i;
        }
        return (temp1 ^ temp2);
    }
    //kk
}