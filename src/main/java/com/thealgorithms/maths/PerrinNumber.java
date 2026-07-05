package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

/**
 * The Perrin Sequence is a sequence of integers defined by the recurrence relation:
 * P(n) = P(n-2) + P(n-3) with initial values P(0) = 3, P(1) = 0, P(2) = 2.
 * Example: 3, 0, 2, 3, 2, 5, 5, 7, 10, 12, 17, 22, 29, 39, 51...
 * <p>
 * Note: The Perrin Sequence uses the same recurrence relation as the Padovan Sequence
 * but has different initial values.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Perrin_number">
 *     Wikipedia: Perrin Number</a>
 * @see PadovanSequence
 */
public final class PerrinNumber {

    private PerrinNumber() {
        // Utility class
    }

    /**
     * Calculates the nth term of the Perrin Sequence.
     *
     * @param n the index of the sequence (must be non-negative)
     * @return the nth term of the Perrin Sequence
     */
    public static long perrin(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative!");
        }
        if (n == 0) {
            return 3;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 2;
        }
        long a = 3;
        long b = 0;
        long c = 2;
        long result = 0;
        for (int i = 3; i <= n; i++) {
            result = a + b;
            a = b;
            b = c;
            c = result;
        }
        return result;
    }
}
