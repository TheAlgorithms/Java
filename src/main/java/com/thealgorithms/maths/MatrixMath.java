package com.thealgorithms.maths;

public class MatrixOperations {

    /**
     * Adds two matrices.
     *
     * @param matrixA The first matrix.
     * @param matrixB The second matrix.
     * @return The result of the matrix addition.
     * @throws IllegalArgumentException If the input matrices have incompatible dimensions.
     */
    public static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        int numRows = matrixA.length;
        int numCols = matrixA[0].length;

        if (numRows != matrixB.length || numCols != matrixB[0].length) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for addition.");
        }

        int[][] result = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return result;
    }

    /**
     * Subtracts one matrix from another.
     *
     * @param matrixA The matrix from which to subtract.
     * @param matrixB The matrix to subtract.
     * @return The result of the matrix subtraction.
     * @throws IllegalArgumentException If the input matrices have incompatible dimensions.
     */
    public static int[][] subtractMatrices(int[][] matrixA, int[][] matrixB) {
        int numRows = matrixA.length;
        int numCols = matrixA[0].length;

        if (numRows != matrixB.length || numCols != matrixB[0].length) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for subtraction.");
        }

        int[][] result = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }

        return result;
    }

    /**
     * Multiplies two matrices.
     *
     * @param matrixA The first matrix.
     * @param matrixB The second matrix.
     * @return The result of the matrix multiplication.
     * @throws IllegalArgumentException If the number of columns in matrixA does not match the number of rows in matrixB.
     */
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int numRowsA = matrixA.length;
        int numColsA = matrixA[0].length;
        int numRowsB = matrixB.length;
        int numColsB = matrixB[0].length;

        if (numColsA != numRowsB) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }

        int[][] result = new int[numRowsA][numColsB];

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                int sum = 0;
                for (int k = 0; k < numColsA; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    /**
     * Calculates the determinant of a square matrix.
     *
     * @param matrix The square matrix for which to calculate the determinant.
     * @return The determinant of the matrix.
     * @throws IllegalArgumentException If the input matrix is not square.
     */
    public static int calculateDeterminant(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            throw new IllegalArgumentException("Matrix must be square to calculate its determinant.");
        }

        if (numRows == 1) {
            return matrix[0][0];
        }

        if (numRows == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;
        for (int i = 0; i < numRows; i++) {
            int[][] subMatrix = new int[numRows - 1][numCols - 1];
            for (int j = 1; j < numRows; j++) {
                for (int k = 0, l = 0; k < numCols; k++) {
                    if (k != i) {
                        subMatrix[j - 1][l] = matrix[j][k];
                        l++;
                    }
                }
            }
            determinant += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * calculateDeterminant(subMatrix);
        }

        return determinant;
    }
}
