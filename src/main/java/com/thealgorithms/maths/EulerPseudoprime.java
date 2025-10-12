package com.thealgorithms.maths;

import java.math.BigInteger;
import java.util.Random;

/**
 * The {@code EulerPseudoprime} class implements the Euler primality test.
 *
 * It is based on Euler’s criterion:
 * For an odd prime number {@code n} and any integer {@code a} coprime to {@code n}:
 *   a^((n-1)/2) ≡ (a/n) (mod n)
 * where (a/n) is the Jacobi symbol.
 *
 * This algorithm is a stronger probabilistic test than Fermat’s test.
 * It may still incorrectly identify a composite as “probably prime” (Euler pseudoprime),
 * but such cases are rare.
 */
public final class EulerPseudoprime {

    private EulerPseudoprime() {
        // Private constructor to prevent instantiation.
    }

    private static final Random RANDOM = new Random(1);

    /**
     * Performs the Euler primality test for a given number.
     *
     * @param n     number to test (must be > 2 and odd)
     * @param trials number of random bases to test
     * @return {@code true} if {@code n} passes all Euler tests (probably prime),
     *         {@code false} if composite.
     */
    public static boolean isProbablePrime(BigInteger n, int trials) {
        if (n.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        for (int i = 0; i < trials; i++) {
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO));
            BigInteger jacobi = BigInteger.valueOf(jacobiSymbol(a, n));
            if (jacobi.equals(BigInteger.ZERO)) {
                return false;
            }

            BigInteger exp = n.subtract(BigInteger.ONE).divide(BigInteger.TWO);
            BigInteger modExp = a.modPow(exp, n);

            // Euler's criterion: a^((n-1)/2) ≡ (a/n) (mod n)
            if (!modExp.equals(jacobi.mod(n))) {
                return false; // definitely composite
            }
        }
        return true; // probably prime
    }

    /**
     * Computes the Jacobi symbol (a/n).
     * Assumes n is positive and odd.
     */
    public static int jacobiSymbol(BigInteger a, BigInteger n) {
        if (n.signum() <= 0 || n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("n must be positive and odd.");
        }

        int result = 1;
        a = a.mod(n);

        while (a.compareTo(BigInteger.ZERO) != 0) {
            while (a.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                a = a.divide(BigInteger.TWO);
                BigInteger nMod8 = n.mod(BigInteger.valueOf(8));
                if (nMod8.equals(BigInteger.valueOf(3)) || nMod8.equals(BigInteger.valueOf(5))) {
                    result = -result;
                }
            }

            BigInteger temp = a;
            a = n;
            n = temp;

            if (a.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
                result = -result;
            }

            a = a.mod(n);
        }

        return n.equals(BigInteger.ONE) ? result : 0;
    }

    /**
     * Generates a random BigInteger between {@code min} and {@code max}, inclusive.
     */
    private static BigInteger uniformRandom(BigInteger min, BigInteger max) {
        BigInteger result;
        do {
            result = new BigInteger(max.bitLength(), RANDOM);
        } while (result.compareTo(min) < 0 || result.compareTo(max) > 0);
        return result;
    }
}
