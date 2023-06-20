package com.thealgorithms.others;

import java.util.Arrays;

/**
 * Sieve of Eratosthenes is an ancient algorithm for finding all prime numbers
 * up to any given limit.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Wiki</a>
 */
public class SieveOfEratosthenes {
    private static void checkInput(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }
    }

    private static Type[] sievePrimesFill(int n) {
        checkInput(n);
        Type[] isPrimeArray = new Type[n + 1];
        Arrays.fill(isPrimeArray, Type.PRIME);
        isPrimeArray[0] = isPrimeArray[1] = Type.NOT_PRIME;

        double cap = Math.sqrt(n);
        for (int i = 2; i <= cap; i++) {
            if (isPrimeArray[i] == Type.PRIME) {
                for (int j = 2; i * j <= n; j++) {
                    isPrimeArray[i * j] = Type.NOT_PRIME;
                }
            }
        }
        return isPrimeArray;
    }

    private static int countPrimes(Type[] isPrimeArray) {
        return (int) Arrays.stream(isPrimeArray).filter(element -> element == Type.PRIME).count();
    }

    private static int[] extractPrimes(Type[] isPrimeArray) {
        int numberOfPrimes = countPrimes(isPrimeArray);
        int[] primes = new int[numberOfPrimes];
        int primeIndex = 0;
        for (int curNumber = 0; curNumber < isPrimeArray.length; ++curNumber) {
            if (isPrimeArray[curNumber] == Type.PRIME) {
                primes[primeIndex++] = curNumber;
            }
        }
        return primes;
    }

    /**
     * Finds all prime numbers till n.
     *
     * @param n The number till which we have to check for primes. Should be more than 1.
     * @return Array of all prime numbers between 0 to n.
     */
    public static int[] findPrimesTill(int n) {
        return extractPrimes(sievePrimesFill(n));
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
