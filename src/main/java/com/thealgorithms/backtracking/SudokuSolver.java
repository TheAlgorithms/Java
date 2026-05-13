package com.thealgorithms.backtracking;

/**
 * The {@code SudokuSolver} class provides a solution to Sudoku puzzles.
 * Solves the puzzle by a depth-first backtracking algorithm.
 *
 * <p>The algorithm fills empty cells (represented by 0) by trying digits 1-9 and
 * recursively verifying if they lead to a valid solution.
 *
 * <p>Time Complexity: O(9^{n*n}) where n is the dimension of the grid.
 * Space Complexity: O(n*n) to store the board and recursion stack.
 *
 * @author subhammohanty-sys
 */
public final class SudokuSolver {

    private SudokuSolver() {
    }

    /**
     * Entry point to solve the Sudoku puzzle. Performs a pre-validation check
     * before starting the recursion.
     *
     * @param board 2D array representing the Sudoku grid (0 for empty cells).
     * @return true if the board is solvable, false otherwise.
     */
    public static boolean solveSudoku(int[][] board) {
        if (board == null || board.length != 9) {
            return false;
        }

        for (int[] row : board) {
            if (row == null || row.length != 9) {
                return false;
            }
        }

        if (!isBoardValid(board)) {
            return false;
        }

        return solve(board);
    }

    private static boolean isBoardValid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    int num = board[i][j];
                    board[i][j] = 0;
                    if (!isValid(board, i, j, num)) {
                        return false;
                    }
                    board[i][j] = num;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num || board[row][i] == num) {
                return false;
            }
        }

        int sr = row / 3 * 3;
        int sc = col / 3 * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

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
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
