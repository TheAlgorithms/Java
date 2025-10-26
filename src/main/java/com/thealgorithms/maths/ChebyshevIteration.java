package com.thealgorithms.maths;

import java.util.Arrays;

/**
 * Implements the Chebyshev Iteration method for solving a system of linear
 * equations (Ax = b).
 *
 * @author Mitrajit ghorui (github: keyKyrios)
 * @see <a href="https://en.wikipedia.org/wiki/Chebyshev_iteration">Wikipedia
 * Page</a>
 *
 * This is an iterative method that requires the matrix A to be
 * symmetric positive definite (SPD).
 * It also requires knowledge of the minimum (lambdaMin) and maximum
 * (lambdaMax)
 * eigenvalues of the matrix A.
 *
 * The algorithm converges faster than simpler methods like Jacobi or
 * Gauss-Seidel
 * by using Chebyshev polynomials to optimize the update steps.
 */
public final class ChebyshevIteration {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ChebyshevIteration() {
    }

    /**
     * Solves the linear system Ax = b using Chebyshev Iteration.
     *
     * @param A A symmetric positive definite matrix.
     * @param b The vector 'b' in the equation Ax = b.
     * @param x0 An initial guess vector for 'x'.
     * @param lambdaMin The smallest eigenvalue of matrix A.
     * @param lambdaMax The largest eigenvalue of matrix A.
     * @param maxIterations The maximum number of iterations to perform.
     * @param tolerance The desired tolerance for convergence (e.g., 1e-10).
     * @return The solution vector 'x'.
     * @throws IllegalArgumentException if matrix/vector dimensions don't
     * match,
     * or if max/min eigenvalues are invalid.
     */
    public static double[] solve(
        double[][] A,
        double[] b,
        double[] x0,
        double lambdaMin,
        double lambdaMax,
        int maxIterations,
        double tolerance
    ) {
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
                // Fix for algorithmic bug (use alphaPrev)
                double alphaPrev = alpha;

                beta = (c * alphaPrev / 2.0) * (c * alphaPrev / 2.0);
                alpha = 1.0 / (d - beta / alphaPrev);

                double betaOverAlphaPrev = beta / alphaPrev;
                double[] rScaled = vectorScale(p, betaOverAlphaPrev);
                p = vectorAdd(r, rScaled);
            }

            double[] pScaled = vectorScale(p, alpha);
            x = vectorAdd(x, pScaled);

            // Re-calculate residual to avoid accumulating floating-point errors
            r = vectorSubtract(b, matrixVectorMultiply(A, x));

            if (vectorNorm(r) < tolerance) {
                break; // Converged
            }
        }
        return x;
    }

    // --- Helper Methods for Linear Algebra ---
    private static void validateInputs(
        double[][] A,
        double[] b,
        double[] x0,
        double lambdaMin,
        double lambdaMax
    ) {
        int n = b.length;
        if (n == 0) {
            throw new IllegalArgumentException("Vectors cannot be empty.");
        }
        if (A.length != n || A[0].length != n) {
            throw new IllegalArgumentException("Matrix A must be square with dimensions n x n.");
        }
        if (x0.length != n) {
            throw new IllegalArgumentException("Initial guess vector x0 must have length n.");
        }
        if (lambdaMin >= lambdaMax || lambdaMin <= 0) {
            throw new IllegalArgumentException("Eigenvalues must satisfy 0 < lambdaMin < lambdaMax.");
        }
    }

    /**
     * Computes y = Ax
     */
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

    /**
     * Computes c = a + b
     */
    private static double[] vectorAdd(double[] a, double[] b) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    /**
     * Computes c = a - b
     */
    private static double[] vectorSubtract(double[] a, double[] b) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    /**
     * Computes c = a * scalar
     */
    private static double[] vectorScale(double[] a, double scalar) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i] * scalar;
        }
        return c;
    }

    /**
     * Computes the L2 norm (Euclidean norm) of a vector
     */
    private static double vectorNorm(double[] a) {
        double sumOfSquares = 0.0;
        for (double val : a) {
            sumOfSquares += val * val;
        }
        return Math.sqrt(sumOfSquares);
    }
}
