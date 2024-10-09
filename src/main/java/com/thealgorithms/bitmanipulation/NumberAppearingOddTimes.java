package com.thealgorithms.bitmanipulation;

/**
 * Find the Number Appearing Odd Times in an array
 * @author Lakshyajeet Singh Goyal (https://github.com/DarkMatter-999)
 */

public final class NumberAppearingOddTimes {
    private NumberAppearingOddTimes() {
    }
    public static int findOddOccurrence(int[] arr) {
        int result = 0;

        // XOR all elements in the array
        for (int num : arr) {
            result ^= num;
        }

        return result;
    }
}
