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
     * Search in a 2D matrix where rows and columns are sorted.
     *
     * @param matrix The 2D matrix with sorted rows and columns.
     * @param target The target integer to search for.
     * @return true if target is found, otherwise false.
     */
    public static boolean searchMatrixSortedRowsAndCols(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            int val = matrix[row][col];
            if (val == target) {
                return true;
            } else if (val > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    /**
     * Search in a 2D matrix that is sorted as if flattened into a 1D array.
     *
     * @param matrix The 2D matrix.
     * @param target The target integer to search for.
     * @return true if target is found, otherwise false.
     */
    public static boolean searchMatrixFlattenedSorted(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid / cols][mid % cols];

            if (val == target) {
                return true;
            } else if (val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
