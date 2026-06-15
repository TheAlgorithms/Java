package com.thealgorithms.maths;

/**
 * The Padovan Sequence is a sequence of integers defined by the recurrence relation:
 * P(n) = P(n-2) + P(n-3) with initial values P(0) = P(1) = P(2) = 1.
 * Example: 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37...
 *
 * @see <a href="https://en.wikipedia.org/wiki/Padovan_sequence">
 *     Wikipedia: Padovan Sequence</a>
 * @author Vraj Prajapati (@Rosander0)
 */
public final class PadovanSequence {

    private PadovanSequence() {
        // Utility class
    }

    /**
     * Calculates the nth term of the Padovan Sequence.
     *
     * @param n the index of the sequence (must be non-negative)
     * @return the nth term of the Padovan Sequence
     */
    public static long padovan(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative. Received: " + n);
        }
        if (n <= 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long c = 1;
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
