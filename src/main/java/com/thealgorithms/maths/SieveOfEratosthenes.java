package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class that provides the Sieve of Eratosthenes algorithm.
 */
public final class SieveOfEratosthenes {

    /** Private constructor to prevent instantiation. */
    private SieveOfEratosthenes() {
        throw new AssertionError("Cannot instantiate utility class.");
    }

    /**
     * Returns an array of all prime numbers less than or equal to {@code n}.
     *
     * @param n the upper bound (inclusive)
     * @return array of primes <= n (empty if n &lt; 2)
     */
    public static int[] sieve(final int n) {
        if (n < 2) {
            return new int[0];
        }

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes.stream().mapToInt(Integer::intValue).toArray();
    }
}
