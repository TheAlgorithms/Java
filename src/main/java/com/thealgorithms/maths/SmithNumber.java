package com.thealgorithms.maths;

import com.thealgorithms.maths.Prime.PrimeCheck;

/**
 * In number theory, a smith number  is a composite number for which, in a given number base,
 *  the sum of its digits is equal to the sum of the digits in its prime factorization in the same base.
 *
 * For example, in base 10, 378 = 21 X 33 X 71 is a Smith number since 3 + 7 + 8 = 2 X 1 + 3 X 3 + 7 X 1,
 * and 22 = 21 X 111 is a Smith number, because 2 + 2 = 2 X 1 + (1 + 1) X 1.
 *
 * Wiki: https://en.wikipedia.org/wiki/Smith_number
 */
public final class SmithNumber {

    private SmithNumber() {
    }

    private static int primeFactorDigitSum(int n) {
        int sum = 0;
        int num = n;

        // Factorize the number using trial division
        for (int i = 2; i * i <= num; i++) {
            while (n % i == 0) { // while i divides n
                sum += SumOfDigits.sumOfDigits(i); // add sum of digits of factor
                n /= i; // divide n by the factor
            }
        }

        // If n is still > 1, it itself is a prime factor
        if (n > 1) {
            sum += SumOfDigits.sumOfDigits(n);
        }

        return sum;
    }

    /**
     * Check if {@code number} is Smith number or not
     *
     * @param number the number
     * @return {@code true} if {@code number} is a Smith number, otherwise false
     */
    public static boolean isSmithNumber(int number) {
        if (PrimeCheck.isPrime(number)) {
            return false; // Smith numbers must be composite
        }

        return SumOfDigits.sumOfDigits(number) == primeFactorDigitSum(number);
    }
}
