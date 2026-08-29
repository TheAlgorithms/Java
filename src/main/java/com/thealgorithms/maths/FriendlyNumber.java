package com.thealgorithms.maths;

/**
 * Two numbers are Friendly if they share the same abundancy index,
 * which is the ratio of the sum of divisors to the number itself.
 * Example: 6 and 28 are friendly because sigma(6)/6 = 2 and sigma(28)/28 = 2
 *
 * @see <a href="https://en.wikipedia.org/wiki/Friendly_number">
 *     Wikipedia: Friendly Number</a>
 *
 * @author Vraj Prajapati @Rosander0
 */
public final class FriendlyNumber {

    private FriendlyNumber() {
        // Utility class
    }

    private static int sumOfDivisors(final int number) {
        int sum = 0;
        final int root = (int) Math.sqrt(number);
        for (int i = 1; i <= root; i++) {
            if (number % i == 0) {
                sum += i;
                final int other = number / i;
                if (other != i) {
                    sum += other;
                }
            }
        }
        return sum;
    }

    /**
     * Checks whether two numbers are Friendly Numbers.
     *
     * @param a First number (must be positive)
     * @param b Second number (must be positive)
     * @return true if a and b are friendly numbers, false otherwise
     */
    public static boolean areFriendly(final int a, final int b) {
        if (a <= 0 || b <= 0) {
            return false;
        }
        final long sigmaA = sumOfDivisors(a);
        final long sigmaB = sumOfDivisors(b);
        return sigmaA * b == sigmaB * a;
    }
}
