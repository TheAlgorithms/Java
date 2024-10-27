package com.thealgorithms.maths;

/**
 * @brief utility class reversing numbers
 */
public final class ReverseNumber {
    private ReverseNumber() {
    }

    /**
     * @param number the input number
     * @return the number created by reversing the order of digits of the input number
     * @throws IllegalArgumentException number is negative
     * @brief reverses the input number
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
