package com.thealgorithms.maths;

/**
 * In number theory, a perfect number is a positive integer that is equal to the
 * sum of its positive divisors, excluding the number itself. For instance, 6
 * has divisors 1, 2 and 3 (excluding itself), and 1 + 2 + 3 = 6, so 6 is a
 * perfect number.
 *
 * link:https://en.wikipedia.org/wiki/Perfect_number
 */
public class PerfectNumber {

    /**
     * Check if {@code number} is perfect number or not
     *
     * @param number the number
     * @return {@code true} if {@code number} is perfect number, otherwise false
     */
    public static boolean isPerfectNumber(int number) {
        if (number <= 0)
            return false;
        int sum = 0;
        /* sum of its positive divisors */
        for (int i = 1; i < number; ++i) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
    
    /**
     * Check if {@code n} is perfect number or not
     *
     * @param n the number
     * @return {@code true} if {@code number} is perfect number, otherwise false
     */
    public static boolean isPerfectNumber2(int n) {
        if (n <= 0)
            return false;
        int sum = 1;
        double root = Math.sqrt(n);
        
        /*
         * We can get the factors after the root by dividing number by its factors
         * before the root.
         * Ex- Factors of 100 are 1, 2, 4, 5, 10, 20, 25, 50 and 100.
         * Root of 100 is 10. So factors before 10 are 1, 2, 4 and 5.
         * Now by dividing 100 by each factor before 10 we get:
         * 100/1 = 100, 100/2 = 50, 100/4 = 25 and 100/5 = 20
         * So we get 100, 50, 25 and 20 which are factors of 100 after 10
         */
        for (int i = 2; i <= root; i++) {
            if (n % i == 0) {
                sum += i + n / i;
            }
        }
        
        // if n is a perfect square then its root was added twice in above loop, so subtracting root from sum
        if (root == (int) root)
            sum -= root;

        return sum == n;
    }
}
