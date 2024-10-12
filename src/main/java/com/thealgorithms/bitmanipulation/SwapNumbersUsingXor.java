package com.thealgorithms.bitmanipulation;

/**
 * <pre>
 * This class provides a method to swap two numbers using XOR without creating a third variable.
 *
 * The XOR swap algorithm works as follows:
 *
 * Let's say we have two numbers, a and b.
 *
 * Step 1: a = a ^ b
 * - This step stores the XOR of a and b in a.
 * - Example: If a = 5 (0101 in binary) and b = 3 (0011 in binary),
 *   then a = 5 ^ 3 = 6 (0110 in binary).
 *
 * Step 2: b = a ^ b
 * - This step updates b to the original value of a.
 * - Example: Now a = 6 (0110 in binary) and b = 3 (0011 in binary),
 *   then b = 6 ^ 3 = 5 (0101 in binary).
 *
 * Step 3: a = a ^ b
 * - This step updates a to the original value of b.
 * - Example: Now a = 6 (0110 in binary) and b = 5 (0101 in binary),
 *   then a = 6 ^ 5 = 3 (0011 in binary).
 *
 * After these three steps, the values of a and b are swapped.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/XOR_swap_algorithm"> XOR swap algorithm </a>.
 *
 * Example usage:
 * <code>
 * int[] result = SwapNumbersUsingXor.swap(5, 3);<br>
 * System.out.println("After swap: a = " + result[0] + ", b = " + result[1]);
 * </code>
 * </pre>
 */
public class SwapNumbersUsingXor {

    /**
     * Swaps two numbers using XOR.
     *
     * @param a the first number
     * @param b the second number
     * @return an array containing the swapped numbers
     */
    public static int[] swap(int a, int b) {
        a = a ^ b; // Step 1
        b = a ^ b; // Step 2
        a = a ^ b; // Step 3
        return new int[] {a, b};
    }
}
