package com.thealgorithms.bitmanipulation;

/**
 * Utility class for checking if a number is a power of two.
 * A power of two is a number that can be expressed as 2^n where n is a non-negative integer.
 * This class provides a method to determine if a given integer is a power of two using bit manipulation.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public final class IsPowerTwo {
    private IsPowerTwo() {
    }

    /**
     * Checks if the given integer is a power of two.
     *
     * A number is considered a power of two if it is greater than zero and
     * has exactly one '1' bit in its binary representation. This method
     * uses the property that for any power of two (n), the expression
     * (n & (n - 1)) will be zero.
     *
     * @param number the integer to check
     * @return true if the number is a power of two, false otherwise
     */
    public static boolean isPowerTwo(int number) {
        if (number <= 0) {
            return false;
        }
        int ans = number & (number - 1);
        return ans == 0;
    }
}
