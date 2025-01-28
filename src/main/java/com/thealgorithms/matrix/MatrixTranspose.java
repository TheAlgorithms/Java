package com.thealgorithms.matrix;

import static com.thealgorithms.matrix.utils.MatrixUtil.validateInputMatrix;

/**
 *
 *
 * <h1>Find the Transpose of Matrix!</h1>
 *
 * Simply take input from the user and print the matrix before the transpose and
 * after the transpose.
 *
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 *
 * @author Rajat-Jain29
 * @version 11.0.9
 * @since 2014-03-31
 */
public final class MatrixTranspose {
    private MatrixTranspose() {
    }

    /**
     * Calculate the transpose of the given matrix.
     *
     * @param matrix The matrix to be transposed
     * @throws IllegalArgumentException if the matrix is empty
     * @throws NullPointerException     if the matrix is null
     * @return The transposed matrix
     */
    public static double[][] transpose(double[][] matrix) {
        validateInputMatrix(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposedMatrix = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }
}
