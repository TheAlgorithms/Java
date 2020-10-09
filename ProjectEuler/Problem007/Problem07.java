package ProjectEuler;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * <p>
 * What is the 10 001st prime number?
 * <p>
 * link: https://projecteuler.net/problem=7
 */
public class Problem07 {
    public static void main(String[] args) {
        int[][] testNumbers = {
                {1, 2},
                {2, 3},
                {3, 5},
                {4, 7},
                {5, 11},
                {6, 13},
                {20, 71},
                {50, 229},
                {100, 541}
        };
        for (int[] number : testNumbers) {
            assert solution1(number[0]) == number[1];
        }
    }

    /***
     * Checks if a number is prime or not
     * @param number the number
     * @return {@code true} if {@code number} is prime
     */
    private static boolean isPrime(int number) {
        if (number == 2) {
            return true;
        }
        if (number < 2 || number % 2 == 0) {
            return false;
        }
        for (int i = 3, limit = (int) Math.sqrt(number); i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int solution1(int n) {
        int count = 0;
        int number = 1;

        while (count != n) {
            if (isPrime(++number)) {
                count++;
            }
        }
        return number;
    }
}