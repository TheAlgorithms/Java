package com.thealgorithms.maths;

/**
 * Sociable numbers are natural numbers that form a cyclic sequence where the
 * sum of proper divisors of each number equals the next number in the sequence,
 * with the sequence eventually returning to the starting number.
 * Amicable numbers are a special case of sociable numbers with a cycle length of 2.
 * Example: (12496, 14288, 15472, 14536, 14264) is a sociable cycle of length 5.
 *
 * @author Vraj Prajapati (@Rosander0)
 * @see <a href="https://en.wikipedia.org/wiki/Sociable_number">Wikipedia: Sociable Number</a>
 * @see AmicableNumber
 */
public final class SociableNumber {

    private SociableNumber() {
        // Utility class
    }

    /**
     * Calculates the sum of proper divisors of a number
     * (all divisors excluding the number itself).
     *
     * @param number the number to calculate proper divisors sum for
     * @return sum of proper divisors, or 0 if number is less than or equal to 1
     */
    static int sumOfProperDivisors(final int number) {
        if (number <= 1) {
            return 0;
        }
        int sum = 1; // 1 is a proper divisor of every number > 1
        final int root = (int) Math.sqrt(number);
        for (int i = 2; i <= root; i++) {
            if (number % i == 0) {
                final int other = number / i;
                sum += i;
                if (other != i) {
                    sum += other;
                }
            }
        }
        return sum;
    }

    /**
     * Checks whether a number is part of a sociable cycle of a given length.
     * Starting from the given number, it follows the chain of proper divisor
     * sums and checks if it returns to the starting number in exactly cycleLength steps.
     *
     * @param number the starting number (must be positive)
     * @param cycleLength the expected cycle length (must be greater than 1)
     * @return true if the number is part of a sociable cycle of given length, false otherwise
     */
    public static boolean isSociable(final int number, final int cycleLength) {
        if (number <= 0 || cycleLength <= 1) {
            return false;
        }
        int current = number;
        for (int i = 0; i < cycleLength; i++) {
            current = sumOfProperDivisors(current);
            if (current == number) {
                return i == cycleLength - 1;
            }
        }
        return false;
    }
}
