package ProjectEuler;

import java.math.BigInteger;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 *
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * <p>
 * Find the sum of the digits in the number 100!
 * <p>
 * link: https://projecteuler.net/problem=20
 */
public class Problem20 {
    public static void main(String[] args) {
        long[][] testNumbers = {
                {4, 6},
                {10, 27},
                {100, 648}
        };

        for (long[] testNumber : testNumbers) {
            assert solution1(testNumber[0]) == testNumber[1];
        }
    }

    /***
     * Finds the factorial of a given number
     * @param n the number
     * @return the factorial value
     */
    private static BigInteger factorial(long n) {
        if (n <= 2) {
            return BigInteger.valueOf(n);
        }
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }

    /***
     * Splits the digits and get the sum
     * @param n the number
     * @return the factorial
     */
    private static long splitAndSum(BigInteger n) {
        long sum = 0;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            long lastDigit = n.mod(BigInteger.valueOf(10)).longValue();
            sum += lastDigit;
            n = n.divide(BigInteger.valueOf(10));
        }
        return sum;
    }

    private static long solution1(long number) {
        return splitAndSum(factorial(number));
    }
}
