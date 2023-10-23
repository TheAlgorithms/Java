package com.thealgorithms.datastructures.matrix;

public class ZeroMatrix {
    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // Check the first row and first column for zeros
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // Mark rows and columns with zeros using the first row and first column
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero out rows based on the first column
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out columns based on the first row
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero the first row and first column if necessary
        if (firstRowHasZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColHasZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);

        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
/**
 * Imagine we have a grid, like a chessboard, where we store numbers. This grid is our "matrix." Each cell in the grid is like a house, and inside each house, there's a number.
 *
 * Now, we have a magic rule: if any house in a row or a column has a number zero, we must make the entire row and the entire column become zeros as well. It's like a domino effect!
 *
 * Here's our plan to follow the magic rule:
 *
 * Step 1:We'll check the first row and the first column (the very top and the very left). If any of them has a zero, we'll remember that fact. We'll use two special flags called firstRowHasZero and firstColHasZero to remember.
 *
 * Step 2: We'll go through the other houses in our grid (the ones not in the first row or first column). If we find a house with a zero, we'll make a note of that by setting the first cell of its row and the first cell of its column to zero. This is like leaving a mark on the first row and the first column, saying, "Hey, there's a zero in this row and column!"
 *
 * Step 3: After checking all the houses, we will go back and look at our marks on the first row and first column. If the firstRowHasZero flag is set, we'll turn the whole first row into zeros. If the firstColHasZero flag is set, we'll turn the entire first column into zeros.
 *
 * Step 4: Finally, we'll go back to each house (each cell) and see if it's in a row or column marked for zero. If it is, we'll turn that house into a zero.
 *
 * In the end, our grid will look like we applied the magic rule everywhere we found a zero.
 *
 * Time Complexity:
 * The time complexity of this code is O(m * n), where 'm' is the number of rows and 'n' is the number of columns in the matrix. This is because we iterate through the entire matrix once to find zeros and then a second time to mark and update the rows and columns. The code does not depend on the size of the matrix in a nested loop, making it an efficient solution.
 */