package com.thealgorithms.maths;

/**
 * Fibonacci: 0 1 1 2 3 5 8 13 21 ...
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        assert isFibonacciNumber(1);
        assert isFibonacciNumber(2);
        assert isFibonacciNumber(21);
        assert !isFibonacciNumber(9);
        assert !isFibonacciNumber(10);
    }

    /**
     * Check if a number is perfect square number
     *
     * @param number the number to be checked
     * @return <tt>true</tt> if {@code number} is a perfect square, otherwise
     *         <tt>false</tt>
     */
    public static boolean isPerfectSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
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
    public static boolean isFibonacciNumber(int number) {
        int value1 = 5 * number * number + 4;
        int value2 = 5 * number * number - 4;
        return isPerfectSquare(value1) || isPerfectSquare(value2);
    }
}
