package com.thealgorithms.others;

import java.util.Arrays;

/**
 * Sieve of Eratosthenes is an ancient algorithm for finding all prime numbers
 * up to any given limit.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Wiki</a>
 */
public class SieveOfEratosthenes {

    /**
     * Finds all prime numbers till n.
     *
     * @param n The number till which we have to check for primes. Should be more than 1.
     * @return Array of all prime numbers between 0 to n.
     */
    public static int[] findPrimesTill(int n) {
        Type[] numbers = new Type[n + 1];
        Arrays.fill(numbers, Type.PRIME);
        numbers[0] = numbers[1] = Type.NOT_PRIME;

        double cap = Math.sqrt(n);
        for (int i = 2; i <= cap; i++) {
            if (numbers[i] == Type.PRIME) {
                for (int j = 2; i * j <= n; j++) {
                    numbers[i * j] = Type.NOT_PRIME;
                }
            }
        }

        int primesCount = (int) Arrays.stream(numbers).filter(element -> element == Type.PRIME).count();
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
