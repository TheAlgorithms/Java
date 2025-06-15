package com.thealgorithms.randomized;

import java.math.BigInteger;
import java.util.Random;

/**
 * The Miller–Rabin primality test
 *
 * Use case:
 *
 * - Determine whether a number is probably prime or definitely composite.
 *
 * In cryptography very big prime numbers are required.
 * A popular procedure to generate a prime number with n bits is to generate a random number and check its primality.
 * This is repeated until a prime number is found.
 *
 * Time Complexity: O(k log n)
 * Space Complexity: O(k)
 * where k - number of iterations, i.e. number of random primes to test on.
 *
 *
 * @author DomTr (https://github.com/DomTr)
 * @see <a href="https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test"> Miller Rabin primality test - Wikipedia </a>
 *
 */
public final class MillerRabinPrimality {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FOUR = new BigInteger("4");
    private static final Random rand = new Random();
    private MillerRabinPrimality() {
        throw new UnsupportedOperationException("Utility class");
    }
    /**
     * Performs the Miller-Rabin probabilistic primality test on the given number.
     *
     * @param n the number to test for primality
     * @return true if n is probably prime, false if it is composite.
     *         The test never falsely classifies a prime as composite (no false negatives),
     *         but it can mistakenly identify a composite as probably prime (false positives),
     *         although this probability is very low and decreases exponentially with the number of bases tested.
     */
    public static boolean millerRabin(BigInteger n, int iter) {
        if (n.compareTo(ONE) <= 0 || n.equals(FOUR)) return false;
        if (n.equals(THREE) || n.equals(TWO)) return true;
        long deg = 0;
        BigInteger oddPart = n.subtract(ONE);
        while (oddPart.mod(TWO).equals(ZERO)) {
            oddPart = oddPart.divide(TWO);
            deg++;
        }

        while (iter-- > 0) {
            if (checkComposite(n, oddPart, deg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the given base 'a' is a witness to the compositeness of 'n'
     * in the Miller-Rabin primality test.
     *
     * @param n the number being tested for primality
     * @param oddPart the odd part of n-1 (i.e., n - 1 = 2^deg * oddPart)
     * @param deg the exponent of 2 in the factorization of n-1
     * @return true if 'a' is a witness that 'n' is composite;
     *         e false if 'n' might still be prime with respect to this base
     */
    public static boolean checkComposite(BigInteger n, BigInteger oddPart, long deg) {
        BigInteger a = getRandom(TWO, n.subtract(TWO));
        BigInteger x = a.modPow(oddPart, n);
        if (x.equals(n.subtract(ONE)) || x.equals(ONE)) {
            return false;
        }
        long tmpDeg = 1;
        while (tmpDeg < deg) {
            x = x.modPow(BigInteger.valueOf(2), n);
            tmpDeg++;
            if (x.equals(n.subtract(ONE))) {
                return false;
            }
        }
        return true;
    }
    /*
     * Returns a random BigInteger in [from, to) interval
     *
     * @param from - lowest value
     * @param to - highest value
     */
    private static BigInteger getRandom(BigInteger from, BigInteger to) {
        BigInteger res;
        do {
            res = new BigInteger(from.bitLength(), rand);
        } while (res.compareTo(from) < 0 || res.compareTo(to) >= 0);

        return res;
    }
}
