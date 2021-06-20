package ProjectEuler;

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n). If d(a) = b
 * and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * <p>
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The
 * proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * <p>
 * Evaluate the sum of all the amicable numbers under 10000.
 * <p>
 * link: https://projecteuler.net/problem=21
 */
public class Problem21 {
    public static void main(String[] args) {
        long[][] testNumbers = {
                {100, 0},
                {1000, 504},
                {10000, 31626}
        };

        for (long[] testNumber : testNumbers) {
            assert solution1(testNumber[0]) == testNumber[1];
        }
    }

    /***
     * Finds the sum of proper divisors
     * @param n the number
     * @return the sum
     */
    private static int sumOfProperDivisors(int n) {
        int result = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // if the divisor is sqare root, the add once.
                // otherwise add both divisors.
                if (i == Math.sqrt(n)) {
                    result += i;
                } else {
                    result += (i + n / i);
                }
            }
        }
        return (result);
    }

    private static long solution1(long n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            if (sumOfProperDivisors(sumOfProperDivisors(i)) == i && sumOfProperDivisors(i) != i) {
                sum += i;
            }
        }
        return sum;
    }
}
