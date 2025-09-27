package com.thealgorithms.bitmanipulation;

import java.math.BigInteger;

/**
 * Bitwise GCD implementation with full-range support utilities.
 *
 * <p>This class provides a fast binary (Stein's) GCD implementation for {@code long}
 * inputs and a BigInteger-backed API for full 2's-complement range support (including
 * {@code Long.MIN_VALUE}). The {@code long} implementation is efficient and avoids
 * division/modulo operations. For edge-cases that overflow signed-64-bit ranges
 * (e.g., gcd(Long.MIN_VALUE, 0) = 2^63), use the BigInteger API {@code gcdBig}.
 *
 * <p>Behaviour:
 * <ul>
 *   <li>{@code gcd(long,long)} : returns non-negative {@code long} gcd for inputs whose
 *       absolute values fit in signed {@code long} (i.e., not causing an unsigned 2^63 result).
 *       If the true gcd does not fit in a signed {@code long} (for example gcd(Long.MIN_VALUE,0) = 2^63)
 *       this method will delegate to BigInteger and throw {@link ArithmeticException} if the
 *       BigInteger result does not fit into a signed {@code long}.</li>
 *   <li>{@code gcdBig(BigInteger, BigInteger)} : returns the exact gcd as a {@link BigInteger}
 *       and works for the full signed-64-bit range and beyond.</li>
 * </ul>
 */
public final class BitwiseGCD {

    private BitwiseGCD() {
    }

    /**
     * Computes GCD of two long values using Stein's algorithm (binary GCD).
     * <p>Handles negative inputs. If either input is {@code Long.MIN_VALUE} the
     * method delegates to the BigInteger implementation and will throw {@link ArithmeticException}
     * if the result cannot be represented as a signed {@code long}.
     *
     * @param a first value (may be negative)
     * @param b second value (may be negative)
     * @return non-negative gcd as a {@code long}
     * @throws ArithmeticException when the exact gcd does not fit into a signed {@code long}
     */
    public static long gcd(long a, long b) {
        // Trivial cases
        if (a == 0L) {
            return absOrThrowIfOverflow(b);
        }
        if (b == 0L) {
            return absOrThrowIfOverflow(a);
        }

        // If either is Long.MIN_VALUE, absolute value doesn't fit into signed long.
        if (a == Long.MIN_VALUE || b == Long.MIN_VALUE) {
            // Delegate to BigInteger and try to return a long if it fits
            BigInteger g = gcdBig(BigInteger.valueOf(a), BigInteger.valueOf(b));
            return g.longValueExact();
        }

        // Work with non-negative long values now (safe because we excluded Long.MIN_VALUE)
        a = (a < 0) ? -a : a;
        b = (b < 0) ? -b : b;

        // Count common factors of 2
        int commonTwos = Long.numberOfTrailingZeros(a | b);

        // Remove all factors of 2 from a
        a >>= Long.numberOfTrailingZeros(a);

        while (b != 0L) {
            // Remove all factors of 2 from b
            b >>= Long.numberOfTrailingZeros(b);

            // Now both a and b are odd. Ensure a <= b
            if (a > b) {
                long tmp = a;
                a = b;
                b = tmp;
            }

            // b >= a; subtract a from b (result is even)
            b = b - a;
        }

        // Restore common powers of two
        return a << commonTwos;
    }

    /**
     * Helper to return absolute value of x unless x == Long.MIN_VALUE, in which
     * case we delegate to BigInteger and throw to indicate overflow.
     */
    private static long absOrThrowIfOverflow(long x) {
        if (x == Long.MIN_VALUE) {
            // |Long.MIN_VALUE| = 2^63 which does not fit into signed long
            throw new ArithmeticException("Absolute value of Long.MIN_VALUE does not fit into signed long. Use gcdBig() for full-range support.");
        }
        return (x < 0) ? -x : x;
    }

    /**
     * Computes GCD for an array of {@code long} values. Returns 0 for empty/null arrays.
     * If any intermediate gcd cannot be represented in signed long (rare), an ArithmeticException
     * will be thrown.
     */
    public static long gcd(long... values) {

        if (values == null || values.length == 0) {
            return 0L;
        }
        long result = values[0];
        for (int i = 1; i < values.length; i++) {
            result = gcd(result, values[i]);
            if (result == 1L) {
                return 1L; // early exit
            }
        }
        return result;
    }

    /**
     * BigInteger-backed gcd that works for the full integer range (and beyond).
     * This is the recommended method when inputs may be Long.MIN_VALUE or when you
     * need an exact result even if it is greater than Long.MAX_VALUE.
     * @param a first value (may be negative)
     * @param b second value (may be negative)
     * @return non-negative gcd as a {@link BigInteger}
     */
    public static BigInteger gcdBig(BigInteger a, BigInteger b) {

        if (a == null || b == null) {
            throw new NullPointerException("Arguments must not be null");
        }
        return a.abs().gcd(b.abs());
    }

    /**
     * Convenience overload that accepts signed-64 inputs and returns BigInteger gcd.
     */
    public static BigInteger gcdBig(long a, long b) {
        return gcdBig(BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    /**
     * int overload for convenience.
     */
    public static int gcd(int a, int b) {
        return (int) gcd((long) a, (long) b);
    }
}
