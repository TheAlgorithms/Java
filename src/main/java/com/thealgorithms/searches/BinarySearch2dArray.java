package com.thealgorithms.searches;

public class BinarySearch2dArray {

    public static int[] binarySearch(int[][] arr, int target) {
        // Get the number of rows and columns in the 2D array
        int rowCount = arr.length;
        int colCount = arr[0].length;

        // Initialize the startRow, endRow, and midCol variables
        int startRow = 0;
        int endRow = rowCount - 1;
        int midCol = colCount / 2;

        // Perform binary search in the 2D array
        while (startRow <= endRow) {
            // Find the middle row index
            int midRow = startRow + (endRow - startRow) / 2;

            // Check if the middle element of the middle column is equal to the target
            if (arr[midRow][midCol] == target) {
                return new int[] { midRow, midCol }; // Return the coordinates of the target element
            }
            // If the middle element is smaller than the target, update the startRow
            else if (arr[midRow][midCol] < target) {
                startRow = midRow + 1;
            }
            // If the middle element is greater than the target, update the endRow
            else {
                endRow = midRow - 1;
            }
        }

        return new int[] { -1, -1 }; // Target element not found
    }
}
