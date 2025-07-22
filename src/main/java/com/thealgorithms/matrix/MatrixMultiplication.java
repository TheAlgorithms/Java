package com.thealgorithms.matrix;

/**
 * This class provides a method to perform matrix multiplication.
 *
 * <p>Matrix multiplication takes two 2D arrays (matrices) as input and
 * produces their product, following the mathematical definition of
 * matrix multiplication.
 *
 * <p>For more details:
 * https://www.geeksforgeeks.org/java/java-program-to-multiply-two-matrices-of-any-size/
 * https://en.wikipedia.org/wiki/Matrix_multiplication
 *
 * <p>Time Complexity: O(n^3) – where n is the dimension of the matrices
 * (assuming square matrices for simplicity).
 *
 * <p>Space Complexity: O(n^2) – for storing the result matrix.
 *
 *
 * @author Nishitha Wihala Pitigala
 *
 */

public final class MatrixMultiplication {
    private MatrixMultiplication() {
    }

    /**
     * Multiplies two matrices.
     *
     * @param matrixA the first matrix rowsA x colsA
     * @param matrixB the second matrix rowsB x colsB
     * @return the product of the two matrices rowsA x colsB
     * @throws IllegalArgumentException if the matrices cannot be multiplied
     */
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        // Check the input matrices are not null
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Input matrices cannot be null");
        }

        // Check for empty matrices
        if (matrixA.length == 0 || matrixB.length == 0 || matrixA[0].length == 0 || matrixB[0].length == 0) {
            throw new IllegalArgumentException("Input matrices must not be empty");
        }

        // Validate the matrix dimensions
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied: incompatible dimensions.");
        }

        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        // Initialize the result matrix with zeros
        double[][] result = new double[rowsA][colsB];

        // Perform matrix multiplication
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }
}
