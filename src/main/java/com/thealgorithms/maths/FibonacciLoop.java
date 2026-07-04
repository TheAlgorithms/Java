package com.thealgorithms.maths;
import java.math.BigInteger;
/**
 * This class provides methods for calculating Fibonacci numbers using BigInteger for large values of 'n'.
 * <p>
 * This specific implementation uses an <b>Iterative approach (Loop)</b> with {@code O(n)} time complexity
 * and {@code O(1)} space complexity.
 * <p>
 * For alternative approaches to compute or verify Fibonacci numbers, see:
 * <ul>
 * <li>{@link com.thealgorithms.recursion.FibonacciSeries} - Naive Recursive approach</li>
 * <li>{@link com.thealgorithms.dynamicprogramming.Fibonacci} - Dynamic Programming approaches (Memoization, Bottom-Up, Optimized)</li>
 * <li>{@link com.thealgorithms.maths.FibonacciJavaStreams} - Functional approach using Java Streams</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberGoldenRation} - Closed-form expression using Binet's formula</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberCheck} - Utility to check if a given number is a Fibonacci number</li>
 * <li>{@link com.thealgorithms.matrix.matrixexponentiation.Fibonacci} - O(log n) Matrix Exponentiation approach</li>
 * </ul>
 */
public final class FibonacciLoop {

    private FibonacciLoop() {
        // Private constructor to prevent instantiation of this utility class.
    }

    /**
     * Calculates the nth Fibonacci number.
     *
     * @param n The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number as a BigInteger.
     * @throws IllegalArgumentException if the input 'n' is a negative integer.
     */
    public static BigInteger compute(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input 'n' must be a non-negative integer.");
        }

        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger prev = BigInteger.ZERO;
        BigInteger current = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            BigInteger next = prev.add(current);
            prev = current;
            current = next;
        }

        return current;
    }
}
