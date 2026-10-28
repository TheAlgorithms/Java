package com.thealgorithms.maths;

import com.thealgorithms.maths.Prime.PrimeCheck;

/**
 * A utility class to check whether a number is a Germain prime or a Safe prime.
 *
 * <p>This class provides methods to:
 * <ul>
 *   <li>Check if a number is a Germain prime</li>
 *   <li>Check if a number is a Safe prime</li>
 * </ul>
 *
 * <p>Definitions:
 * <ul>
 *   <li>A Germain prime is a prime number p such that 2p + 1 is also prime.</li>
 *   <li>A Safe prime is a prime number p such that (p - 1) / 2 is also prime.</li>
 * </ul>
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Safe_and_Sophie_Germain_primes">Wikipedia: Safe and Sophie Germain primes</a>
 */
public final class GermainPrimeAndSafePrime {

    // Private constructor to prevent instantiation
    private GermainPrimeAndSafePrime() {
    }

    /**
     * Checks if a number is a Germain prime.
     *
     * <p>A Germain prime is a prime number p such that 2p + 1 is also prime.
     *
     * @param number the number to check; must be a positive integer
     * @return {@code true} if the number is a Germain prime, {@code false} otherwise
     * @throws IllegalArgumentException if the input number is less than 1
     */
    public static boolean isGermainPrime(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Input value must be a positive integer. Input value: " + number);
        }
        // A number is a Germain prime if it is prime and 2 * number + 1 is also prime
        return PrimeCheck.isPrime(number) && PrimeCheck.isPrime(2 * number + 1);
    }

    /**
     * Checks if a number is a Safe prime.
     *
     * <p>A Safe prime is a prime number p such that (p - 1) / 2 is also prime.
     *
     * @param number the number to check; must be a positive integer
     * @return {@code true} if the number is a Safe prime, {@code false} otherwise
     * @throws IllegalArgumentException if the input number is less than 1
     */
    public static boolean isSafePrime(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Input value must be a positive integer. Input value: " + number);
        }
        // A number is a Safe prime if it is prime, (number - 1) is even, and (number - 1) / 2 is prime
        return ((number - 1) % 2 == 0) && PrimeCheck.isPrime(number) && PrimeCheck.isPrime((number - 1) / 2);
    }
}
