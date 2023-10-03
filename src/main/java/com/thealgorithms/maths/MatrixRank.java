package com.thealgorithms.maths;

public class MatrixRank {

    /**
     * Computes the rank of a matrix.
     *
     * @param matrix the input matrix
     * @return the rank of the matrix
     */
    public static int rank(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        // Initialize rank to 0
        int rank = 0;

        // Perform Gaussian elimination
        for (int col = 0; col < numCols; col++) {
            // Find the first non-zero element in the column
            int pivotRow = rank;
            while (pivotRow < numRows && matrix[pivotRow][col] == 0) {
                pivotRow++;
            }

            // If a non-zero element is found, swap rows
            if (pivotRow < numRows) {
                double[] temp = matrix[rank];
                matrix[rank] = matrix[pivotRow];
                matrix[pivotRow] = temp;

                // Scale the pivot row
                double pivotValue = matrix[rank][col];
                for (int i = col; i < numCols; i++) {
                    matrix[rank][i] /= pivotValue;
                }

                // Eliminate other rows
                for (int i = 0; i < numRows; i++) {
                    if (i != rank && matrix[i][col] != 0) {
                        double factor = matrix[i][col];
                        for (int j = col; j < numCols; j++) {
                            matrix[i][j] -= factor * matrix[rank][j];
                        }
                    }
                }

                rank++;
            }
        }

        return rank;
    }
    // **************was added for Test purpose */
    // public static void main(String[] args) {
    // double[][] matrix = {
    // {1, 2, 3},
    // {4, 5, 6},
    // {7, 8, 9}
    // };

    // int matrixRank = rank(matrix);
    // System.out.println("Rank of the matrix: " + matrixRank);
    // }
}
