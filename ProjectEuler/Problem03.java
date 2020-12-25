package ProjectEuler;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * <p>What is the largest prime factor of the number 600851475143 ?
 *
 * <p>Link: https://projecteuler.net/problem=3
 */
public class Problem03 {
    /**
     * Check if a given number is prime
     *
     * @param n integer number
     * @return true if n is prime, false otherwise
     */
    static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * Calculate all the prime factors of a number and return the largest
     *
     * @param n integer number
     * @return the maximum prime factor of the given number
     */
    static long maxPrimeFactor(long n) {
        for (int i = 2; i < n/2; i++) {
            if (isPrime(i))
                while (n % i == 0) {
                    n /= i;
                }
        }
        return n;
    }

    public static void main(String[] args) {
        long c = 600851475143L;
        int[][] testNumbers = {
                {87, 29},
                {10, 5},
                {21, 7},
                {657, 73},
                {777, 37}
        };
        for (int[] num : testNumbers) {
            assert Problem03.maxPrimeFactor(num[0]) == num[1];
        }
        assert Problem03.maxPrimeFactor(c) == 6857;
    }
}
