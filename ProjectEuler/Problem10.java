package ProjectEuler;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two million.
 * <p>
 * link: https://projecteuler.net/problem=10
 */
public class Problem10 {
    public static void main(String[] args) {
        long[][] testNumbers = {
                {2000000, 142913828922L},
                {10000, 5736396},
                {5000, 1548136},
                {1000, 76127},
                {10, 17},
                {7, 10}
        };

        for (long[] testNumber : testNumbers) {
            assert solution1(testNumber[0]) == testNumber[1];
        }
    }

    /***
     * Checks if a number is prime or not
     * @param n the number
     * @return {@code true} if {@code n} is prime
     */
    private static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        if (n < 2 || n % 2 == 0) {
            return false;
        }
        for (int i = 3, limit = (int) Math.sqrt(n); i <= limit; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long solution1(long n) {
        long sum = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }
}