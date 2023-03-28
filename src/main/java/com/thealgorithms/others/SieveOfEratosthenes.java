package com.thealgorithms.others;

import java.util.Arrays;

/**
 * Sieve of Eratosthenes is an ancient algorithm for finding all prime numbers
 * up to any given limit. It does so by iteratively marking as composite (i.e.,
 * not prime) the multiples of each prime, starting with the first prime number,
 * 2. The multiples of a given prime are generated as a sequence of numbers
 * starting from that prime, with constant difference between them that is equal
 * to that prime. This is the sieve's key distinction from using trial division
 * to sequentially test each candidate number for divisibility by each prime.
 * Once all the multiples of each discovered prime have been marked as
 * composites, the remaining unmarked numbers are primes.
 * <p>
 * Poetry about Sieve of Eratosthenes:
 * <p>
 * <i>Sift the Two's and Sift the Three's:</i></p>
 * <p>
 * <i>The Sieve of Eratosthenes.</i></p>
 * <p>
 * <i>When the multiples sublime,</i></p>
 * <p>
 * <i>The numbers that remain are Prime.</i></p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Wiki</a>
 */
import java.util.ArrayList;
import java.util.Arrays;

public class SieveOfEratosthenes {

    /**
     * @param n The number till which we have to check for prime Prints all the
     * prime numbers till n. Should be more than 1.
     * @return ArrayList of all prime numbers between 0 to n
     */
    public static ArrayList<Integer> findPrimesTill(int n) {
        // Create boolean array where index is number and value is flag - is that number a prime or not.
        boolean[] numbers = new boolean[n + 1];

        // Start with assumption that all odd numbers are primes.
        Arrays.fill(numbers, true);
        numbers[0] = numbers[1] = false;
        numbers[2] = true;

        double cap = Math.sqrt(n);
        // Main algorithm: mark all numbers which are multiples of some other primes as not prime
        for (int i = 3; i <= cap; i += 2) {
            if (numbers[i]) {
                for (int j = i * i; j <= n; j += 2 * i) {
                    numbers[j] = false;
                }
            }
        }

        // Write all primes to result list
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i <= n; i += 2) {
            if (numbers[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Searching for all primes from zero to " + n);
        ArrayList<Integer> primes = findPrimesTill(n);
        System.out.println("Found: " + primes);
    }
}
