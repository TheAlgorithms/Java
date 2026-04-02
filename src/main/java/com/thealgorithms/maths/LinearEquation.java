package com.thealgorithms.maths;

/**
 * Solves linear equations of the form ax + b = 0.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linear_equation">Linear equation (Wikipedia)</a>
 */
public final class LinearEquation {

    private LinearEquation() {
    }

    /**
     * Solves the equation ax + b = 0 and returns the value of x.
     *
     * @param a the coefficient of x, must not be zero
     * @param b the constant term
     * @return the value of x that satisfies the equation
     * @throws IllegalArgumentException if a is zero
     */
    public static double solve(final double a, final double b) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' must not be zero");
        }
        return -b / a;
    }
}
