package com.thealgorithms.matrix;

/**
 * This class implements an algorithm for solving a system of equations of the form Ax=b using gaussian elimination and back substitution.
 *
 * @link <a href="https://en.wikipedia.org/wiki/Gaussian_elimination">Gaussian Elimination Wiki</a>
 * @see InverseOfMatrix finds the full of inverse of a matrice, but is not required to solve a system.
 */
public final class SolveSystem {
    private SolveSystem() {
    }

    /**
     * Problem: Given a matrix A and vector b, solve the linear system Ax = b for the vector x.\
     * <p>
     * <b>This OVERWRITES the input matrix to save on memory</b>
     *
     * @param matrix    - a square matrix of doubles
     * @param constants - an array of constant
     * @return solutions
     */
    public static double[] solveSystem(double[][] matrix, double[] constants) {
        final double tol = 0.00000001; // tolerance for round off
        for (int k = 0; k < matrix.length - 1; k++) {
            // find the largest value in column (to avoid zero pivots)
            double maxVal = Math.abs(matrix[k][k]);
            int maxIdx = k;
            for (int j = k + 1; j < matrix.length; j++) {
                if (Math.abs(matrix[j][k]) > maxVal) {
                    maxVal = matrix[j][k];
                    maxIdx = j;
                }
            }
            if (Math.abs(maxVal) < tol) {
                // hope the matrix works out
                continue;
            }
            // swap rows
            double[] temp = matrix[k];
            matrix[k] = matrix[maxIdx];
            matrix[maxIdx] = temp;
            double tempConst = constants[k];
            constants[k] = constants[maxIdx];
            constants[maxIdx] = tempConst;
            for (int i = k + 1; i < matrix.length; i++) {
                // compute multipliers and save them in the column
                matrix[i][k] /= matrix[k][k];
                for (int j = k + 1; j < matrix.length; j++) {
                    matrix[i][j] -= matrix[i][k] * matrix[k][j];
                }
                constants[i] -= matrix[i][k] * constants[k];
            }
        }
        // back substitution
        double[] x = new double[constants.length];
        System.arraycopy(constants, 0, x, 0, constants.length);
        for (int i = matrix.length - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < matrix.length; j++) {
                sum += matrix[i][j] * x[j];
            }
            x[i] = constants[i] - sum;
            if (Math.abs(matrix[i][i]) > tol) {
                x[i] /= matrix[i][i];
            } else {
                throw new IllegalArgumentException("Matrix was found to be singular");
            }
        }
        return x;
    }
}
