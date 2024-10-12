package com.thealgorithms.maths;

/**
 * This class provides a method to perform fast exponentiation (exponentiation by squaring),
 * which calculates (base^exp) % mod efficiently.
 *
 * <p>The algorithm works by repeatedly squaring the base and reducing the exponent
 * by half at each step. It exploits the fact that:
 * <ul>
 *   <li>If exp is even, (base^exp) = (base^(exp/2))^2</li>
 *   <li>If exp is odd, (base^exp) = base * (base^(exp-1))</li>
 * </ul>
 * The result is computed modulo `mod` at each step to avoid overflow and keep the result within bounds.
 * </p>
 *
 * <p><strong>Time complexity:</strong> O(log(exp)) — much faster than naive exponentiation (O(exp)).</p>
 *
 * For more information, please visit {@link https://en.wikipedia.org/wiki/Exponentiation_by_squaring}
 */
public final class FastExponentiation {

    /**
     * Private constructor to hide the implicit public one.
     */
    private FastExponentiation() {
    }

    /**
     * Performs fast exponentiation to calculate (base^exp) % mod using the method
     * of exponentiation by squaring.
     *
     * <p>This method efficiently computes the result by squaring the base and halving
     * the exponent at each step. It multiplies the base to the result when the exponent is odd.
     *
     * @param base the base number to be raised to the power of exp
     * @param exp the exponent to which the base is raised
     * @param mod the modulus to ensure the result does not overflow
     * @return (base^exp) % mod
     * @throws IllegalArgumentException if the modulus is less than or equal to 0
     * @throws ArithmeticException if the exponent is negative (not supported in this implementation)
     */
    public static long fastExponentiation(long base, long exp, long mod) {
        if (mod <= 0) {
            throw new IllegalArgumentException("Modulus must be positive.");
        }

        if (exp < 0) {
            throw new ArithmeticException("Negative exponent is not supported.");
        }

        long result = 1;
        base = base % mod; // Take the modulus of the base to handle large base values

        // Fast exponentiation by squaring algorithm
        while (exp > 0) {
            // If exp is odd, multiply the base to the result
            if ((exp & 1) == 1) { // exp & 1 checks if exp is odd
                result = result * base % mod;
            }
            // Square the base and halve the exponent
            base = base * base % mod; // base^2 % mod to avoid overflow
            exp >>= 1; // Right shift exp to divide it by 2
        }

        return result;
    }
}
