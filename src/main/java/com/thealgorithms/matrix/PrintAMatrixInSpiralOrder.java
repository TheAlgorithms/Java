package com.thealgorithms.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to print a matrix in spiral order.
 * <p>
 * Given a 2D array (matrix), this class provides a method to return the
 * elements
 * of the matrix in spiral order, starting from the top-left corner and moving
 * clockwise.
 * </p>
 *
 * @author Sadiul Hakim (https://github.com/sadiul-hakim)
 */
public class PrintAMatrixInSpiralOrder {

    /**
     * Returns the elements of the given matrix in spiral order.
     *
     * @param matrix the 2D array to traverse in spiral order
     * @param row    the number of rows in the matrix
     * @param col    the number of columns in the matrix
     * @return a list containing the elements of the matrix in spiral order
     *
     *         <p>
     *         Example:
     *
     *         <pre>
     * int[][] matrix = {
     *   {1, 2, 3},
     *   {4, 5, 6},
     *   {7, 8, 9}
     * };
     * print(matrix, 3, 3) returns [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *         </pre>
     *         </p>
     */
    public List<Integer> print(int[][] matrix, int row, int col) {
        // r traverses matrix row wise from first
        int r = 0;
        // c traverses matrix column wise from first
        int c = 0;
        int i;
        List<Integer> result = new ArrayList<>();
        while (r < row && c < col) {
            // print first row of matrix
            for (i = c; i < col; i++) {
                result.add(matrix[r][i]);
            }
            // increase r by one because first row printed
            r++;
            // print last column
            for (i = r; i < row; i++) {
                result.add(matrix[i][col - 1]);
            }
            // decrease col by one because last column has been printed
            col--;
            // print rows from last except printed elements
            if (r < row) {
                for (i = col - 1; i >= c; i--) {
                    result.add(matrix[row - 1][i]);
                }
                row--;
            }
            // print columns from first except printed elements
            if (c < col) {
                for (i = row - 1; i >= r; i--) {
                    result.add(matrix[i][c]);
                }
                c++;
            }
        }
        return result;
    }
}
