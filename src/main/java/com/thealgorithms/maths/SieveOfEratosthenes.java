package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * Sieve of Eratosthenes Algorithm
 * An efficient algorithm to find all prime numbers up to a given limit.
 *
 * Algorithm:
 * 1. Create a boolean array of size n+1, initially all true
 * 2. Mark 0 and 1 as not prime
 * 3. For each number i from 2 to sqrt(n):
 *    - If i is still marked as prime
 *    - Mark all multiples of i (starting from i²) as not prime
 * 4. Collect all numbers still marked as prime
 *
 * Time Complexity: O(n log log n)
 * Space Complexity: O(n)
 *
 * @author Navadeep0007
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Sieve of Eratosthenes</a>
 */
public final class SieveOfEratosthenes {

    private SieveOfEratosthenes() {
        // Utility class, prevent instantiation
    }

    /**
     * Finds all prime numbers up to n using the Sieve of Eratosthenes algorithm
     *
     * @param n the upper limit (inclusive)
     * @return a list of all prime numbers from 2 to n
     * @throws IllegalArgumentException if n is negative
     */
    public static List<Integer> findPrimes(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        if (n < 2) {
            return new ArrayList<>();
        }

        // Create boolean array, initially all true
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // Sieve process
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as not prime
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Collect all prime numbers
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Counts the number of prime numbers up to n
     *
     * @param n the upper limit (inclusive)
     * @return count of prime numbers from 2 to n
     */
    public static int countPrimes(int n) {
        return findPrimes(n).size();
    }
}
