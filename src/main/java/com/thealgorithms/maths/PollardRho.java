package com.thealgorithms.maths;

/*
 * Java program for pollard rho algorithm
 * The algorithm is used to factorize a number n = pq,
 * where p is a non-trivial factor.
 * Pollard's rho algorithm is an algorithm for integer factorization
 * and it takes as its inputs n, the integer to be factored;
 * and g(x), a polynomial in x computed modulo n.
 * In the original algorithm, g(x) = ((x ^ 2) − 1) mod n,
 * but nowadays it is more common to use g(x) = ((x ^ 2) + 1 ) mod n.
 * The output is either a non-trivial factor of n, or failure.
 * It performs the following steps:
 *     x ← 2
 *     y ← 2
 *     d ← 1

 *     while d = 1:
 *         x ← g(x)
 *         y ← g(g(y))
 *         d ← gcd(|x - y|, n)

 *     if d = n:
 *         return failure
 *     else:
 *         return d

 * Here x and y corresponds to xi and xj in the previous section.
 * Note that this algorithm may fail to find a nontrivial factor even when n is composite.
 * In that case, the method can be tried again, using a starting value other than 2 or a different
 g(x)
 *
 * Wikipedia: https://en.wikipedia.org/wiki/Pollard%27s_rho_algorithm
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */
public class PollardRho {

    /**
     * This method returns a polynomial in x computed modulo n
     *
     * @param base Integer base of the polynomial
     * @param modulus Integer is value which is to be used to perform modulo operation over the
     *     polynomial
     * @return Integer (((base * base) - 1) % modulus)
     */
    static int g(int base, int modulus) {
        return ((base * base) - 1) % modulus;
    }

    /**
     * This method returns a non-trivial factor of given integer number
     *
     * @param number Integer is a integer value whose non-trivial factor is to be found
     * @return Integer non-trivial factor of number
     * @throws RuntimeException object if GCD of given number cannot be found
     */
    static int pollardRho(int number) {
        int x = 2, y = 2, d = 1;
        while (d == 1) {
            // tortoise move
            x = g(x, number);

            // hare move
            y = g(g(y, number), number);

            // check GCD of |x-y| and number
            d = GCD.gcd(Math.abs(x - y), number);
        }
        if (d == number) {
            throw new RuntimeException("GCD cannot be found.");
        }
        return d;
    }
}
