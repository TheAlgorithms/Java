package com.thealgorithms.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a method to generate all possible permutations of the rows of a matrix.
 * Row permutations are useful for exploring different arrangements while keeping the column structure intact.
 * 
 * For example, consider the following 2x2 matrix:
 * 1 2
 * 3 4
 * The row permutations are:
 * 1 2      3 4
 * 3 4      1 2
 *
 * @author Suraj Singh Chauhan
 */
public final class MatrixRowPermutation {

    private MatrixRowPermutation() {
    }

    /**
     * @brief Generates all permutations of the rows of a matrix.
     *
     * @param matrix The input matrix; must be non-null and non-empty
     * @return A list of matrices, each representing a unique row permutation
     * @throws IllegalArgumentException if the matrix is empty
     * @throws NullPointerException if the matrix is null
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
     * @brief Helper method to recursively generate permutations of matrix rows.
     *
     * @param matrix The matrix being permuted
     * @param start The current index to fix
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
     * @brief Swaps two rows in the matrix.
     *
     * @param matrix The matrix
     * @param i The index of the first row
     * @param j The index of the second row
     */
    private static void swap(int[][] matrix, int i, int j) {
        int[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
}
