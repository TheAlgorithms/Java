package com.thealgorithms.matrix;

/**
 * LU Decomposition is a matrix factorization technique that decomposes a matrix A
 * into the product of a lower triangular matrix L and an upper triangular matrix U.
 * This is useful for solving systems of linear equations, computing determinants,
 * and inverting matrices.
 *
 * @author Hardvan
 */
public final class LUDecomposition {
    private LUDecomposition() {
    }

    public static class Result {
        private final double[][] l;
        private final double[][] u;

        public Result(double[][] l, double[][] u) {
            this.l = l;
            this.u = u;
        }

        public double[][] getL() {
            return l;
        }

        public double[][] getU() {
            return u;
        }
    }

    /**
     * Performs LU decomposition on the given matrix.
     *
     * @param matrix The input matrix to decompose
     * @return Result object containing L and U matrices
     * @throws IllegalArgumentException if matrix is not square, empty, or singular
     */
    public static Result decompose(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }

        int n = matrix.length;
        if (matrix[0].length != n) {
            throw new IllegalArgumentException("Matrix must be square");
        }

        double[][] l = new double[n][n];
        double[][] u = new double[n][n];

        for (int i = 0; i < n; i++) {
            l[i][i] = 1.0;

            for (int j = i; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += l[i][k] * u[k][j];
                }
                u[i][j] = matrix[i][j] - sum;
            }

            for (int j = i + 1; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += l[j][k] * u[k][i];
                }

                if (Math.abs(u[i][i]) < 1e-10) {
                    throw new IllegalArgumentException("Matrix is singular or nearly singular");
                }

                l[j][i] = (matrix[j][i] - sum) / u[i][i];
            }
        }

        return new Result(l, u);
    }
}
