package com.thealgorithms.backtracking;
/**
 * Solves the Sudoku puzzle using backtracking
 * @author SadafSk
 */
public class SudokuSolver {
    /**
     * Solve the Sudoku puzzle
     * @param board the Sudoku board as a 9x9 2D array.
     * @return true if a solution exists, false otherwise.
     */
    public static boolean solveSudoku(char[][] board) {
        return solve(board);
    }

    /**
     * Recursive function to solve the Sudoku puzzle
     * @param board the Sudoku board as a 9x9 2D array.
     * @return true if a solution exists, false otherwise.
     */
    private static boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if it's valid to place a number in a given position on the Sudoku board
     * @param board the Sudoku board as a 9x9 2D array.
     * @param row the current row being considered.
     * @param col the current column being considered.
     * @param num the number to be placed.
     * @return true if it's valid, false otherwise.
     */
    private static boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num || board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
    
}
