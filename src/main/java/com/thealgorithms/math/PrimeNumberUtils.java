package com.thealgorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to work with prime numbers.
 */
public class PrimeNumberUtils {

    /**
     * Checks if a number is a prime.
     * 
     * @param n the number to check
     * @return true if prime, false otherwise
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }

    /**
     * Returns a list of prime numbers up to a given number.
     * 
     * @param limit the upper bound (inclusive)
     * @return list of prime numbers <= limit
     */
    public static List<Integer> getPrimesUpTo(int limit) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println("Is 17 prime? " + isPrime(17));
        System.out.println("Primes up to 30: " + getPrimesUpTo(30));
    }
}
