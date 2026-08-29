package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

/**
 * The Jacobsthal Sequence is a sequence of integers defined by the recurrence relation:
 * J(n) = J(n-1) + 2*J(n-2) with initial values J(0) = 0, J(1) = 1.
 * Example: 0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341...
 *
 * @see <a href="https://en.wikipedia.org/wiki/Jacobsthal_number">
 *     Wikipedia: Jacobsthal Number</a>
 */
public final class JacobsthalNumber {

    private JacobsthalNumber() {
        // Utility class
    }

    /**
     * Calculates the nth term of the Jacobsthal Sequence.
     *
     * @param n the index of the sequence (must be non-negative)
     * @return the nth term of the Jacobsthal Sequence
     */
    public static long jacobsthal(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative!");
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long a = 0;
        long b = 1;
        long result = 0;
        for (int i = 2; i <= n; i++) {
            result = b + 2 * a;
            a = b;
            b = result;
        }
        return result;
    }
}
