package com.thealgorithms.bitmanipulation;

/**
 * A utility class to find the non-repeating number in an array where every other number repeats.
 * This class contains a method to identify the single unique number using bit manipulation.
 *
 * The solution leverages the properties of the XOR operation, which states that:
 * - x ^ x = 0 for any integer x (a number XORed with itself is zero)
 * - x ^ 0 = x for any integer x (a number XORed with zero is the number itself)
 *
 * Using these properties, we can find the non-repeating number in linear time with constant space.
 *
 * Example:
 * Given the input array [2, 3, 5, 2, 3], the output will be 5 since it does not repeat.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public final class NonRepeatingNumberFinder {
    private NonRepeatingNumberFinder() {
    }

    /**
     * Finds the non-repeating number in the given array.
     *
     * @param arr an array of integers where every number except one appears twice
     * @return the integer that appears only once in the array or 0 if the array is empty
     */
    public static int findNonRepeatingNumber(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
