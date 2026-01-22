/**
 * Spiral Matrix II
 * Generates an n x n matrix filled with numbers from 1 to n^2 in spiral order.
 *
 * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/">LeetCode – Spiral Matrix II</a>
 * @see <a href="https://en.wikipedia.org/wiki/Spiral_matrix">Wikipedia – Spiral Matrix</a>
 */
package com.thealgorithms.matrix;

public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        int top = 0;
        int bottom = n - 1;

        int left = 0;
        int right = n - 1;

        int num = 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++;
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }
}
