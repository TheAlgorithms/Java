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
public class SieveOfEratosthenes {

    /**
     * @param n The number till which we have to check for prime Prints all the
     * prime numbers till n. Should be more than 1.
     * @return array of all prime numbers between 0 to n
     */
    public static int[] findPrimesTill(int n) {
        // Create array where index is number and value is flag - is that number a prime or not.
        // size of array is n + 1 cause in Java array indexes starts with 0
        Type[] numbers = new Type[n + 1];

        // Start with assumption that all numbers except 0 and 1 are primes.
        Arrays.fill(numbers, Type.PRIME);
        numbers[0] = numbers[1] = Type.NOT_PRIME;

        double cap = Math.sqrt(n);
        // Main algorithm: mark all numbers which are multiples of some other values as not prime
        for (int i = 2; i <= cap; i++) {
            if (numbers[i] == Type.PRIME) {
                for (int j = 2; i * j <= n; j++) {
                    numbers[i * j] = Type.NOT_PRIME;
                }
            }
        }

        //Write all primes to result array
        int primesCount = (int) Arrays
            .stream(numbers)
            .filter(element -> element == Type.PRIME)
            .count();
        int[] primes = new int[primesCount];

        int primeIndex = 0;
        for (int i = 0; i < n + 1; i++) {
            if (numbers[i] == Type.PRIME) {
                primes[primeIndex++] = i;
            }
        }

        return primes;
    }

    private enum Type {
        PRIME,
        NOT_PRIME,
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Searching for all primes from zero to " + n);
        int[] primes = findPrimesTill(n);
        System.out.println("Found: " + Arrays.toString(primes));
    }
}
