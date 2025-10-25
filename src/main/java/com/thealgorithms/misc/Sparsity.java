package com.thealgorithms.misc;

/**
 * Utility class for calculating the sparsity of a matrix.
 * A matrix is considered sparse if a large proportion of its elements are zero.
 * Typically, if more than 2/3 of the elements are zero, the matrix is considered sparse.
 *
 * Sparsity is defined as:
 * sparsity = (number of zero elements) / (total number of elements)
 *
 * This can lead to significant computational optimizations.
 */
public final class Sparsity {

    private Sparsity() {
    }

    /**
     * Calculates the sparsity of a given 2D matrix.
     *
     * @param matrix the input matrix
     * @return the sparsity value between 0 and 1
     * @throws IllegalArgumentException if the matrix is null, empty, or contains empty rows
     */
    public static double sparsity(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }

        int zeroCount = 0;
        int totalElements = 0;

        // Count the number of zero elements and total elements
        for (double[] row : matrix) {
            for (double value : row) {
                if (value == 0.0) {
                    zeroCount++;
                }
                totalElements++;
            }
        }

        // Return sparsity as a double
        return (double) zeroCount / totalElements;
    }
}
