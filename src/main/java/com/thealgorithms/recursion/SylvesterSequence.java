package com.thealgorithms.recursion;

import java.math.BigInteger;

/**
 * A utility class for calculating numbers in Sylvester's sequence.
 *
 * <p>Sylvester's sequence is a sequence of integers where each term is calculated
 * using the formula:
 * <pre>
 * a(n) = a(n-1) * (a(n-1) - 1) + 1
 * </pre>
 * with the first term being 2.
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sylvester_sequence">Wikipedia: Sylvester sequence</a>
 */
public final class SylvesterSequence {

    // Private constructor to prevent instantiation
    private SylvesterSequence() {
    }

    /**
     * Calculates the nth number in Sylvester's sequence.
     *
     * <p>The sequence is defined recursively, with the first term being 2:
     * <pre>
     * a(1) = 2
     * a(n) = a(n-1) * (a(n-1) - 1) + 1 for n > 1
     * </pre>
     *
     * @param n the position in the sequence (must be greater than 0)
     * @return the nth number in Sylvester's sequence
     * @throws IllegalArgumentException if n is less than or equal to 0
     */
    public static BigInteger sylvester(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("sylvester() does not accept negative numbers or zero.");
        }
        if (n == 1) {
            return BigInteger.valueOf(2);
        } else {
            BigInteger prev = sylvester(n - 1);
            // Sylvester sequence formula: a(n) = a(n-1) * (a(n-1) - 1) + 1
            return prev.multiply(prev.subtract(BigInteger.ONE)).add(BigInteger.ONE);
        }
    }
}
