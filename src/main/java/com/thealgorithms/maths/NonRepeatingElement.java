package com.thealgorithms.maths;

/**
 * Find the 2 elements which are non-repeating in an array
 * Reason to use bitwise operator: It makes our program faster as we are operating on bits and not
 * on actual numbers.
 *
 * Explanation of the code:
 * Let us assume we have an array [1, 2, 1, 2, 3, 4]
 * Property of XOR: num ^ num = 0.
 * If we XOR all the elements of the array, we will be left with 3 ^ 4 as 1 ^ 1
 * and 2 ^ 2 would give 0. Our task is to find num1 and num2 from the result of 3 ^ 4 = 7.
 * We need to find the two's complement of 7 and find the rightmost set bit, i.e., (num & (-num)).
 * Two's complement of 7 is 001, and hence res = 1. There can be 2 options when we Bitwise AND this res
 * with all the elements in our array:
 * 1. The result will be a non-zero number.
 * 2. The result will be 0.
 * In the first case, we will XOR our element with the first number (which is initially 0).
 * In the second case, we will XOR our element with the second number (which is initially 0).
 * This is how we will get non-repeating elements with the help of bitwise operators.
 */
public final class NonRepeatingElement {
    private NonRepeatingElement() {
    }

    /**
     * Finds the two non-repeating elements in the array.
     *
     * @param arr The input array containing exactly two non-repeating elements and all other elements repeating.
     * @return An array containing the two non-repeating elements.
     * @throws IllegalArgumentException if the input array length is odd.
     */
    public static int[] findNonRepeatingElements(int[] arr) {
        if (arr.length % 2 != 0) {
            throw new IllegalArgumentException("Array should contain an even number of elements");
        }

        int xorResult = 0;

        // Find XOR of all elements
        for (int num : arr) {
            xorResult ^= num;
        }

        // Find the rightmost set bit
        int rightmostSetBit = xorResult & (-xorResult);
        int num1 = 0;
        int num2 = 0;

        // Divide the elements into two groups and XOR them
        for (int num : arr) {
            if ((num & rightmostSetBit) != 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[] {num1, num2};
    }
}
