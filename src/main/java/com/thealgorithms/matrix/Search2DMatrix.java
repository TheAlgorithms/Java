package com.thealgorithms.matrix;

/**
 * Search a 2D Matrix.
 * Reference: https://leetcode.com/problems/search-a-2d-matrix/
 */
public class Search2DMatrix {

    // Private constructor to satisfy Checkstyle utility class rule
    private Search2DMatrix() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Performs binary search on a 2D matrix.
     *
     * @param matrix the 2D matrix
     * @param target the value to search
     * @return true if found, false otherwise
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
