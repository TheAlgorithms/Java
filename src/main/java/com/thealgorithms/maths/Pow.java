package com.thealgorithms.maths;

/**
 * A utility class for computing exponentiation (power) of integers.
 * <p>
 * This class provides a method to calculate the value of a base raised to a given exponent using a simple iterative approach.
 * For example, given a base {@code a} and an exponent {@code b}, the class computes {@code a}<sup>{@code b}</sup>.
 * </p>
 */
public final class Pow {
    private Pow() {
    }

    /**
     * Computes the value of the base raised to the power of the exponent.
     * <p>
     * The method calculates {@code a}<sup>{@code b}</sup> by iteratively multiplying the base {@code a} with itself {@code b} times.
     * If the exponent {@code b} is negative, an {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @param a the base of the exponentiation. Must be a non-negative integer.
     * @param b the exponent to which the base {@code a} is raised. Must be a non-negative integer.
     * @return the result of {@code a}<sup>{@code b}</sup> as a {@code long}.
     * @throws IllegalArgumentException if {@code b} is negative.
     */
    public static long pow(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative.");
        }
        long result = 1;
        for (int i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }
}
