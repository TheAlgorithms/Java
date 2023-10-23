package com.thealgorithms.bitmanipulation;

/**
 * Find the missing number in a array of consecutive integers, only positve consecutive numbers using bit manipulation
 */
import java.util.Arrays;

public class MissingNumber {
    public static int missingNumber(int[] nums) {

        int low = Arrays.stream(nums).min().getAsInt();
        int high = Arrays.stream(nums).max().getAsInt();
        int missingNumber = high;

        for (int i = low; i < high; i++) {
            missingNumber ^= i ^ nums[i - low];
        }

        return missingNumber;
    }
}