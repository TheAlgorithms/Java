package com.thealgorithms.prefixsum;

/**
 * A class that implements the 2D Prefix Sum algorithm.
 *
 * <p>2D Prefix Sum is a technique used to preprocess a 2D matrix such that
 * sub-matrix sum queries can be answered in O(1) time.
 * The preprocessing step takes O(N*M) time.
 *
 * <p>This implementation uses a long array for the prefix sums to prevent
 * integer overflow.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Summed-area_table">Summed-area table (Wikipedia)</a>
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 */
public class PrefixSum2D {

    private final long[][] prefixSums;

    /**
     * Constructor to preprocess the input matrix.
     *
     * @param matrix The input integer matrix.
     * @throws IllegalArgumentException if the matrix is null or empty.
     */
    public PrefixSum2D(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Input matrix cannot be null or empty");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        this.prefixSums = new long[rows + 1][cols + 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // P[i+1][j+1] = current + above + left - diagonal_overlap
                this.prefixSums[i + 1][j + 1] = matrix[i][j] + this.prefixSums[i][j + 1] + this.prefixSums[i + 1][j] - this.prefixSums[i][j];
            }
        }
    }

    /**
     * Calculates the sum of the sub-matrix defined by (row1, col1) to (row2, col2).
     * Indices are 0-based.
     *
     * @param row1 Top row index.
     * @param col1 Left column index.
     * @param row2 Bottom row index.
     * @param col2 Right column index.
     * @return The sum of the sub-matrix.
     * @throws IndexOutOfBoundsException if indices are invalid.
     */
    public long sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || row2 >= prefixSums.length - 1 || row2 < row1) {
            throw new IndexOutOfBoundsException("Invalid row indices");
        }
        if (col1 < 0 || col2 >= prefixSums[0].length - 1 || col2 < col1) {
            throw new IndexOutOfBoundsException("Invalid column indices");
        }

        return prefixSums[row2 + 1][col2 + 1] - prefixSums[row1][col2 + 1] - prefixSums[row2 + 1][col1] + prefixSums[row1][col1];
    }
}
