package com.thealgorithms.others;

public final class SpiralMatrixII {
    private SpiralMatrixII() {
    }
        static public int[][] generateMatrix(int size) {
        int[][] result = new int[size][size];
        int num = 1;
        int top = 0, bottom = size - 1, left = 0, right = size - 1;

        while (num <= size * size) {
            // Traverse right
            for (int i = left; i <= right && num <= size * size; i++) {
                result[top][i] = num++;
            }
            top++;

            // Traverse down
            for (int i = top; i <= bottom && num <= size * size; i++) {
                result[i][right] = num++;
            }
            right--;

            // Traverse left
            for (int i = right; i >= left && num <= size * size; i--) {
                result[bottom][i] = num++;
            }
            bottom--;

            // Traverse up
            for (int i = bottom; i >= top && num <= size * size; i--) {
                result[i][left] = num++;
            }
            left++;
        }
        return result;
    }
    public static void main(String[] args) {
        SpiralMatrixII solution = new SpiralMatrixII();
        int size = 3;
        int[][] result = solution.generateMatrix(size);
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}