package com.thealgorithms.maths;

import java.util.stream.IntStream;

/**
 * In number theory, the aliquot sum s(n) of a positive integer n is the sum of
 * all proper divisors of n, that is, all divisors of n other than n itself. For
 * example, the proper divisors of 15 (that is, the positive divisors of 15 that
 * are not equal to 15) are 1, 3 and 5, so the aliquot sum of 15 is 9 i.e. (1 +
 * 3 + 5). Wikipedia: https://en.wikipedia.org/wiki/Aliquot_sum
 */
public class AliquotSum {

    /**
     * Finds the aliquot sum of an integer number.
     *
     * @param number a positive integer
     * @return aliquot sum of given {@code number}
     */
    public static int getAliquotValue(int number) {
        var sumWrapper = new Object() {
            int value = 0;
        };

        IntStream
            .iterate(1, i -> ++i)
            .limit(number / 2)
            .filter(i -> number % i == 0)
            .forEach(i -> sumWrapper.value += i);

        return sumWrapper.value;
    }
    
     /**
     * Function to calculate the aliquot sum of an integer number
     *
     * @param n a positive integer
     * @return aliquot sum of given {@code number}
     */
    public static int getAliquotSum(int n) {
        if (n <= 0)
            return -1;
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
        return sum;
    }
}
