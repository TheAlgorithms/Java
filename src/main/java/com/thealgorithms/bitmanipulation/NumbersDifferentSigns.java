package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to determine whether two integers have
 * different signs. It utilizes the XOR operation on the two numbers:
 *
 * - If two numbers have different signs, their most significant bits
 *   (sign bits) will differ, resulting in a negative XOR result.
 * - If two numbers have the same sign, the XOR result will be non-negative.
 *
 * Time Complexity: O(1) - Constant time operation.
 * Space Complexity: O(1) - No extra space used.
 *
 * @author Bama Charan Chhandogi
 */
public final class NumbersDifferentSigns {
    private NumbersDifferentSigns() {
    }

    /**
     * Determines if two integers have different signs using bitwise XOR.
     *
     * @param num1 the first integer
     * @param num2 the second integer
     * @return true if the two numbers have different signs, false otherwise
     */
    public static boolean differentSigns(int num1, int num2) {
        return (num1 ^ num2) < 0;
    }
}
