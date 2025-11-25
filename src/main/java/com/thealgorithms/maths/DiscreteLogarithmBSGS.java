package com.thealgorithms.maths;

import java.util.HashMap;
import java.util.Map;

/**
 * Baby-Step Giant-Step algorithm for the Discrete Logarithm Problem.
 *
 * <p>
 * Solves for x in: a^x ≡ b (mod m),
 * where a, b, m are given, and m is prime (or a has order in modulo m).
 *
 * <p>
 * Time complexity: O(√m)
 * Space complexity: O(√m)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Baby-step_giant-step">Baby-step giant-step algorithm</a>
 */
public class DiscreteLogarithmBSGS {

    private DiscreteLogarithmBSGS() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Computes x such that (a^x) % m == b.
     * Returns -1 if no solution exists.
     */
    public static long discreteLog(long a, long b, long m) {
        a %= m;
        b %= m;

        if (b == 1) {
            return 0;
        }

        long n = (long) Math.ceil(Math.sqrt(m));

        Map<Long, Long> babySteps = new HashMap<>();

        long value = 1;
        for (long i = 0; i < n; i++) {
            babySteps.put(value, i);
            value = (value * a) % m;
        }

        long factor = modPow(a, m - n - 1, m);

        long gamma = b;
        for (long j = 0; j <= n; j++) {
            if (babySteps.containsKey(gamma)) {
                return j * n + babySteps.get(gamma);
            }
            gamma = (gamma * factor) % m;
        }

        return -1; // no solution
    }

    /** Fast modular exponentiation */
    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
