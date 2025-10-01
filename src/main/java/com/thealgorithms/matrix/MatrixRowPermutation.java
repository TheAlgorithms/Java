package com.thealgorithms.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <h1>Find All Row Permutations of a Matrix!</h1>
 *
 * This program generates and returns all possible permutations of the rows of a
 * given matrix.
 * It is useful for exploring different row arrangements while keeping column
 * structure intact.
 *
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 *
 * @author Copilot
 * @version 11.0.9
 * @since 2025-10-01
 */
public final class MatrixRowPermutation {

    private MatrixRowPermutation() {
    }

    /**
     * Generate all permutations of the rows of a matrix.
     *
     * @param matrix The input matrix whose row permutations are to be generated
     * @throws IllegalArgumentException if the matrix is empty
     * @throws NullPointerException     if the matrix is null
     * @return A list of matrices, each representing a unique row permutation
     */
    public static List<int[][]> permuteRows(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix is empty");
        }

        List<int[][]> result = new ArrayList<>();
        permute(matrix, 0, result);
        return result;
    }

    /**
     * Helper method to recursively generate permutations.
     *
     * @param matrix The matrix being permuted
     * @param start  The current index to fix
     * @param result The list to store all permutations
     */
    private static void permute(int[][] matrix, int start, List<int[][]> result) {
        if (start == matrix.length) {
            int[][] copy = new int[matrix.length][];
            for (int i = 0; i < matrix.length; i++) {
                copy[i] = matrix[i].clone();
            }
            result.add(copy);
            return;
        }

        for (int i = start; i < matrix.length; i++) {
            swap(matrix, start, i);
            permute(matrix, start + 1, result);
            swap(matrix, start, i); // backtrack
        }
    }

    /**
     * Swap two rows in the matrix.
     *
     * @param matrix The matrix in which rows are to be swapped
     * @param i      The first row index
     * @param j      The second row index
     */
    private static void swap(int[][] matrix, int i, int j) {
        int[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
}
