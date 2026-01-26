package com.thealgorithms.maths;

/**
 * In number theory, an abundant number or excessive number is a positive integer for which
 * the sum of its proper divisors is greater than the number.
 * Equivalently, it is a number for which the sum of proper divisors (or aliquot sum) is greater than n.
 *
 * The integer 12 is the first abundant number. Its proper divisors are 1, 2, 3, 4 and 6 for a total of 16.
 *
 * Wiki: https://en.wikipedia.org/wiki/Abundant_number
 */
public final class AbundantNumber {

    private AbundantNumber() {
    }

    // Function to calculate sum of all divisors including n
    private static int sumOfDivisors(int n) {
        int sum = 1 + n; // 1 and n are always divisors
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i; // adding divisor to sum
            }
        }
        return sum;
    }

    // Common validation method
    private static void validatePositiveNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive.");
        }
    }

    /**
     * Check if {@code number} is an Abundant number or not by checking sum of divisors > 2n
     *
     * @param number the number
     * @return {@code true} if {@code number} is an Abundant number, otherwise false
     */
    public static boolean isAbundant(int number) {
        validatePositiveNumber(number);

        return sumOfDivisors(number) > 2 * number;
    }

    /**
     * Check if {@code number} is an Abundant number or not by checking Aliquot Sum > n
     *
     * @param number the number
     * @return {@code true} if {@code number} is a Abundant number, otherwise false
     */
    public static boolean isAbundantNumber(int number) {
        validatePositiveNumber(number);

        return AliquotSum.getAliquotSum(number) > number;
    }
}
