package com.thealgorithms.maths;
import java.util.Arrays;

public final class RankOfMatrix {
    private RankOfMatrix() {
    }
    /**
     * @brief Calculates the rank of a given matrix
     * @param matrix the input matrix
     * @return the rank of the matrix
     */
    public static int rankOfMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int rank = Math.min(rowCount, colCount);

        for (int row = 0; row < rank; row++) {
            // Make the diagonal element 1
            int diagElement = matrix[row][row];
            if (diagElement != 0) {
                for (int col = 0; col < colCount; col++) {
                    matrix[row][col] /= diagElement;
                }
            } else {
                // If the diagonal element is 0, find a row below with a non-zero element in the same column
                boolean found = false;
                for (int i = row + 1; i < rowCount; i++) {
                    if (matrix[i][row] != 0) {
                        // Swap rows
                        int[] temp = matrix[row];
                        matrix[row] = matrix[i];
                        matrix[i] = temp;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    rank--;
                    continue;
                }
            }

            // Eliminate non-zero elements below the current row
            for (int i = row + 1; i < rowCount; i++) {
                int factor = matrix[i][row];
                for (int j = row; j < colCount; j++) {
                    matrix[i][j] -= factor * matrix[row][j];
                }
            }
        }

        return rank;
    }

    public static void main(String[] args) {
        // Example usage:
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int rank = rankOfMatrix(matrix);
        System.out.println("Rank of the matrix: " + rank);
    }
}
