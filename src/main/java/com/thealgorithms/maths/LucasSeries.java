package com.thealgorithms.maths;

/**
 * Utility class for calculating Lucas numbers.
 * The Lucas sequence is similar to the Fibonacci sequence but starts with 2 and
 * 1.
 * The sequence follows: L(n) = L(n-1) + L(n-2)
 * Starting values: L(1) = 2, L(2) = 1
 * Sequence: 2, 1, 3, 4, 7, 11, 18, 29, 47, 76, 123, ...
 *
 * @see <a href="https://en.wikipedia.org/wiki/Lucas_number">Lucas Number</a>
 * @author TheAlgorithms Contributors
 */
public final class LucasSeries {
    private LucasSeries() {
    }

    /**
     * Calculate the nth Lucas number using recursion.
     * Time Complexity: O(2^n) - exponential due to recursive calls
     * Space Complexity: O(n) - recursion depth
     *
     * @param n the position in the Lucas sequence (1-indexed, must be positive)
     * @return the nth Lucas number
     * @throws IllegalArgumentException if n is less than 1
     */
    public static int lucasSeries(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input must be a positive integer. Provided: " + n);
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 1;
        }
        return lucasSeries(n - 1) + lucasSeries(n - 2);
    }

    /**
     * Calculate the nth Lucas number using iteration.
     * Time Complexity: O(n) - single loop through n iterations
     * Space Complexity: O(1) - constant space usage
     *
     * @param n the position in the Lucas sequence (1-indexed, must be positive)
     * @return the nth Lucas number
     * @throws IllegalArgumentException if n is less than 1
     */
    public static int lucasSeriesIteration(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input must be a positive integer. Provided: " + n);
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 1;
        }

        int previous = 2;
        int current = 1;
        for (int i = 2; i < n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }
}
