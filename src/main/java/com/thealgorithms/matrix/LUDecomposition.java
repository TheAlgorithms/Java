// File: src/main/java/com/thealgorithms/matrix/LUDecomposition.java
package com.thealgorithms.matrix;

import java.util.Arrays;

/**
 * Implements the LU Decomposition algorithm for a square matrix.
 *
 * LU decomposition factors a matrix as the product of a lower triangular matrix (L)
 * and an upper triangular matrix (U). This implementation uses the Doolittle algorithm,
 * where the diagonal elements of the lower triangular matrix L are all 1s.
 */
public class LUDecomposition {

    /**
     * Decomposes a given square matrix into Lower (L) and Upper (U) matrices.
     *
     * @param matrix The square matrix to be decomposed.
     * @return An array containing L and U matrices. result[0] is L, result[1] is U.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static double[][][] decompose(double[][] matrix) {
        int n = matrix.length;
        if (n == 0 || matrix[0].length != n) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        double[][] lower = new double[n][n];
        double[][] upper = new double[n][n];

        for (int i = 0; i < n; i++) {
            // Upper Triangular Matrix
            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += (lower[i][j] * upper[j][k]);
                }
                upper[i][k] = matrix[i][k] - sum;
            }

            // Lower Triangular Matrix
            for (int k = i; k < n; k++) {
                if (i == k) {
                    lower[i][i] = 1; // Diagonal of L is 1
                } else {
                    double sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += (lower[k][j] * upper[j][i]);
                    }
                    lower[k][i] = (matrix[k][i] - sum) / upper[i][i];
                }
            }
        }

        return new double[][][] {lower, upper};
    }

    /**
     * A utility function to print a matrix.
     * @param matrix The matrix to be printed.
     */
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Main method to demonstrate the LU Decomposition.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        double[][] matrix = {
            {2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        double[][][] luMatrices = decompose(matrix);
        double[][] lower = luMatrices[0];
        double[][] upper = luMatrices[1];

        System.out.println("\nLower Triangular Matrix (L):");
        printMatrix(lower);

        System.out.println("\nUpper Triangular Matrix (U):");
        printMatrix(upper);
    }
}