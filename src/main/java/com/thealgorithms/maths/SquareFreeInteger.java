package com.thealgorithms.maths.Prime;

/**
 * A square-free integer is an integer divisible by no perfect square other than 1.
 * In other words, its prime factorization has no repeated factors.
 * Examples: 1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15...
 * Non-examples: 4, 8, 9, 12, 16, 18 (all divisible by 4 or 9)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Square-free_integer">Square-free integer (Wikipedia)</a>
 */
public final class SquareFreeInteger {

    private SquareFreeInteger() {
    }

    /**
     * Checks if a given number is a square-free integer.
     *
     * @param number the number to check (must be greater than zero)
     * @return true if number is square-free, false otherwise
     * @throws IllegalArgumentException if number is not greater than zero
     */
    public static boolean isSquareFreeInteger(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than zero.");
        }
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % (i * i) == 0) {
                return false;
            }
        }
        return true;
    }
}
