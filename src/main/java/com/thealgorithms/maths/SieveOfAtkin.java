package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Sieve of Atkin, an optimized algorithm to generate
 * all prime numbers up to a given limit.
 *
 * The Sieve of Atkin uses quadratic forms and modular arithmetic to identify
 * prime candidates, then eliminates multiples of squares. It is more efficient
 * than the Sieve of Eratosthenes for large limits.
 */
public final class SieveOfAtkin {

    private SieveOfAtkin() {
        // Utility class; prevent instantiation
    }

    /**
     * Generates a list of all prime numbers up to the specified limit
     * using the Sieve of Atkin algorithm.
     *
     * @param limit the upper bound up to which primes are generated; must be zero or positive
     * @return a list of prime numbers up to the limit; empty if the limit is less than 2
     */
    public static List<Integer> generatePrimes(int limit) {
        if (limit < 1) {
            return List.of();
        }

        boolean[] sieve = new boolean[limit + 1];
        int sqrtLimit = (int) Math.sqrt(limit);

        markQuadraticResidues(limit, sqrtLimit, sieve);
        eliminateMultiplesOfSquares(limit, sqrtLimit, sieve);

        List<Integer> primes = new ArrayList<>();
        if (limit >= 2) {
            primes.add(2);
        }
        if (limit >= 3) {
            primes.add(3);
        }

        for (int i = 5; i <= limit; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Marks numbers in the sieve as prime candidates based on quadratic residues.
     *
     * This method iterates over all x and y up to sqrt(limit) and applies
     * the three quadratic forms used in the Sieve of Atkin. Numbers satisfying
     * the modulo conditions are toggled in the sieve array.
     *
     * @param limit the upper bound for primes
     * @param sqrtLimit square root of the limit
     * @param sieve boolean array representing potential primes
     */
    private static void markQuadraticResidues(int limit, int sqrtLimit, boolean[] sieve) {
        for (int x = 1; x <= sqrtLimit; x++) {
            for (int y = 1; y <= sqrtLimit; y++) {
                applyQuadraticForm(4 * x * x + y * y, limit, sieve, 1, 5);
                applyQuadraticForm(3 * x * x + y * y, limit, sieve, 7);
                applyQuadraticForm(3 * x * x - y * y, limit, sieve, 11, x > y);
            }
        }
    }

    /**
     * Toggles the sieve entry for a number if it satisfies one modulo condition.
     *
     * @param n the number to check
     * @param limit upper bound of primes
     * @param sieve boolean array representing potential primes
     * @param modulo the modulo condition number to check
     */
    private static void applyQuadraticForm(int n, int limit, boolean[] sieve, int modulo) {
        if (n <= limit && n % 12 == modulo) {
            sieve[n] ^= true;
        }
    }

    /**
     * Toggles the sieve entry for a number if it satisfies either of two modulo conditions.
     *
     * @param n the number to check
     * @param limit upper bound of primes
     * @param sieve boolean array representing potential primes
     * @param modulo1 first modulo condition number to check
     * @param modulo2 second modulo condition number to check
     */
    private static void applyQuadraticForm(int n, int limit, boolean[] sieve, int modulo1, int modulo2) {
        if (n <= limit && (n % 12 == modulo1 || n % 12 == modulo2)) {
            sieve[n] ^= true;
        }
    }

    /**
     * Toggles the sieve entry for a number if it satisfies the modulo condition and an additional boolean condition.
     *
     * This version is used for the quadratic form 3*x*x - y*y, which requires x > y.
     *
     * @param n the number to check
     * @param limit upper bound of primes
     * @param sieve boolean array representing potential primes
     * @param modulo the modulo condition number to check
     * @param condition an additional boolean condition that must be true
     */
    private static void applyQuadraticForm(int n, int limit, boolean[] sieve, int modulo, boolean condition) {
        if (condition && n <= limit && n % 12 == modulo) {
            sieve[n] ^= true;
        }
    }

    /**
     * Eliminates numbers that are multiples of squares from the sieve.
     *
     * All numbers that are multiples of i*i (where i is marked as prime) are
     * marked non-prime to finalize the sieve. This ensures only actual primes remain.
     *
     * @param limit the upper bound for primes
     * @param sqrtLimit square root of the limit
     * @param sieve boolean array representing potential primes
     */
    private static void eliminateMultiplesOfSquares(int limit, int sqrtLimit, boolean[] sieve) {
        for (int i = 5; i <= sqrtLimit; i++) {
            if (!sieve[i]) {
                continue;
            }
            int square = i * i;
            for (int j = square; j <= limit; j += square) {
                sieve[j] = false;
            }
        }
    }
}
