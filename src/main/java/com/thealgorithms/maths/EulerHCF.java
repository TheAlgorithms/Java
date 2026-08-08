package com.thealgorithms.maths;

/**
 * Eulers HCF (Highest Common Factor) Algorithm
 * Also known as Euclid's algorithm for finding the Greatest Common Divisor (GCD)
 */
public final class EulerHCF {
    private EulerHCF() {
    }

    /**
     * String of IllegalArgumentException for positive numbers
     */
    private static final String POSITIVE_NUMBERS = "Both numbers must be greater than 0";

    /**
     * Calculate the Highest Common Factor (HCF) of two numbers using Eulers algorithm.
     * This is also known as the Greatest Common Divisor (GCD).
     *
     * @param num1 first number
     * @param num2 second number
     * @return the highest common factor of the two given numbers
     */
    public static int eulerHCF(final int num1, final int num2) {
        if (num1 <= 0 || num2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_NUMBERS);
        }
        return eulerHCFHelper(num1, num2);
    }

    /**
     * Helper method to recursively calculate HCF using Eulers algorithm.
     *
     * @param num1 first number
     * @param num2 second number
     * @return the highest common factor of the two given numbers
     */
    private static int eulerHCFHelper(final int num1, final int num2) {
        if (num2 == 0) {
            return num1;
        }
        return eulerHCFHelper(num2, num1 % num2);
    }

    /**
     * Calculate the Highest Common Factor (HCF) of two numbers using Eulers algorithm (iterative approach).
     *
     * @param num1 first number
     * @param num2 second number
     * @return the highest common factor of the two given numbers
     */
    public static int eulerHCFIterative(final int num1, final int num2) {
        if (num1 <= 0 || num2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_NUMBERS);
        }
        int a = num1;
        int b = num2;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculate the Highest Common Factor (HCF) of two long numbers using Eulers algorithm.
     *
     * @param num1 first long number
     * @param num2 second long number
     * @return the highest common factor of the two given numbers
     */
    public static long eulerHCF(final long num1, final long num2) {
        if (num1 <= 0 || num2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_NUMBERS);
        }
        long a = num1;
        long b = num2;
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
