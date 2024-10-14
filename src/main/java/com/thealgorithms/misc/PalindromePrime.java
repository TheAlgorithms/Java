package com.thealgorithms.misc;

/**
 * Utility class for finding palindromic prime numbers.
 *
 * Palindromic primes are prime numbers that remain the same when their digits are reversed.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Palindromic_prime">Palindromic prime</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int[] palindromicPrimes = PalindromePrime.findPalindromicPrimes(5);
 * System.out.println(Arrays.toString(palindromicPrimes)); // Output: [2, 3, 5, 7, 11]
 *
 * int nthPalindromicPrime = PalindromePrime.findNthPalindromicPrime(5);
 * System.out.println(nthPalindromicPrime); // Output: 11
 * </pre>
 */
public final class PalindromePrime {

    private PalindromePrime() {
        // Private constructor to prevent instantiation
    }

    /**
     * Finds the first n palindromic prime numbers.
     *
     * @param n The number of palindromic primes to find
     * @return An array containing the first n palindromic prime numbers
     */
    public static int[] findPalindromicPrimes(int n) {
        if (n == 0) {
            return new int[0];
        }
        int[] result = new int[n];
        result[0] = 2; // The first palindromic prime
        int count = 1;
        int num = 3;
        while (count < n) {
            if (num == reverse(num) && isPrime(num)) { // number is prime and its reverse is the same
                result[count] = num;
                count++; // counts check when to terminate while loop
            }
            num += 2; // increase iterator value by two
        }
        return result;
    }

    /**
     * Finds the nth palindromic prime number.
     *
     * @param n The position of the palindromic prime to find
     * @return The nth palindromic prime number
     */
    public static int findNthPalindromicPrime(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (n == 1) {
            return 2; // The first palindromic prime
        }
        int count = 1;
        int num = 3;
        while (count < n) {
            if (num == reverse(num) && isPrime(num)) { // number is prime and its reverse is the same
                count++; // counts check when to terminate while loop
            }
            if (count < n) {
                num += 2; // increase iterator value by two
            }
        }
        return num;
    }

    /**
     * Checks if a number is prime.
     *
     * @param num The number to check
     * @return true if the number is prime, false otherwise
     */
    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for (int divisor = 3; divisor <= Math.sqrt(num); divisor += 2) {
            if (num % divisor == 0) {
                return false; // false if not prime
            }
        }
        return true; // true if prime
    }

    /**
     * Reverses the digits of a number.
     *
     * @param num The number to reverse
     * @return The reversed number
     */
    private static int reverse(int num) {
        int reversed = 0;
        while (num != 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }
}
