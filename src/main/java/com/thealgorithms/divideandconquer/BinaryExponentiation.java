package com.thealgorithms.divideandconquer;

// Java Program to Implement Binary Exponentiation (power in log n)

/*
 * Binary Exponentiation is a method to calculate a to the power of b.
 * It is used to calculate a^n in O(log n) time.
 *
 * Reference:
 * https://iq.opengenus.org/binary-exponentiation/
 */

public class BinaryExponentiation {

    // recursive function to calculate a to the power of b
    public static long calculatePower(long x, long y) {
        if (y == 0) {
            return 1;
        }
        long val = calculatePower(x, y / 2);
        if (y % 2 == 0) {
            return val * val;
        }
        return val * val * x;
    }

    // iterative function to calculate a to the power of b
    long power(long N, long M) {
        long power = N, sum = 1;
        while (M > 0) {
            if ((M & 1) == 1) {
                sum *= power;
            }
            power = power * power;
            M = M >> 1;
        }
        return sum;
    }
}
