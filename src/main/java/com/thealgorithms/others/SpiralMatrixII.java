package com.thealgorithms.others;

import java.util.Arrays;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (num <= n * n) {
            // Traverse right
            for (int i = left; i <= right && num <= n * n; i++) {
                result[top][i] = num++;
            }
            top++;

            // Traverse down
            for (int i = top; i <= bottom && num <= n * n; i++) {
                result[i][right] = num++;
            }
            right--;

            // Traverse left
            for (int i = right; i >= left && num <= n * n; i--) {
                result[bottom][i] = num++;
            }
            bottom--;

            // Traverse up
            for (int i = bottom; i >= top && num <= n * n; i--) {
                result[i][left] = num++;
            }
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralMatrixII solution = new SpiralMatrixII();
        int n = 3;
        int[][] result = solution.generateMatrix(n);

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}