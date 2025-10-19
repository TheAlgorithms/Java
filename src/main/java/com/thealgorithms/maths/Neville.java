Neville.java
package com.thealgorithms.maths;

/**
 * In numerical analysis, Neville's algorithm is an algorithm used for
 * polynomial interpolation. Given n+1 points, there is a unique polynomial
 * of degree at most n that passes through all the points. Neville's
 * algorithm computes the value of this polynomial at a given point.
 *
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Neville%27s_algorithm
 *
 * @author Mitrajit Ghorui(KeyKyrios)
 */
public final class Neville {
    private Neville() {
    }

    /**
     * Evaluates the polynomial that passes through the given points at a specific x-coordinate.
     *
     * @param x The x-coordinates of the points. Must be the same length as y.
     * @param y The y-coordinates of the points. Must be the same length as x.
     * @param target The x-coordinate at which to evaluate the polynomial.
     * @return The interpolated y-value at the target x-coordinate.
     * @throws IllegalArgumentException if the lengths of x and y arrays are different,
     * or if the arrays are empty.
     */
    public static double interpolate(double[] x, double[] y, double target) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("x and y arrays must have the same length.");
        }
        if (x.length == 0) {
            throw new IllegalArgumentException("Input arrays cannot be empty.");
        }

        int n = x.length;
        double[] p = new double[n];
        System.arraycopy(y, 0, p, 0, n); // Initialize p with y values

        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                p[i] = ((target - x[i + k]) * p[i] + (x[i] - target) * p[i + 1]) / (x[i] - x[i + k]);
            }
        }

        return p[0];
    }
}