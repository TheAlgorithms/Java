package com.thealgorithms.matrix;

/**
 * LU Decomposition Algorithm
 * --------------------------
 * Decomposes a square matrix A into a product of two matrices:
 * A = L * U
 * where:
 * - L is a lower triangular matrix with 1s on its diagonal
 * - U is an upper triangular matrix
 *
 * This algorithm is widely used in:
 * - Solving systems of linear equations (Ax = b)
 * - Finding matrix inverses
 * - Computing determinants efficiently
 *
 * Time Complexity: O(n³)
 *
 * Reference:
 * https://en.wikipedia.org/wiki/LU_decomposition
 *
 * Example:
 * >>> double[][] A = {
 * >>> {2, -1, -2},
 * >>> {-4, 6, 3},
 * >>> {-4, -2, 8}
 * >>> };
 * >>> LUDecomposition.LU result = LUDecomposition.decompose(A);
 * >>> LUDecomposition.printMatrix(result.L);
 * >>> LUDecomposition.printMatrix(result.U);
 *
 * Expected Output:
 * L =
 * [1.000, 0.000, 0.000]
 * [-2.000, 1.000, 0.000]
 * [-2.000, -1.000, 1.000]
 *
 * U =
 * [2.000, -1.000, -2.000]
 * [0.000, 4.000, -1.000]
 * [0.000, 0.000, 3.000]
 */

public class LUDecomposition {

    /**
     * A helper class to store both L and U matrices
     */
    public static class LU {
        double[][] L;
        double[][] U;

        LU(double[][] L, double[][] U) {
            this.L = L;
            this.U = U;
        }
    }

    /**
     * Performs LU Decomposition on a square matrix A
     *
     * @param A input square matrix
     * @return LU object containing L and U matrices
     */
    public static LU decompose(double[][] A) {
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        for (int i = 0; i < n; i++) {
            // Upper Triangular Matrix
            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += L[i][j] * U[j][k];
                }
                U[i][k] = A[i][k] - sum;
            }

            // Lower Triangular Matrix
            for (int k = i; k < n; k++) {
                if (i == k) {
                    L[i][i] = 1; // Diagonal as 1
                } else {
                    double sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += L[k][j] * U[j][i];
                    }
                    L[k][i] = (A[k][i] - sum) / U[i][i];
                }
            }
        }

        return new LU(L, U);
    }

    /**
     * Utility function to print a matrix
     *
     * @param M matrix to print
     */
    public static void printMatrix(double[][] M) {
        for (double[] row : M) {
            System.out.print("[");
            for (int j = 0; j < row.length; j++) {
                System.out.printf("%7.3f", row[j]);
                if (j < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Demonstration (doctest)
     */
    public static void main(String[] args) {
        double[][] A = {{2, -1, -2}, {-4, 6, 3}, {-4, -2, 8}};

        LU result = decompose(A);

        System.out.println("L matrix:");
        printMatrix(result.L);

        System.out.println("\nU matrix:");
        printMatrix(result.U);
    }
}
