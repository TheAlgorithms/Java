package com.thealgorithms.maths;

/**
 * In mathematics, the extended Euclidean algorithm is an extension to the
 * Euclidean algorithm, and computes, in addition to the greatest common divisor
 * (gcd) of integers a and b, also the coefficients of Bézout's identity, which
 * are integers x and y such that ax + by = gcd(a, b).
 *
 * <p>
 * For more details, see
 * https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
 */
public final class ExtendedEuclideanAlgorithm {

    private ExtendedEuclideanAlgorithm() {
    }

    /**
     * This method implements the extended Euclidean algorithm.
     *
     * @param a The first number.
     * @param b The second number.
     * @return An array of three integers:
     *         <ul>
     *         <li>Index 0: The greatest common divisor (gcd) of a and b.</li>
     *         <li>Index 1: The value of x in the equation ax + by = gcd(a, b).</li>
     *         <li>Index 2: The value of y in the equation ax + by = gcd(a, b).</li>
     *         </ul>
     */
    public static long[] extendedGCD(long a, long b) {
        if (b == 0) {
            // Base case: gcd(a, 0) = a. The equation is a*1 + 0*0 = a.
            return new long[] {a, 1, 0};
        }

        // Recursive call
        long[] result = extendedGCD(b, a % b);
        long gcd = result[0];
        long x1 = result[1];
        long y1 = result[2];

        // Update coefficients using the results from the recursive call
        long x = y1;
        long y = x1 - a / b * y1;

        return new long[] {gcd, x, y};
    }
}
