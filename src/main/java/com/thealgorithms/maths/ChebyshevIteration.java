package com.thealgorithms.maths;

import java.util.Arrays;

/**
 * Implements the Chebyshev Iteration method for solving a system of linear
 * equations (Ax = b).
 *
 * This iterative method requires:
 *  - Matrix A to be symmetric positive definite (SPD)
 *  - Knowledge of minimum (lambdaMin) and maximum (lambdaMax) eigenvalues
 *
 * Reference: https://en.wikipedia.org/wiki/Chebyshev_iteration
 *
 * Author: Mitrajit Ghorui (github: keyKyrios)
 */
public final class ChebyshevIteration {

    private ChebyshevIteration() {
    }

    /**
     * Solves Ax = b using Chebyshev Iteration.
     *
     * @param A             SPD matrix
     * @param b             vector b
     * @param x0            initial guess
     * @param lambdaMin     minimum eigenvalue
     * @param lambdaMax     maximum eigenvalue
     * @param maxIterations maximum iterations
     * @param tolerance     convergence tolerance
     * @return solution vector x
     */
    public static double[] solve(double[][] A, double[] b, double[] x0,
                                 double lambdaMin, double lambdaMax,
                                 int maxIterations, double tolerance) {
        validateInputs(A, b, x0, lambdaMin, lambdaMax);

        int n = b.length;
        double[] x = Arrays.copyOf(x0, n);
        double[] r = vectorSubtract(b, matrixVectorMultiply(A, x));
        double[] p = new double[n];
        double alpha = 0.0;
        double beta = 0.0;
        double c = (lambdaMax - lambdaMin) / 2.0;
        double d = (lambdaMax + lambdaMin) / 2.0;

        double initialResidualNorm = vectorNorm(r);
        if (initialResidualNorm < tolerance) {
            return x; // Already converged
        }

        for (int k = 1; k <= maxIterations; k++) {
            if (k == 1) {
                alpha = 1.0 / d;
                p = Arrays.copyOf(r, n);
            } else {
                double alphaPrev = alpha;
                beta = Math.pow(c * alphaPrev / 2.0, 2);
                alpha = 1.0 / (d - beta / alphaPrev);
                p = vectorAdd(r, vectorScale(p, beta / alphaPrev));
            }

            x = vectorAdd(x, vectorScale(p, alpha));
            r = vectorSubtract(b, matrixVectorMultiply(A, x));

            if (vectorNorm(r) < tolerance) {
                break;
            }
        }

        return x;
    }

    private static void validateInputs(double[][] A, double[] b, double[] x0,
                                       double lambdaMin, double lambdaMax) {
        int n = b.length;
        if (n == 0) throw new IllegalArgumentException("Vectors cannot be empty.");
        if (A.length != n || A[0].length != n)
            throw new IllegalArgumentException("Matrix A must be square (n x n).");
        if (x0.length != n)
            throw new IllegalArgumentException("Initial guess vector x0 must have length n.");
        if (lambdaMin >= lambdaMax || lambdaMin <= 0)
            throw new IllegalArgumentException("Eigenvalues must satisfy 0 < lambdaMin < lambdaMax.");
    }

    private static double[] matrixVectorMultiply(double[][] A, double[] x) {
        int n = A.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            y[i] = sum;
        }
        return y;
    }

    private static double[] vectorAdd(double[] a, double[] b) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) c[i] = a[i] + b[i];
        return c;
    }

    private static double[] vectorSubtract(double[] a, double[] b) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) c[i] = a[i] - b[i];
        return c;
    }

    private static double[] vectorScale(double[] a, double scalar) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) c[i] = a[i] * scalar;
        return c;
    }

    private static double vectorNorm(double[] a) {
        double sum = 0.0;
        for (double val : a) sum += val * val;
        return Math.sqrt(sum);
    }
}
