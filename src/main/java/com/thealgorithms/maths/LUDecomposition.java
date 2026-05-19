package com.thealgorithms.maths;

/**
 * @brief Implementation of LU Decomposition using the Doolittle algorithm
 * @details Decomposes a square matrix A into a lower triangular matrix L and
 * an upper triangular matrix U such that A = L * U. The diagonal of L contains
 * all ones (Doolittle convention). This decomposition is useful for solving
 * systems of linear equations, computing determinants, and finding inverses.
 * @see <a href="https://en.wikipedia.org/wiki/LU_decomposition">LU Decomposition</a>
 */
public final class LUDecomposition {

    private LUDecomposition() {
    }

    /**
     * @brief Performs LU decomposition on a square matrix using the Doolittle algorithm
     * @param matrix a square matrix
     * @return a 2D array where the lower triangle (excluding diagonal) contains L
     *         elements (with implicit 1s on the diagonal) and the upper triangle
     *         (including diagonal) contains U elements
     * @throws IllegalArgumentException if the matrix is not square
     * @throws ArithmeticException if a zero pivot is encountered
     */
    public static double[][] decompose(double[][] matrix) {
        int n = matrix.length;
        for (double[] row : matrix) {
            if (row.length != n) {
                throw new IllegalArgumentException("Matrix must be square.");
            }
        }

        double[][] lu = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lu[i][j] = matrix[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int j = k; j < n; j++) {
                double sum = 0;
                for (int s = 0; s < k; s++) {
                    sum += lu[k][s] * lu[s][j];
                }
                lu[k][j] -= sum;
            }

            if (lu[k][k] == 0) {
                throw new ArithmeticException("Zero pivot encountered. Matrix may be singular.");
            }

            for (int i = k + 1; i < n; i++) {
                double sum = 0;
                for (int s = 0; s < k; s++) {
                    sum += lu[i][s] * lu[s][k];
                }
                lu[i][k] = (lu[i][k] - sum) / lu[k][k];
            }
        }

        return lu;
    }

    /**
     * @brief Extracts the lower triangular matrix L from the combined LU matrix
     * @param lu the combined LU matrix from decompose()
     * @return the lower triangular matrix L with 1s on the diagonal
     */
    public static double[][] getLowerMatrix(double[][] lu) {
        int n = lu.length;
        double[][] lower = new double[n][n];
        for (int i = 0; i < n; i++) {
            lower[i][i] = 1.0;
            for (int j = 0; j < i; j++) {
                lower[i][j] = lu[i][j];
            }
        }
        return lower;
    }

    /**
     * @brief Extracts the upper triangular matrix U from the combined LU matrix
     * @param lu the combined LU matrix from decompose()
     * @return the upper triangular matrix U
     */
    public static double[][] getUpperMatrix(double[][] lu) {
        int n = lu.length;
        double[][] upper = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                upper[i][j] = lu[i][j];
            }
        }
        return upper;
    }

    /**
     * @brief Solves a system of linear equations Ax = b using LU decomposition
     * @param lu the combined LU matrix from decompose()
     * @param b the right-hand side vector
     * @return the solution vector x
     */
    public static double[] solve(double[][] lu, double[] b) {
        int n = lu.length;
        double[] y = new double[n];
        double[] x = new double[n];

        // Forward substitution: solve Ly = b
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += lu[i][j] * y[j];
            }
            y[i] = b[i] - sum;
        }

        // Back substitution: solve Ux = y
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += lu[i][j] * x[j];
            }
            x[i] = (y[i] - sum) / lu[i][i];
        }

        return x;
    }
}
