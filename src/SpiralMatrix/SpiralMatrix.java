package com.thealgorithms.SpiralMatrix;

public class SpiralMatrix {
    public static int[][] generateSpiralMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (num <= n * n) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // Traverse from right to left along the bottom row
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            bottom--;

            // Traverse from bottom to top along the left column
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        int n = 3; // Replace with the desired value of n
        int[][] result = generateSpiralMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
