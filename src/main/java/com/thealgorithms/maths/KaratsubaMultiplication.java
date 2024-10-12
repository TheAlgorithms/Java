package com.thealgorithms.maths;

import java.math.BigInteger;

/**
 * This class provides an implementation of the Karatsuba multiplication algorithm.
 *
 * <p>
 * Karatsuba multiplication is a divide-and-conquer algorithm for multiplying two large
 * numbers. It is faster than the classical multiplication algorithm and reduces the
 * time complexity to O(n^1.585) by breaking the multiplication of two n-digit numbers
 * into three multiplications of n/2-digit numbers.
 * </p>
 *
 * <p>
 * The main idea of the Karatsuba algorithm is based on the following observation:
 * </p>
 *
 * <pre>
 * Let x and y be two numbers:
 * x = a * 10^m + b
 * y = c * 10^m + d
 *
 * Then, the product of x and y can be expressed as:
 * x * y = (a * c) * 10^(2*m) + ((a * d) + (b * c)) * 10^m + (b * d)
 * </pre>
 *
 * The Karatsuba algorithm calculates this more efficiently by reducing the number of
 * multiplications from four to three by using the identity:
 *
 * <pre>
 * (a + b)(c + d) = ac + ad + bc + bd
 * </pre>
 *
 * <p>
 * The recursion continues until the numbers are small enough to multiply directly using
 * the traditional method.
 * </p>
 */
public final class KaratsubaMultiplication {

    /**
     * Private constructor to hide the implicit public constructor
     */
    private KaratsubaMultiplication() {
    }

    /**
     * Multiplies two large numbers using the Karatsuba algorithm.
     *
     * <p>
     * This method recursively splits the numbers into smaller parts until they are
     * small enough to be multiplied directly using the traditional method.
     * </p>
     *
     * @param x The first large number to be multiplied (BigInteger).
     * @param y The second large number to be multiplied (BigInteger).
     * @return The product of the two numbers (BigInteger).
     */
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // Base case: when numbers are small enough, use direct multiplication
        // If the number is 4 bits or smaller, switch to the classical method
        if (x.bitLength() <= 4 || y.bitLength() <= 4) {
            return x.multiply(y);
        }

        // Find the maximum bit length of the two numbers
        int n = Math.max(x.bitLength(), y.bitLength());

        // Split the numbers in the middle
        int m = n / 2;

        // High and low parts of the first number x (x = a * 10^m + b)
        BigInteger high1 = x.shiftRight(m); // a = x / 2^m (higher part)
        BigInteger low1 = x.subtract(high1.shiftLeft(m)); // b = x - a * 2^m (lower part)

        // High and low parts of the second number y (y = c * 10^m + d)
        BigInteger high2 = y.shiftRight(m); // c = y / 2^m (higher part)
        BigInteger low2 = y.subtract(high2.shiftLeft(m)); // d = y - c * 2^m (lower part)

        // Recursively calculate three products
        BigInteger z0 = karatsuba(low1, low2); // z0 = b * d (low1 * low2)
        BigInteger z1 = karatsuba(low1.add(high1), low2.add(high2)); // z1 = (a + b) * (c + d)
        BigInteger z2 = karatsuba(high1, high2); // z2 = a * c (high1 * high2)

        // Combine the results using Karatsuba's formula
        // z0 + ((z1 - z2 - z0) << m) + (z2 << 2m)
        return z2
            .shiftLeft(2 * m) // z2 * 10^(2*m)
            .add(z1.subtract(z2).subtract(z0).shiftLeft(m)) // (z1 - z2 - z0) * 10^m
            .add(z0); // z0
    }
}
