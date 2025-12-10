package com.thealgorithms.maths.Prime;

import java.util.Random;

public final class MillerRabinPrimalityCheck {
    private MillerRabinPrimalityCheck() {
    }

    /**
     * Check whether the given number is prime or not
     * MillerRabin algorithm is probabilistic. There is also an altered version which is deterministic.
     * https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
     * https://cp-algorithms.com/algebra/primality_tests.html
     *
     * @param n Whole number which is tested on primality
     * @param k Number of iterations
     *       If n is composite then running k iterations of the Miller–Rabin
     *       test will declare n probably prime with a probability at most 4^(−k)
     * @return true or false whether the given number is probably prime or not
     */

    public static boolean millerRabin(long n, int k) { // returns true if n is probably prime, else returns false.
        if (n < 4) {
            return n == 2 || n == 3;
        }

        int s = 0;
        long d = n - 1;
        while ((d & 1) == 0) {
            d >>= 1;
            s++;
        }
        Random rnd = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + rnd.nextLong(n) % (n - 3);
            if (checkComposite(n, a, d, s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean deterministicMillerRabin(long n) { // returns true if n is prime, else returns false.
        if (n < 2) {
            return false;
        }

        int r = 0;
        long d = n - 1;
        while ((d & 1) == 0) {
            d >>= 1;
            r++;
        }

        for (int a : new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37}) {
            if (n == a) {
                return true;
            }
            if (checkComposite(n, a, d, r)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if number n is composite (probabilistic)
     *
     * @param n Whole number which is tested for compositeness
     * @param a Random number (prime base) to check if it holds certain equality
     * @param d Number which holds this equation: 'n - 1 = 2^s * d'
     * @param s Number of twos in (n - 1) factorization
     *
     * @return true or false whether the numbers hold the equation or not
     *          the equations are described on the websites mentioned at the beginning of the class
     */
    private static boolean checkComposite(long n, long a, long d, int s) {
        long x = powerModP(a, d, n);
        if (x == 1 || x == n - 1) {
            return false;
        }
        for (int r = 1; r < s; r++) {
            x = powerModP(x, 2, n);
            if (x == n - 1) {
                return false;
            }
        }
        return true;
    }

    private static long powerModP(long x, long y, long p) {
        long res = 1; // Initialize result

        x = x % p; // Update x if it is more than or equal to p

        if (x == 0) {
            return 0; // In case x is divisible by p;
        }
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1) {
                res = multiplyModP(res, x, p);
            }

            // y must be even now
            y = y >> 1; // y = y/2
            x = multiplyModP(x, x, p);
        }
        return res;
    }

    private static long multiplyModP(long a, long b, long p) {
        long aHi = a >> 24;
        long aLo = a & ((1 << 24) - 1);
        long bHi = b >> 24;
        long bLo = b & ((1 << 24) - 1);
        long result = ((((aHi * bHi << 16) % p) << 16) % p) << 16;
        result += ((aLo * bHi + aHi * bLo) << 24) + aLo * bLo;
        return result % p;
    }
}
