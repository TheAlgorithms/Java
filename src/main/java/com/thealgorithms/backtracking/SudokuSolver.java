package com.thealgorithms.backtracking;

/**
 * The {@code SudokuSolver} class provides a solution to Sudoku puzzles
 * Solves the puzzle by a depth-first backtracking algorithm.
 *
 * The algorithm fills empty cells (represented by 0) by trying digits 1-9
 * and recursively verifying if they lead to a valid solution.
 *
 * Time Complexity: O(9^{n*n}) where n is the dimension of the grid.
 * Space Complexity: O(n*n) to store the board and recursion stack.
 * 
 * @author subhammohanty-sys
 */

public class SudokuSolver {

    private SudokuSolver(){}

    /**
     * Entry point to solve the Sudoku puzzle. 
     * Performs a pre-validation check before starting the recursion.
     *
     * @param board 2D array representing the Sudoku grid (0 for empty cells).
     * @return true if the board is solvable, false otherwise.
     */
    public static boolean solveSudoku(int[][] board) {
        if (board == null || board.length !=9 || !isBoardValid(board)) {
            return false;
        }

        return solve(board);
    }


    /**
     * Validates the initial state of the board to ensure no existing 
     * numbers violate Sudoku rules.
     *
     * @param board The Sudoku grid.
     * @return true if the initial board configuration is valid.
     */


    private static boolean isBoardValid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    int num = board[i][j];
                    board[i][j] = 0; // Temporarily empty to validate
                    if (!isValid(board, i, j, num)) {
                        return false;
                    }

                    board[i][j] = num;
                }
            }
        }

        return true;
    }

    /**
     * Checks if placing a number in a specific cell is legal.
     *
     * @param board The Sudoku grid.
     * @param row Row index.
     * @param col Column index.
     * @param num Number to place (1-9).
     * @return true if the placement is safe.
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            // Check row and column constraints
            if (board[i][col] == num || board[row][i] == num) {
                return false;
            }
        }

        // Check 3x3 sub-grid constraints
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Internal recursive helper that implements the backtracking logic.
     *
     * @param board The Sudoku grid being solved.
     * @return true if a solution is found.
     */
    private static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Demonstration of the solver.
     */
    public static void main(String[] args) {

        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (SudokuSolver.solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    /**
     * Helper method to print the board in a readable format.
     *
     * @param board The Sudoku grid.
     */
    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("---------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}