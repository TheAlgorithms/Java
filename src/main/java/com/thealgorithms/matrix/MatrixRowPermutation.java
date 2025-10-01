package com.thealgorithms.matrix;

import java.util.ArrayList;
import java.util.List;
/**
 * Generate all possible permutations of the rows of a matrix.
 * Useful for exploring different row arrangements while keeping column structure intact.
 */
public final class MatrixRowPermutation {

    private MatrixRowPermutation() {}

    /**
     * Generate all permutations of the rows of a matrix.
     *
     * @param matrix the input matrix
     * @return list of matrices, each representing a unique row permutation
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

    private static void swap(int[][] matrix, int i, int j) {
        int[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
}
