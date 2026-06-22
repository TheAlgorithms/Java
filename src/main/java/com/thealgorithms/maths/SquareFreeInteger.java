package com.thealgorithms.maths;

/**
 * A square-free integer is an integer divisible by no perfect square other than 1
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
     * Checks if a given number is square-free.
     *
     * @param num the number to check (this must be positive)
     * @return true if num is square-free, false otherwise
     * @throws IllegalArgumentException if num is not positive
     */
    public static boolean isSquareFree(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer.");
        }
        for (int i = 2; (long) i * i <= num; i++) {
            if (num % (i * i) == 0) {
                return false;
            }
        }
        return true;
    }
}