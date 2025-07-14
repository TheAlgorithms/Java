package com.thealgorithms.maths;

/**
 * Calculates the generic root (repeated digital sum) of a non-negative integer.
 * <p>
 * For example, the generic root of 12345 is calculated as:
 * 1 + 2 + 3 + 4 + 5 = 15,
 * then 1 + 5 = 6, so the generic root is 6.
 * <p>
 * Reference:
 * https://technotip.com/6774/c-program-to-find-generic-root-of-a-number/
 */
public final class GenericRoot {

    private static final int BASE = 10;

    private GenericRoot() {
    }

    /**
     * Computes the sum of the digits of a non-negative integer in base 10.
     *
     * @param n non-negative integer
     * @return sum of digits of {@code n}
     */
    private static int sumOfDigits(final int n) {
        assert n >= 0;
        if (n < BASE) {
            return n;
        }
        return (n % BASE) + sumOfDigits(n / BASE);
    }

    /**
     * Computes the generic root (repeated digital sum) of an integer.
     * For negative inputs, the absolute value is used.
     *
     * @param n integer input
     * @return generic root of {@code n}
     */
    public static int genericRoot(final int n) {
        int number = Math.abs(n);
        if (number < BASE) {
            return number;
        }
        return genericRoot(sumOfDigits(number));
    }
}
