package com.thealgorithms.maths.utils;

public class MathsUtil {
    private MathsUtil() {
    }
    /**
     * Validates that the input is a positive integer.
     *
     * @param n the input number to validate
     * @throws IllegalArgumentException if {@code n} is non-positive
     */
    public static void checkInputIsPositive(double n, String errorMessage) {
        if (n <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
