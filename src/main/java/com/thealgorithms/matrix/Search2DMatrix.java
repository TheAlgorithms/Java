package com.thealgorithms.matrix;

/**
 * Two variations of searching in a 2D matrix
 * 
 * Variation 1:
 * - Each row is sorted left to right
 * - Each column is sorted top to bottom
 * 
 * Variation 2:
 * - The matrix is a flattened sorted array
 * - i.e., row 0 ends, row 1 continues (matrix is sorted as a 1D array)
 * 
 * LeetCode: #74 and #240
 */
public class Search2DMatrix {

    /**
     * Variation 1: Search in a matrix where each row and each column is sorted
     * Time: O(m + n)
     *
     * @param matrix input matrix
     * @param target value to find
     * @return true if found, false otherwise
     */
    public static boolean searchMatrixSortedRowsAndCols(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;

        int cols = matrix[0].length;
        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            int val = matrix[row][col];
            if (val == target) return true;
            else if (val > target) col--;
            else row++;
        }

        return false;
    }

    /**
     * Variation 2: Search in a matrix that behaves like a flattened sorted array
     * Time: O(log(m * n))
     *
     * @param matrix input matrix
     * @param target value to find
     * @return true if found, false otherwise
     */
    public static boolean searchMatrixFlattenedSorted(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;

        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid / cols][mid % cols];

            if (val == target) return true;
            else if (val < target) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 4, 7, 11},
            {2, 5, 8, 12},
            {3, 6, 9, 16},
            {10, 13, 14, 17}
        };
        System.out.println(searchMatrixSortedRowsAndCols(matrix1, 5));  // true

        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };
        System.out.println(searchMatrixFlattenedSorted(matrix2, 3));   // true
    }
}
