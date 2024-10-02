package com.thealgorithms.maths;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : @pri-sin
 * This class provides methods for calculating Fibonacci numbers using Recursion with Memoization.
 */
public final class FibonacciWithDP {
	static List<BigInteger> fibonacciList=new ArrayList<>(Arrays.asList(BigInteger.ZERO,BigInteger.ONE));

    private FibonacciWithDP() {
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
            return fibonacciList.get(n);
        }
        
        if(fibonacciList.size()<=n) {
        	fibonacciList.add(computeRecursively(n-2).add(computeRecursively(n-1)));
        }

        return fibonacciList.get(n);
    }  
}
