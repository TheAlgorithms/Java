package src.main.java.com.dynamic;

import java.util.HashMap;

/**
 * The Fibonacci sequence is a sequence of numbers in which each number after the first two is the sum of the two preceding ones.
 * 1, 1, 2, 3, 5, 8, 13, ...
 */
public class Fibonacci {

    private static HashMap<Integer, Integer> fibonacciCache = new HashMap<>();

    /**
     * A recursive method that calculates and returns the nth fibonacci number.
     *
     * @return the nth fibonacci number.
     */
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if(fibonacciCache.containsKey(n)) {
            return fibonacciCache.get(n);
        }

        int calculatedFibonacci = fibonacci(n - 1) + fibonacci(n - 2);
        fibonacciCache.put(n, calculatedFibonacci);

        return calculatedFibonacci;
    }

}
