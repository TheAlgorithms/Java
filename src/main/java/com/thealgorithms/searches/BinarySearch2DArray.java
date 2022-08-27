package com.thealgorithms.searches;

import java.util.Arrays;

public class BinarySearch2DArray {

/* To apply binary search on a 2d Array, the provided array must be strictly sorted. In this method,
*  two pointers, one at 0th row and the other at the last row are taken and the searching is done on
*  the basis of the middle element of the middle column. If that element is equal to target, its coordinates
*  are returned, else if it is smaller than the target element, the rows above that element are ignored (because
*  the elements above it will also be smaller than the target), else if that element is greater than the target,
*  then the rows below it are ignored.
*/
    public static void main(String[] args) {
        int[][] arr = {                         //The provided array should be strictly increasing
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {10, 11, 12, 13}
        };
        int target = 4;
        int[] points = BinarySearch(arr, target);

        if (points[0] == -1) {                 //if the returned index is -1, that means target doesn't exist in the array
            System.out.println(target + " doesn't exists in the array");
        }
        else {
            System.out.println(target + " is at points: " + Arrays.toString(points));
        }
    }
/*
 * In this function, target element will be searched at the middle column
 * and the search space will be reduced accordingly.
 */
    private static int[] BinarySearch(int[][] arr, int target) {

        int rowCount = arr.length, colCount = arr[0].length;

        if (rowCount == 1) {
            return binarySearch(arr, target, 0, 0, colCount);
        }
        int startRow = 0, endRow = arr.length-1, midCol = colCount/2;

        while (startRow < endRow - 1) {

            int midRow = startRow + (endRow - startRow) / 2;

            if (arr[midRow][midCol] == target)
                return new int[] {midRow, midCol};

            else if (arr[midRow][midCol] < target)
                startRow = midRow;

            else
                endRow = midRow;
        }

        if (arr[startRow][midCol] == target)
            return new int[] {startRow, midCol};

        else if (arr[endRow][midCol] == target)
            return new int[] {endRow, midCol};

        else if (target <= arr[startRow][midCol-1])
            return binarySearch(arr, target, startRow, 0, midCol - 1);

        else if (target >= arr[startRow][midCol+1] && target <= arr[startRow][colCount-1])
            return binarySearch(arr, target, startRow, midCol + 1, colCount - 1);

        else if (target <= arr[endRow][midCol-1])
            return binarySearch(arr, target, endRow, 0, midCol - 1);

        return binarySearch(arr, target, endRow, midCol + 1, colCount - 1);
    }

 /* If the target element can't be found by the above function then,
  * the leftover space will be searched by the below function.
 */

    private static int[] binarySearch(int[][] arr, int target, int row, int colStart, int colEnd) {

        while (colStart <= colEnd) {

            int mid = colStart + (colEnd - colStart) / 2;

            if (arr[row][mid] == target)
                return new int[] {row, mid};

            else if (arr[row][mid] > target)
                colEnd = mid - 1;

            else
                colStart = mid + 1;
        }
        return new int[] {-1, -1};
    }


}
