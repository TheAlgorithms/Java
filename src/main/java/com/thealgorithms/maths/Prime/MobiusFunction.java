package com.thealgorithms.maths.Prime;

/*
 * Java program for mobius function
 * For any positive integer n, define μ(n) as the sum of the primitive nth roots of unity.
 * It has values in {−1, 0, 1} depending on the factorization of n into prime factors:
 *   μ(n) = +1 if n is a square-free positive integer with an even number of prime factors.
 *   μ(n) = −1 if n is a square-free positive integer with an odd number of prime factors.
 *   μ(n) = 0 if n has a squared prime factor.
 * Wikipedia: https://en.wikipedia.org/wiki/M%C3%B6bius_function
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */
public final class MobiusFunction {
    private MobiusFunction() {
    }

    /**
     * This method returns μ(n) of given number n
     *
     * @param number Integer value which μ(n) is to be calculated
     * @return  1 when number is less than or equals 1
     *            or number has even number of prime factors
     *          0 when number has repeated prime factor
     *         -1 when number has odd number of prime factors
     */
    public static int mobius(int number) {
        if (number <= 0) {
            // throw exception when number is less than or is zero
            throw new IllegalArgumentException("Number must be greater than zero.");
        }

        int primeFactorCount = 0;
        int remaining = number;

        /* Divide out every prime factor in turn. Trial division only has to run up to the square
        root of the remaining value, and the multiplication is widened to long so that the bound
        does not overflow for numbers close to Integer.MAX_VALUE. */
        for (int factor = 2; (long) factor * factor <= remaining; factor++) {
            if (remaining % factor == 0) {
                remaining /= factor;
                if (remaining % factor == 0) {
                    // number is divisible by the square of this prime factor
                    return 0;
                }
                primeFactorCount++;
            }
        }

        /* Whatever is left is either 1 or a single prime factor larger than the square root. */
        if (remaining > 1) {
            primeFactorCount++;
        }

        return (primeFactorCount % 2 == 0) ? 1 : -1;
    }
}
