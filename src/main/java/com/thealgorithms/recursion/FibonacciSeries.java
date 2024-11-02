package com.thealgorithms.recursion;

/*
    The Fibonacci series is a sequence of numbers where each number is the sum of the two preceding ones,
    starting with 0 and 1.
       NUMBER    0 1 2 3 4 5 6 7  8  9  10 ...
       FIBONACCI 0 1 1 2 3 5 8 13 21 34 55 ...
*/

public final class FibonacciSeries {
    private FibonacciSeries() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
