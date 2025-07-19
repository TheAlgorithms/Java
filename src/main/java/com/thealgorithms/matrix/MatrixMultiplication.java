package com.thealgorithms.matrix;

public class MatrixMultiplication {
    private MatrixMultiplication() {
    }

    /**
     * Multiplies two matrices.
     *
     * @param matrixA the first matrix     rowsA x colsA
     * @param matrixB the second matrix    rowsB x colsB
     * @return the product of the two matrices    rowsA x colsB
     * @throws IllegalArgumentException if the matrices cannot be multiplied
     */
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {

        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Input matrices cannot be null");
        }

        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied: incompatible dimensions.");
        }

        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        double[][] result = new double[rowsA][colsB];

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
