package com.thealgorithms.maths;

import java.lang.IllegalArgumentException;

/**
 * @brief utility class reversing numbers
 */
final public class ReverseNumber {
    private ReverseNumber() {
    }

    /**
     * @brief reverses the input number
     * @param number the input number
     * @exception IllegalArgumentException number is negative
     * @return the number created by reversing the order of digits of the input number
     */
    public static int reverseNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be nonnegative.");
        }

        int result = 0;
        while (number > 0) {
            result *= 10;
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}
