package com.thealgorithms.maths;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * author : @pri-sin
 * This class provides methods for calculating Fibonacci numbers using Recursion with Memoization.
 */
public final class FibonacciJavaRecursion {
	static Map<Integer, BigInteger> fibonacciMap=new HashMap<>();

    private FibonacciJavaRecursion() {
    }

    /**
     * Calculates the nth Fibonacci number.
     *
     * @param n The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number as a BigInteger.
     * @throws IllegalArgumentException if the input 'n' is a negative integer.
     */
    public static BigInteger computeRecursively(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input 'n' must be a non-negative integer.");
        }
        
        if (n <= 1) {
        	fibonacciMap.put(n, BigInteger.valueOf(n));
            return BigInteger.valueOf(n);
        }
        
        if(!fibonacciMap.containsKey(n)) {
        	fibonacciMap.put(n, computeRecursively(n-2).add(computeRecursively(n-1)));
        }

        return fibonacciMap.get(n);
    }
    
}
