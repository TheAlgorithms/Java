package com.thealgorithms.maths;

/**
 * @author <a href="https://github.com/siddhant2002">Siddhant Swarup Mallick</a>
 * Program description - To find the FrizzyNumber
 */
public final class FrizzyNumber {
    private FrizzyNumber() {
    }

    /**
     * Returns the n-th number that is a sum of powers
     * of the given base.
     * Example: base = 3 and n = 4
     * Ascending order of sums of powers of 3 =
     * 3^0 = 1, 3^1 = 3, 3^1 + 3^0 = 4, 3^2 + 3^0 = 9
     * Ans = 9
     *
     * @param base The base whose n-th sum of powers is required
     * @param n    Index from ascending order of sum of powers of base
     * @return n-th sum of powers of base
     */
    public static double getNthFrizzy(int base, int n) {
        double final1 = 0.0;
        int i = 0;
        do {
            final1 += Math.pow(base, i++) * (n % 2);
        } while ((n /= 2) > 0);
        return final1;
    }
}
