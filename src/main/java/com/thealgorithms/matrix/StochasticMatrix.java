package com.thealgorithms.matrix;

/**
 * Utility class to check whether a matrix is stochastic.
 * A matrix is stochastic if all its elements are non-negative
 * and the sum of each row or column is equal to 1.
 *Reference: https://en.wikipedia.org/wiki/Stochastic_matrix
 */
public final class StochasticMatrix {

    private static final double TOLERANCE = 1e-9;

    private StochasticMatrix() {
        // Utility class
    }

    /**
     * Checks if a matrix is row-stochastic.
     *
     * @param matrix the matrix to check
     * @return true if the matrix is row-stochastic
     * @throws IllegalArgumentException if matrix is null or empty
     */
    public static boolean isRowStochastic(double[][] matrix) {
        validateMatrix(matrix);

        for (double[] row : matrix) {
            double sum = 0.0;
            for (double value : row) {
                if (value < 0) {
                    return false;
                }
                sum += value;
            }
            if (Math.abs(sum - 1.0) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a matrix is column-stochastic.
     *
     * @param matrix the matrix to check
     * @return true if the matrix is column-stochastic
     * @throws IllegalArgumentException if matrix is null or empty
     */
    public static boolean isColumnStochastic(double[][] matrix) {
        validateMatrix(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int j = 0; j < cols; j++) {
            double sum = 0.0;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
                sum += matrix[i][j];
            }
            if (Math.abs(sum - 1.0) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    private static void validateMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix must not be null or empty");
        }
    }
}
