package com.thealgorithms.bitmanipulation;

/**
 * <pre>
 * This class provides a method to toggle the nth bit of an integer.
 *
 * Toggling a bit means flipping it: changing a 0 to a 1 or a 1 to a 0.
 *
 * The algorithm works as follows:
 *
 * Let's say we have an integer x and we want to toggle its nth bit.
 *
 * Step 1: Create a bitmask with a 1 at the nth position.
 * - Example: If n = 2, the bitmask will be 1 << 2 = 4 (0100 in binary).
 *
 * Step 2: XOR the integer x with the bitmask.
 * - Example: If x = 5 (0101 in binary) and the bitmask is 4 (0100 in binary),
 *   then x ^ bitmask = 5 ^ 4 = 1 (0001 in binary).
 *
 * After these two steps, the nth bit of x is toggled.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Bit_manipulation">Bit manipulation</a>.
 *
 * <b>Example usage:</b>
 * <code>
 * int result = ToggleNthBit.toggleNthBit(5, 2);<br>
 * System.out.println("Result after toggling 2nd bit: " + result);
 * </code>
 * </pre>
 */
public final class ToggleNthBit {
    private ToggleNthBit() {
    }

    /**
     * Toggles the nth bit of an integer.
     *
     * @param x the integer whose nth bit is to be toggled
     * @param n the position of the bit to be toggled (0-based index)
     * @return the integer with the nth bit toggled
     */
    public static int toggleNthBit(int x, int n) {
        return x ^ (1 << n);
    }
}
