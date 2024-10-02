package com.thealgorithms.backtracking;

/**
 * Java program for Sudoku Solver using Backtracking Algorithm.
 * Solves a given Sudoku puzzle by filling the empty cells (denoted by '0') with
 * valid digits (1-9).
 */
public final class SudokuSolver {
    private SudokuSolver() {
    }

    /**
     * Check if placing a number in a specific cell is valid.
     * Validity is checked based on the row, column, and 3x3 subgrid.
     *
     * @param board The 9x9 Sudoku board
     * @param row   The row index
     * @param col   The column index
     * @param num   The number to be placed in the cell
     * @return true if placing the number is valid, false otherwise
     */
    public static boolean isValidMove(final int[][] board, final int row, final int col, final int num) {
        // Check the row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 sub-grid
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Solve the Sudoku puzzle using backtracking.
     *
     * @param board The 9x9 Sudoku board
     * @return true if the puzzle is solved, false if no solution exists
     */
    public static boolean solveSudoku(final int[][] board) {
        // Traverse the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Empty cell found
                if (board[row][col] == 0) {
                    // Try placing numbers 1-9 in the empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively try to solve the board with the new number
                            if (solveSudoku(board)) {
                                return true; // Solution found
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number can be placed in this cell
                }
            }
        }

        return true; // Sudoku is solved if no empty cells are left
    }

    /**
     * Print the current state of the Sudoku board.
     *
     * @param board The 9x9 Sudoku board
     */
    public static void printBoard(final int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example 9x9 Sudoku board with some cells pre-filled and some cells empty (0)
        int[][] board = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        System.out.println("Original Sudoku Board:");
        printBoard(board);

        // Solve the Sudoku puzzle
        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Board:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }
}
