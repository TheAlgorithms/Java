package com.thealgorithms.matrix;

/**
 * Search a 2D Matrix
 * Each row is sorted left to right, and the first element of each row
 * is greater than the last element of the previous row.
 * 
 * Time Complexity: O(log(m*n))
 * Space Complexity: O(1)
 */
public class Search2DMatrix {

    /**
     * Searches for a target value in a 2D matrix using binary search.
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
