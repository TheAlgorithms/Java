package com.thealgorithms.backtracking;

/**
 * Solves a Sudoku of any level and prints solved Sudoku.
 * This class is a utility and should not be instantiated.
 * @author Indraneela Doradla (<a href="https://github.com/captiosus1">git-Indraneela Doradla</a>)
 */
public final class SudokuSolver {

    /**
     * Private constructor to prevent instantiation.
     */
    private SudokuSolver() {
        // Utility class
    }

    /**
     * Solves the Sudoku using backtracking.
     * @param board the Sudoku grid
     * @return boolean indicating if the Sudoku can be solved
     */
    public static boolean solveSudoku(int[][] board) {
        int r = -1;
        int c = -1;
        boolean isEmpty = true;

        // Find the first empty position
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    r = i;
                    c = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If no empty position is found, the Sudoku is solved
        if (isEmpty) {
            return true;
        }

        // Try filling the empty position with numbers from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, r, c, num)) {
                board[r][c] = num;
                if (solveSudoku(board)) {
                    return true; // Successfully solved
                }
                board[r][c] = 0; // Backtrack
            }
        }

        return false; // No solution found, backtrack
    }

    /**
     * Checks if placing a number at the given position is valid.
     * @param board the Sudoku grid
     * @param r row index
     * @param c column index
     * @param val the value to place
     * @return boolean indicating if it's safe to place the value
     */
    public static boolean isSafe(int[][] board, int r, int c, int val) {
        // Check the row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == val || board[r][i] == val) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = r - r % sqrt;
        int boxColStart = c - c % sqrt;

        for (int i = boxRowStart; i < boxRowStart + sqrt; i++) {
            for (int j = boxColStart; j < boxColStart + sqrt; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku grid.
     * @param board the Sudoku grid
     */
    public static void printSudoku(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
