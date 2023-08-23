package com.thealgorithms.maths;

import java.util.Random;

public class MillerRabinPrimalityTest {

    /**
     * Check whether the given number is prime or not
     * MillerRabin algorithm is probabilistic. There is also an altered version which is deterministic.
     * https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
     *
     * @param n Whole number which is tested on primality
     * @param k Number of iterations
     *       If n is composite then running k iterations of the Miller–Rabin
     *       test will declare n probably prime with a probability at most 4^(−k)
     * @return true or false whether the given number is probably prime or not
     */

    public static boolean millerRabin(long n, int k) { // returns true if n is probably prime, else returns false.
        if (n < 4)
            return n == 2 || n == 3;

        int s = 0;
        long d = n - 1;
        while ((d & 1) == 0) {
            d >>= 1;
            s++;
        }
        Random rnd = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + rnd.nextLong() % (n - 3);
            if (checkComposite(n, a, d, s))
                return false;
        }
        return true;
    }

    public static boolean deterministicMillerRabin(long n) { // returns true if n is prime, else returns false.
        if (n < 2)
            return false;

        int r = 0;
        long d = n - 1;
        while ((d & 1) == 0) {
            d >>= 1;
            r++;
        }

        for (int a : new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37}) {
            if (n == a)
                return true;
            if (checkComposite(n, a, d, r))
                return false;
        }
        return true;
    }

    private static boolean checkComposite(long n, long a, long d, int s) {
        long x = power(a, d, n);
        if (x == 1 || x == n - 1)
            return false;
        for (int r = 1; r < s; r++) {
            x = power(x, 2, n);
            if (x == n - 1)
                return false;
        }
        return true;
    }

    private static long power(long x, long y, long p) {
        long res = 1;     // Initialize result

        x = x % p; // Update x if it is more than or
        // equal to p

        if (x == 0) return 0; // In case x is divisible by p;

        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = modulo_multiplication(res, x, p);

            // y must be even now
            y = y>>1; // y = y/2
            x = modulo_multiplication(x, x, p);
        }
        return res;
    }

    private static long modulo_multiplication(long a, long b, long m) {
        long a_hi = a >> 24, a_lo = a & ((1 << 24) - 1);
        long b_hi = b >> 24, b_lo = b & ((1 << 24) - 1);
        long result = ((((a_hi*b_hi << 16) % m) << 16) % m) << 16;
        result += ((a_lo*b_hi+a_hi*b_lo) << 24) + a_lo*b_lo;
        return result % m;
    }

    public static void main(String[] args) {
        // check primes
        assert deterministicMillerRabin(2);
        assert deterministicMillerRabin(37);
        assert deterministicMillerRabin(123457);
        assert deterministicMillerRabin(6472601713L);

        // check not primes
        assert !deterministicMillerRabin(1);
        assert !deterministicMillerRabin(35);
        assert !deterministicMillerRabin(123453);
        assert !deterministicMillerRabin(647260175);

        Random rnd = new Random();
        int iterations = 1; // usually it is 5, but let's pick 1 for testing purposes
        for (int i = 0; i < 100; i++) {
            long number = rnd.nextLong(0, 3000);
            if (!millerRabin(number, iterations)) {
                System.out.println(number + " is not a prime");
            }
            else if (deterministicMillerRabin(number)) {
                System.out.println(number + " is a prime");
            }
            else {
                System.out.println(number + " is not a prime, but Miller-Rabin said it is");
            }
        }
    }
}
