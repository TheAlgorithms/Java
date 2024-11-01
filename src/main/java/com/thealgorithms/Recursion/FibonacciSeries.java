package com.thealgorithms.Recursion;

/*
    The Fibonacci series is a sequence of numbers where each number is the sum of the two preceding ones,
    starting with 0 and 1.
       NUMBER    0 1 2 3 4 5 6 7  8  9  10 ...
       FIBONACCI 0 1 1 2 3 5 8 13 21 34 55 ...
*/

public class FibonacciSeries {

    static int fib(int n) {
        if (n == 0 || n ==1)
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci = " + fib(1)); // Fibonacci = 1
        System.out.println("Fibonacci = " + fib(0)); // Fibonacci = 0
        System.out.println("Fibonacci = " + fib(14)); // Fibonacci = 377
    }
}
