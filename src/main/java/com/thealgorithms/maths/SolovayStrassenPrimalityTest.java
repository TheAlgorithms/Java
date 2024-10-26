package com.thealgorithms.maths;

import java.util.Random;

/**
 * This class implements the Solovay-Strassen primality test,
 * which is a probabilistic algorithm to determine whether a number is prime.
 * The algorithm is based on properties of the Jacobi symbol and modular exponentiation.
 *
 * For more information, go to {@link https://en.wikipedia.org/wiki/Solovay%E2%80%93Strassen_primality_test}
 */
final class SolovayStrassenPrimalityTest {

    private final Random random;

    /**
     * Constructs a SolovayStrassenPrimalityTest instance with a specified seed for randomness.
     *
     * @param seed the seed for generating random numbers
     */
    private SolovayStrassenPrimalityTest(int seed) {
        random = new Random(seed);
    }

    /**
     * Factory method to create an instance of SolovayStrassenPrimalityTest.
     *
     * @param seed the seed for generating random numbers
     * @return a new instance of SolovayStrassenPrimalityTest
     */
    public static SolovayStrassenPrimalityTest getSolovayStrassenPrimalityTest(int seed) {
        return new SolovayStrassenPrimalityTest(seed);
    }

    /**
     * Calculates modular exponentiation using the method of exponentiation by squaring.
     *
     * @param base the base number
     * @param exponent the exponent
     * @param mod the modulus
     * @return (base^exponent) mod mod
     */
    private static long calculateModularExponentiation(long base, long exponent, long mod) {
        long x = 1; // This will hold the result of (base^exponent) % mod
        long y = base; // This holds the current base value being squared

        while (exponent > 0) {
            // If exponent is odd, multiply the current base (y) with x
            if (exponent % 2 == 1) {
                x = x * y % mod; // Update result with current base
            }
            // Square the base for the next iteration
            y = y * y % mod; // Update base to be y^2
            exponent = exponent / 2; // Halve the exponent for next iteration
        }

        return x % mod; // Return final result after all iterations
    }

    /**
     * Computes the Jacobi symbol (a/n), which is a generalization of the Legendre symbol.
     *
     * @param a the numerator
     * @param num the denominator (must be an odd positive integer)
     * @return the Jacobi symbol value: 1, -1, or 0
     */
    public int calculateJacobi(long a, long num) {
        // Check if num is non-positive or even; Jacobi symbol is not defined in these cases
        if (num <= 0 || num % 2 == 0) {
            return 0;
        }

        a = a % num; // Reduce a modulo num to simplify calculations
        int jacobi = 1; // Initialize Jacobi symbol value

        while (a != 0) {
            // While a is even, reduce it and adjust jacobi based on properties of num
            while (a % 2 == 0) {
                a /= 2; // Divide a by 2 until it becomes odd
                long nMod8 = num % 8; // Get num modulo 8 to check conditions for jacobi adjustment
                if (nMod8 == 3 || nMod8 == 5) {
                    jacobi = -jacobi; // Flip jacobi sign based on properties of num modulo 8
                }
            }

            long temp = a; // Temporarily store value of a
            a = num; // Set a to be num for next iteration
            num = temp; // Set num to be previous value of a

            // Adjust jacobi based on properties of both numbers when both are odd and congruent to 3 modulo 4
            if (a % 4 == 3 && num % 4 == 3) {
                jacobi = -jacobi; // Flip jacobi sign again based on congruences
            }

            a = a % num; // Reduce a modulo num for next iteration of Jacobi computation
        }

        return (num == 1) ? jacobi : 0; // If num reduces to 1, return jacobi value, otherwise return 0 (not defined)
    }

    /**
     * Performs the Solovay-Strassen primality test on a given number.
     *
     * @param num the number to be tested for primality
     * @param iterations the number of iterations to run for accuracy
     * @return true if num is likely prime, false if it is composite
     */
    public boolean solovayStrassen(long num, int iterations) {
        if (num <= 1) {
            return false; // Numbers <=1 are not prime by definition.
        }
        if (num <= 3) {
            return true; // Numbers <=3 are prime.
        }

        for (int i = 0; i < iterations; i++) {
            long r = Math.abs(random.nextLong() % (num - 1)) + 2; // Generate a non-negative random number.
            long a = r % (num - 1) + 1; // Choose random 'a' in range [1, n-1].

            long jacobi = (num + calculateJacobi(a, num)) % num;
            // Calculate Jacobi symbol and adjust it modulo n.

            long mod = calculateModularExponentiation(a, (num - 1) / 2, num);
            // Calculate modular exponentiation: a^((n-1)/2) mod n.

            if (jacobi == 0 || mod != jacobi) {
                return false; // If Jacobi symbol is zero or doesn't match modular result, n is composite.
            }
        }

        return true; // If no contradictions found after all iterations, n is likely prime.
    }
}
