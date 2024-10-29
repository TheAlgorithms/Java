package com.thealgorithms.maths;

import java.util.List;

/**
 * @brief Implementation of the Chinese Remainder Theorem (CRT) algorithm
 * @details
 * The Chinese Remainder Theorem (CRT) is used to solve systems of
 * simultaneous congruences. Given several pairwise coprime moduli
 * and corresponding remainders, the algorithm finds the smallest
 * positive solution.
 */
public final class ChineseRemainderTheorem {
    private ChineseRemainderTheorem() {
    }

    /**
     * @brief Solves the Chinese Remainder Theorem problem.
     * @param remainders The list of remainders.
     * @param moduli The list of pairwise coprime moduli.
     * @return The smallest positive solution that satisfies all the given congruences.
     */
    public static int solveCRT(List<Integer> remainders, List<Integer> moduli) {
        int product = 1;
        int result = 0;

        // Calculate the product of all moduli
        for (int mod : moduli) {
            product *= mod;
        }

        // Apply the formula for each congruence
        for (int i = 0; i < moduli.size(); i++) {
            int partialProduct = product / moduli.get(i);
            int inverse = modInverse(partialProduct, moduli.get(i));
            result += remainders.get(i) * partialProduct * inverse;
        }

        // Adjust result to be the smallest positive solution
        result = result % product;
        if (result < 0) {
            result += product;
        }

        return result;
    }

    /**
     * @brief Computes the modular inverse of a number with respect to a modulus using
     * the Extended Euclidean Algorithm.
     * @param a The number for which to find the inverse.
     * @param m The modulus.
     * @return The modular inverse of a modulo m.
     */
    private static int modInverse(int a, int m) {
        int m0 = m;
        int x0 = 0;
        int x1 = 1;

        if (m == 1) {
            return 0;
        }

        while (a > 1) {
            int q = a / m;
            int t = m;

            // m is remainder now, process same as Euclid's algorithm
            m = a % m;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        // Make x1 positive
        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }
}
