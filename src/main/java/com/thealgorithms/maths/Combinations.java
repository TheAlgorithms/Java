package com.thealgorithms.maths;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Combination">Combination</a>
 */
public class Combinations {

    /**
     * Calculate of factorial
     *
     * @param n the number
     * @return factorial of given number
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number is negative");
        }
        return n == 0 || n == 1 ? 1 : n * factorial(n - 1);
    }

    /**
     * Calculate combinations
     *
     * @param n first number
     * @param k second number
     * @return combinations of given {@code n} and {@code k}
     */
    public static long combinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    /**
     * The above method can exceed limit of long (overflow) when factorial(n) is
     * larger than limits of long variable. Thus even if nCk is within range of
     * long variable above reason can lead to incorrect result. This is an
     * optimized version of computing combinations. Observations: nC(k + 1) = (n
     * - k) * nCk / (k + 1) We know the value of nCk when k = 1 which is nCk = n
     * Using this base value and above formula we can compute the next term
     * nC(k+1)
     *
     * @param n
     * @param k
     * @return nCk
     */
    public static long combinationsOptimized(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("n or k can't be negative");
        }
        if (n < k) {
            throw new IllegalArgumentException("n can't be smaller than k");
        }
        // nC0 is always 1
        long solution = 1;
        for (int i = 0; i < k; i++) {
            long next = (n - i) * solution / (i + 1);
            solution = next;
        }
        return solution;
    }
}
