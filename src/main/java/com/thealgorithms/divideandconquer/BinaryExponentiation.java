package com.thealgorithms.divideandconquer;

// Java Program to Implement Binary Exponentiation (power in log n)

// Reference Link: https://en.wikipedia.org/wiki/Exponentiation_by_squaring

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
        // Base Case
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 1) { // odd power
            return x * calculatePower(x, y - 1);
        }
        return calculatePower(x * x, y / 2); // even power
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
