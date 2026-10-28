package com.thealgorithms.recursion;

/**
 * The Fibonacci series is a sequence of numbers where each number is the sum of the two preceding ones,
 * starting with 0 and 1.
 * <p>
 * Example:
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 ...
 * </p>
 * <p>
 * This specific implementation demonstrates a <b>Naive Recursive approach</b> with {@code O(2^n)} time complexity.
 * For more performant variations or different programming paradigms, see:
 * <ul>
 * <li>{@link com.thealgorithms.maths.FibonacciLoop} - Standard Iterative (Loop) approach</li>
 * <li>{@link com.thealgorithms.dynamicprogramming.Fibonacci} - Dynamic Programming variants (Memoization / Bottom-Up)</li>
 * <li>{@link com.thealgorithms.maths.FibonacciJavaStreams} - Functional approach using Java Streams</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberGoldenRation} - Closed-form expression using Binet's formula</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberCheck} - Utility to check if a given number is a Fibonacci number</li>
 * <li>{@link com.thealgorithms.matrix.matrixexponentiation.Fibonacci} - O(log n) Matrix Exponentiation approach</li>
 * </ul>
 */
public final class FibonacciSeries {
    private FibonacciSeries() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Calculates the nth term in the Fibonacci sequence using recursion.
     *
     * @param n the position in the Fibonacci sequence (must be non-negative)
     * @return the nth Fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
