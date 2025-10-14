package com.thealgorithms.maths;

/**
 * Utility class for checking if a number is a Krishnamurthy number.
 *
 * <p>
 * A Krishnamurthy number (also known as a Strong number or Factorion) is a
 * number
 * whose sum of the factorials of its digits is equal to the number itself.
 * </p>
 *
 * <p>
 * For example, 145 is a Krishnamurthy number because 1! + 4! + 5! = 1 + 24 +
 * 120 = 145.
 * </p>
 *
 * <p>
 * The only Krishnamurthy numbers in base 10 are: 1, 2, 145, and 40585.
 * </p>
 *
 * <p>
 * <b>Example usage:</b>
 * </p>
 *
 * <pre>
 * boolean isKrishnamurthy = KrishnamurthyNumber.isKrishnamurthy(145);
 * System.out.println(isKrishnamurthy); // Output: true
 *
 * isKrishnamurthy = KrishnamurthyNumber.isKrishnamurthy(123);
 * System.out.println(isKrishnamurthy); // Output: false
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Factorion">Factorion
 *      (Wikipedia)</a>
 */
public final class KrishnamurthyNumber {

    // Pre-computed factorials for digits 0-9 to improve performance
    private static final int[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    private KrishnamurthyNumber() {
    }

    /**
     * Checks if a number is a Krishnamurthy number.
     *
     * <p>
     * A number is a Krishnamurthy number if the sum of the factorials of its digits
     * equals the number itself.
     * </p>
     *
     * @param n the number to check
     * @return true if the number is a Krishnamurthy number, false otherwise
     */
    public static boolean isKrishnamurthy(int n) {
        if (n <= 0) {
            return false;
        }

        int original = n;
        int sum = 0;

        while (n != 0) {
            int digit = n % 10;
            sum = sum + FACTORIALS[digit];
            n = n / 10;
        }

        return sum == original;
    }
}
