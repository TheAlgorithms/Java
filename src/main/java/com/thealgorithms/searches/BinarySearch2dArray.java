package com.thealgorithms.searches;

/**
 * This class provides a method to search for a target value in a 2D sorted
 * array.
 * The search is performed using a combination of binary search on rows and
 * columns.
 * The 2D array must be strictly sorted in both rows and columns.
 *
 * The algorithm works by:
 * 1. Performing a binary search on the middle column of the 2D array.
 * 2. Depending on the value found, it eliminates rows above or below the middle
 * element.
 * 3. After finding or eliminating rows, it further applies binary search in the
 * relevant columns.
 */
public final class BinarySearch2dArray {

    private BinarySearch2dArray() {
    }

    /**
     * Performs a binary search on a 2D sorted array to find the target value.
     * The array must be sorted in ascending order in both rows and columns.
     *
     * @param arr    The 2D array to search in.
     * @param target The value to search for.
     * @return An array containing the row and column indices of the target, or [-1,
     *         -1] if the target is not found.
     */
    static int[] binarySearch(int[][] arr, int target) {
        int rowCount = arr.length;
        int colCount = arr[0].length;

        // Edge case: If there's only one row, search that row directly.
        if (rowCount == 1) {
            return binarySearch(arr, target, 0, 0, colCount);
        }

        // Set initial boundaries for binary search on rows.
        int startRow = 0;
        int endRow = rowCount - 1;
        int midCol = colCount / 2; // Middle column index for comparison.

        // Perform binary search on rows based on the middle column.
        while (startRow < endRow - 1) {
            int midRow = startRow + (endRow - startRow) / 2;

            // If the middle element matches the target, return its position.
            if (arr[midRow][midCol] == target) {
                return new int[] {midRow, midCol};
            }
            // If the middle element is smaller than the target, discard the upper half.
            else if (arr[midRow][midCol] < target) {
                startRow = midRow;
            }
            // If the middle element is larger than the target, discard the lower half.
            else {
                endRow = midRow;
            }
        }

        // If the target wasn't found during the row search, check the middle column of
        // startRow and endRow.
        if (arr[startRow][midCol] == target) {
            return new int[] {startRow, midCol};
        }

        if (arr[endRow][midCol] == target) {
            return new int[] {endRow, midCol};
        }

        // If target is smaller than the element in the left of startRow, perform a
        // binary search on the left of startRow.
        if (target <= arr[startRow][midCol - 1]) {
            return binarySearch(arr, target, startRow, 0, midCol - 1);
        }

        // If target is between midCol and the last column of startRow, perform a binary
        // search on that part of the row.
        if (target >= arr[startRow][midCol + 1] && target <= arr[startRow][colCount - 1]) {
            return binarySearch(arr, target, startRow, midCol + 1, colCount - 1);
        }

        // If target is smaller than the element in the left of endRow, perform a binary
        // search on the left of endRow.
        if (target <= arr[endRow][midCol - 1]) {
            return binarySearch(arr, target, endRow, 0, midCol - 1);
        } else {
            // Otherwise, search on the right of endRow.
            return binarySearch(arr, target, endRow, midCol + 1, colCount - 1);
        }
    }

    /**
     * Performs a binary search on a specific row of the 2D array.
     *
     * @param arr      The 2D array to search in.
     * @param target   The value to search for.
     * @param row      The row index where the target will be searched.
     * @param colStart The starting column index for the search.
     * @param colEnd   The ending column index for the search.
     * @return An array containing the row and column indices of the target, or [-1,
     *         -1] if the target is not found.
     */
    static int[] binarySearch(int[][] arr, int target, int row, int colStart, int colEnd) {
        // Perform binary search within the specified column range.
        while (colStart <= colEnd) {
            int midIndex = colStart + (colEnd - colStart) / 2;

            // If the middle element matches the target, return its position.
            if (arr[row][midIndex] == target) {
                return new int[] {row, midIndex};
            }
            // If the middle element is smaller than the target, move to the right half.
            else if (arr[row][midIndex] < target) {
                colStart = midIndex + 1;
            }
            // If the middle element is larger than the target, move to the left half.
            else {
                colEnd = midIndex - 1;
            }
        }

        return new int[] {-1, -1}; // Target not found
    }
}
