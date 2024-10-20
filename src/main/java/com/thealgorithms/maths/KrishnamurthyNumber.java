package com.thealgorithms.maths;

/**
 * Utility class for checking if a number is a Krishnamurthy number.
 *
 * A Krishnamurthy number (also known as a Strong number) is a number whose sum of the factorials of its digits is equal to the number itself.
 *
 * For example, 145 is a Krishnamurthy number because 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * <b>Example usage:</b>
 * <pre>
 * boolean isKrishnamurthy = KrishnamurthyNumber.isKrishnamurthy(145);
 * System.out.println(isKrishnamurthy); // Output: true
 *
 * isKrishnamurthy = KrishnamurthyNumber.isKrishnamurthy(123);
 * System.out.println(isKrishnamurthy); // Output: false
 * </pre>
 */
public final class KrishnamurthyNumber {

    private KrishnamurthyNumber() {
    }

    /**
     * Checks if a number is a Krishnamurthy number.
     *
     * @param n The number to check
     * @return true if the number is a Krishnamurthy number, false otherwise
     */
    public static boolean isKrishnamurthy(int n) {
        int tmp = n;
        int s = 0;

        if (n <= 0) {
            return false;
        } else {
            while (n != 0) {
                // initialising the variable fact that will store the factorials of the digits
                int fact = 1;
                // computing factorial of each digit
                for (int i = 1; i <= n % 10; i++) {
                    fact = fact * i;
                }
                // computing the sum of the factorials
                s = s + fact;
                // discarding the digit for which factorial has been calculated
                n = n / 10;
            }

            // evaluating if sum of the factorials of the digits equals the number itself
            return tmp == s;
        }
    }
}
