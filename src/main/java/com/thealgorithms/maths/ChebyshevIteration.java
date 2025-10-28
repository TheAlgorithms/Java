package com.thealgorithms.maths;

/**
 * In numerical analysis, Chebyshev iteration is an iterative method for solving
 * systems of linear equations Ax = b. It is designed for systems where the
 * matrix A is symmetric positive-definite (SPD).
 *
 * <p>
 * This method is a "polynomial acceleration" method, meaning it finds the
 * optimal polynomial to apply to the residual to accelerate convergence.
 *
 * <p>
 * It requires knowledge of the bounds of the eigenvalues of the matrix A:
 * m(A) (smallest eigenvalue) and M(A) (largest eigenvalue).
 *
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Chebyshev_iteration
 *
 * @author Mitrajit Ghorui(KeyKyrios)
 */
public final class ChebyshevIteration {

    private ChebyshevIteration() {
    }

    /**
     * Solves the linear system Ax = b using the Chebyshev iteration method.
     *
     * <p>
     * NOTE: The matrix A *must* be symmetric positive-definite (SPD) for this
     * algorithm to converge.
     *
     * @param a The matrix A (must be square, SPD).
     * @param b The vector b.
     * @param x0 The initial guess vector.
     * @param minEigenvalue The smallest eigenvalue of A (m(A)).
     * @param maxEigenvalue The largest eigenvalue of A (M(A)).
     * @param maxIterations The maximum number of iterations to perform.
     * @param tolerance The desired tolerance for the residual norm.
     * @return The solution vector x.
     * @throws IllegalArgumentException if matrix/vector dimensions are
     * incompatible,
     * if maxIterations <= 0, or if eigenvalues are invalid (e.g., minEigenvalue
     * <= 0, maxEigenvalue <= minEigenvalue).
     */
    public static double[] solve(double[][] a, double[] b, double[] x0, double minEigenvalue, double maxEigenvalue, int maxIterations, double tolerance) {
        validateInputs(a, b, x0, minEigenvalue, maxEigenvalue, maxIterations, tolerance);

        int n = b.length;
        double[] x = x0.clone();
        double[] r = vectorSubtract(b, matrixVectorMultiply(a, x));
        double[] p = new double[n];

        double d = (maxEigenvalue + minEigenvalue) / 2.0;
        double c = (maxEigenvalue - minEigenvalue) / 2.0;

        double alpha = 0.0;
        double alphaPrev = 0.0;

        for (int k = 0; k < maxIterations; k++) {
            double residualNorm = vectorNorm(r);
            if (residualNorm < tolerance) {
                return x; // Solution converged
            }

            if (k == 0) {
                alpha = 1.0 / d;
                System.arraycopy(r, 0, p, 0, n); // p = r
            } else {
                double beta = c * alphaPrev / 2.0 * (c * alphaPrev / 2.0);
                alpha = 1.0 / (d - beta / alphaPrev);
                double[] pUpdate = scalarMultiply(beta / alphaPrev, p);
                p = vectorAdd(r, pUpdate); // p = r + (beta / alphaPrev) * p
            }

            double[] xUpdate = scalarMultiply(alpha, p);
            x = vectorAdd(x, xUpdate); // x = x + alpha * p

            // Recompute residual for accuracy
            r = vectorSubtract(b, matrixVectorMultiply(a, x));
            alphaPrev = alpha;
        }

        return x; // Return best guess after maxIterations
    }

    /**
     * Validates the inputs for the Chebyshev solver.
     */
    private static void validateInputs(double[][] a, double[] b, double[] x0, double minEigenvalue, double maxEigenvalue, int maxIterations, double tolerance) {
        int n = a.length;
        if (n == 0) {
            throw new IllegalArgumentException("Matrix A cannot be empty.");
        }
        if (n != a[0].length) {
            throw new IllegalArgumentException("Matrix A must be square.");
        }
        if (n != b.length) {
            throw new IllegalArgumentException("Matrix A and vector b dimensions do not match.");
        }
        if (n != x0.length) {
            throw new IllegalArgumentException("Matrix A and vector x0 dimensions do not match.");
        }
        if (minEigenvalue <= 0) {
            throw new IllegalArgumentException("Smallest eigenvalue must be positive (matrix must be positive-definite).");
        }
        if (maxEigenvalue <= minEigenvalue) {
            throw new IllegalArgumentException("Max eigenvalue must be strictly greater than min eigenvalue.");
        }
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("Max iterations must be positive.");
        }
        if (tolerance <= 0) {
            throw new IllegalArgumentException("Tolerance must be positive.");
        }
    }

    // --- Vector/Matrix Helper Methods ---
    /**
     * Computes the product of a matrix A and a vector v (Av).
     */
    private static double[] matrixVectorMultiply(double[][] a, double[] v) {
        int n = a.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += a[i][j] * v[j];
            }
            result[i] = sum;
        }
        return result;
    }

    /**
     * Computes the subtraction of two vectors (v1 - v2).
     */
    private static double[] vectorSubtract(double[] v1, double[] v2) {
        int n = v1.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    /**
     * Computes the addition of two vectors (v1 + v2).
     */
    private static double[] vectorAdd(double[] v1, double[] v2) {
        int n = v1.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    /**
     * Computes the product of a scalar and a vector (s * v).
     */
    private static double[] scalarMultiply(double scalar, double[] v) {
        int n = v.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = scalar * v[i];
        }
        return result;
    }

    /**
     * Computes the L2 norm (Euclidean norm) of a vector.
     */
    private static double vectorNorm(double[] v) {
        double sumOfSquares = 0;
        for (double val : v) {
            sumOfSquares += val * val;
        }
        return Math.sqrt(sumOfSquares);
    }
}
