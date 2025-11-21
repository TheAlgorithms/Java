package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm.
 * Solves a 9x9 Sudoku puzzle by filling empty cells with valid digits (1-9).
 *
 * @author prasanth-30011
 */
public final class SudokuSolver {

    private static final int GRID_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    private SudokuSolver() {
        // Prevent instantiation
    }

    /**
     * Public method to solve a Sudoku puzzle.
     *
     * @param board 9x9 Sudoku grid (0 means empty)
     * @return true if solved, false otherwise
     */
    public static boolean solveSudoku(int[][] board) {
        if (board == null || board.length != GRID_SIZE) {
            return false;
        }

        for (int row = 0; row < GRID_SIZE; row++) {
            if (board[row].length != GRID_SIZE) {
                return false;
            }
        }

        return solve(board);
    }

    /**
     * Recursive helper that applies backtracking.
     *
     * @param board Sudoku board
     * @return true if solved, false otherwise
     */
    private static boolean solve(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {

                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidPlacement(board, row, col, number)) {
                            board[row][col] = number;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = EMPTY_CELL; // Backtrack
                        }
                    }

                    return false; // No number fits here
                }
            }
        }
        return true; // Solved completely
    }

    private static boolean isValidPlacement(int[][] board, int row, int col, int number) {
        // Row and column check
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number || board[i][col] == number) {
                return false;
            }
        }
 // Subgrid check
        int startRow = (row / SUBGRID_SIZE) * SUBGRID_SIZE;
        int startCol = (col / SUBGRID_SIZE) * SUBGRID_SIZE;

        for (int r = 0; r < SUBGRID_SIZE; r++) {
            for (int c = 0; c < SUBGRID_SIZE; c++) {
                if (board[startRow + r][startCol + c] == number) {
                    return false;
                }
            }
        }

        return true;
    }
}
