package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.MatrixSearchAlgorithm;

/**
 * The search is for any array which is sorted row and column-wise too. For ex :
 * {{10, 20, 30, 40},
 *  {15, 25, 35, 45},
 *  {18, 28, 38, 48},
 *  {21, 31, 41, 51}}
 *
 * This array is sorted in both row and column manner.
 * In this two pointers are taken, the first points to the 0th row and the second one points to end
 * column, and then the element corresponding to the pointers placed in the array is compared with
 * the target that either its equal, greater or smaller than the target. If the element is equal to
 * the target, the co-ordinates of that element is returned i.e. an array of the two pointers will
 * be returned, else if the target is greater than corresponding element then the pointer pointing
 * to the 0th row will be incremented by 1, else if the target is lesser than the corresponding
 * element then the pointer pointing to the end column will be decremented by 1. And if the element
 * doesn't exist in the array, an array
 * {-1, -1} will be returned.
 */
public class RowColumnWiseSorted2dArrayBinarySearch implements MatrixSearchAlgorithm {

    @Override
    public <T extends Comparable<T>> int[] find(T[][] matrix, T key) {
        return search(matrix, key);
    }

    public static <T extends Comparable<T>> int[] search(T[][] matrix, T target) {
        int rowPointer = 0; // The pointer at 0th row
        int colPointer = matrix.length - 1; // The pointer at end column

        while (rowPointer < matrix.length && colPointer >= 0) {
            int comp = target.compareTo(matrix[rowPointer][colPointer]);

            if (comp == 0) {
                return new int[] {rowPointer, colPointer};
            } else if (comp > 0) {
                rowPointer++; // Incrementing the row pointer if the target is greater
            } else {
                colPointer--; // Decrementing the column pointer if the target is lesser
            }
        }
        return new int[] {-1, -1}; // The not found condition
    }
}
