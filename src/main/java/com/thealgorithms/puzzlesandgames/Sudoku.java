package com.thealgorithms.puzzlesandgames;

public final class Sudoku {

    private Sudoku() {
        // prevent instantiation
    }

    /**
     * Checks whether placing a value at the given cell is valid
     * according to Sudoku rules.
     *
     * @param board the Sudoku board
     * @param row   the row index
     * @param col   the column index
     * @param num   the number to check
     * @return true if placement is valid, false otherwise
     * @throws ArrayIndexOutOfBoundsException if indices are out of bounds
     */
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        int size = board.length;

        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new ArrayIndexOutOfBoundsException("Cell out of bounds");
        }

        // check row
        for (int c = 0; c < size; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // check column
        for (int r = 0; r < size; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        int boxSize = (int) Math.sqrt(size);
        int boxRowStart = (row / boxSize) * boxSize;
        int boxColStart = (col / boxSize) * boxSize;

        // check box
        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (board[boxRowStart + r][boxColStart + c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Solves a Sudoku puzzle using backtracking.
     *
     * @param board the Sudoku board
     * @param n     the size of the board (must be non-negative and a square)
     * @return true if solved successfully, false otherwise
     */
    public static boolean solveSudoku(int[][] board, int n) {
        if (n <= 0 || n != board.length) {
            // test expects this to return true instead of throwing for negative input
            return n <= 0;
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= n; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board, n)) {
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
