package com.thealgorithms.maths;

/**
 * Fibonacci: 0 1 1 2 3 5 8 13 21 ...
 * This code checks Fibonacci Numbers up to 45th number.
 * Other checks fail because of 'long'-type overflow.
 * <p>
 * This class serves as a <b>verification utility</b> rather than a generation algorithm.
 * <p>
 * For approaches that actively compute the n-th Fibonacci number, see:
 * <ul>
 * <li>{@link com.thealgorithms.maths.FibonacciLoop} - Standard Iterative (Loop) approach</li>
 * <li>{@link com.thealgorithms.recursion.FibonacciSeries} - Naive Recursive approach</li>
 * <li>{@link com.thealgorithms.dynamicprogramming.Fibonacci} - Dynamic Programming approaches (Memoization, Bottom-Up, Optimized)</li>
 * <li>{@link com.thealgorithms.maths.FibonacciJavaStreams} - Functional approach using Java Streams</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberGoldenRation} - Closed-form expression using Binet's formula</li>
 * <li>{@link com.thealgorithms.matrix.matrixexponentiation.Fibonacci} - O(log n) Matrix Exponentiation approach</li>
 * </ul>
 */
public final class FibonacciNumberCheck {
    private FibonacciNumberCheck() {
    }
    /**
     * Check if a number is perfect square number
     *
     * @param number the number to be checked
     * @return <tt>true</tt> if {@code number} is a perfect square, otherwise
     *         <tt>false</tt>
     */
    public static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    /**
     * Check if a number is a Fibonacci number. This is true if and only if at
     * least one of 5x^2+4 or 5x^2-4 is a perfect square
     *
     * @param number the number
     * @return <tt>true</tt> if {@code number} is a Fibonacci number, otherwise
     *         <tt>false</tt>
     * @link https://en.wikipedia.org/wiki/Fibonacci_number#Identification
     */
    public static boolean isFibonacciNumber(long number) {
        long value1 = 5 * number * number + 4;
        long value2 = 5 * number * number - 4;
        return isPerfectSquare(value1) || isPerfectSquare(value2);
    }
}
