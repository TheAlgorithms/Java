package com.thealgorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The MatrixChainMultiplication class provides functionality to compute the
 * optimal way to multiply a sequence of matrices. The optimal multiplication
 * order is determined using dynamic programming, which minimizes the total
 * number of scalar multiplications required.
 */
public final class MatrixChainMultiplication {
    private MatrixChainMultiplication() {
    }

    // Matrices to store minimum multiplication costs and split points
    private static int[][] m;
    private static int[][] s;
    private static int[] p;

    /**
     * Calculates the optimal order for multiplying a given list of matrices.
     *
     * @param matrices an ArrayList of Matrix objects representing the matrices
     *                 to be multiplied.
     * @return a Result object containing the matrices of minimum costs and
     *         optimal splits.
     */
    public static Result calculateMatrixChainOrder(ArrayList<Matrix> matrices) {
        int size = matrices.size();
        m = new int[size + 1][size + 1];
        s = new int[size + 1][size + 1];
        p = new int[size + 1];

        for (int i = 0; i < size + 1; i++) {
            Arrays.fill(m[i], -1);
            Arrays.fill(s[i], -1);
        }

        for (int i = 0; i < p.length; i++) {
            p[i] = i == 0 ? matrices.get(i).col() : matrices.get(i - 1).row();
        }

        matrixChainOrder(size);
        return new Result(m, s);
    }

    /**
     * A helper method that computes the minimum cost of multiplying
     * the matrices using dynamic programming.
     *
     * @param size the number of matrices in the multiplication sequence.
     */
    private static void matrixChainOrder(int size) {
        for (int i = 1; i < size + 1; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l < size + 1; l++) {
            for (int i = 1; i < size - l + 2; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    /**
     * The Result class holds the results of the matrix chain multiplication
     * calculation, including the matrix of minimum costs and split points.
     */
    public static class Result {
        private final int[][] m;
        private final int[][] s;

        /**
         * Constructs a Result object with the specified matrices of minimum
         * costs and split points.
         *
         * @param m the matrix of minimum multiplication costs.
         * @param s the matrix of optimal split points.
         */
        public Result(int[][] m, int[][] s) {
            this.m = m;
            this.s = s;
        }

        /**
         * Returns the matrix of minimum multiplication costs.
         *
         * @return the matrix of minimum multiplication costs.
         */
        public int[][] getM() {
            return m;
        }

        /**
         * Returns the matrix of optimal split points.
         *
         * @return the matrix of optimal split points.
         */
        public int[][] getS() {
            return s;
        }
    }

    /**
     * The Matrix class represents a matrix with its dimensions and count.
     */
    public static class Matrix {
        private final int count;
        private final int col;
        private final int row;

        /**
         * Constructs a Matrix object with the specified count, number of columns,
         * and number of rows.
         *
         * @param count the identifier for the matrix.
         * @param col   the number of columns in the matrix.
         * @param row   the number of rows in the matrix.
         */
        public Matrix(int count, int col, int row) {
            this.count = count;
            this.col = col;
            this.row = row;
        }

        /**
         * Returns the identifier of the matrix.
         *
         * @return the identifier of the matrix.
         */
        public int count() {
            return count;
        }

        /**
         * Returns the number of columns in the matrix.
         *
         * @return the number of columns in the matrix.
         */
        public int col() {
            return col;
        }

        /**
         * Returns the number of rows in the matrix.
         *
         * @return the number of rows in the matrix.
         */
        public int row() {
            return row;
        }
    }
}
