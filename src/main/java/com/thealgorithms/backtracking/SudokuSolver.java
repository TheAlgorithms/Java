package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm.
 *
 * Solves a 9×9 Sudoku grid by filling empty cells (0) such that every row,
 * column, and 3×3 subgrid contains all digits 1–9 exactly once.
 */
public class SudokuSolver {

    private static final int SIZE = 9;
    private static final int SUBGRID = 3;
    private static final int EMPTY = 0;

    /**
     * Public API: Validates the initial board and triggers the solver.
     *
     * @param board 9×9 Sudoku grid
     * @return true if solvable
     */
    public static boolean solveSudoku(int[][] board) {
        if (!isInitialBoardValid(board)) {
            return false;
        }
        return solve(board);
    }

    /**
     * Solves Sudoku recursively using backtracking.
     */
    private static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == EMPTY) {

                    for (int num = 1; num <= SIZE; num++) {

                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = EMPTY;
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validates initial puzzle (checks duplicates in rows/cols/subgrids).
     */
    public static boolean isInitialBoardValid(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                int num = board[row][col];

                if (num != EMPTY) {
                    board[row][col] = EMPTY;

                    if (!isValid(board, row, col, num)) {
                        board[row][col] = num;
                        return false;
                    }

                    board[row][col] = num;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        return isRowOk(board, row, num)
                && isColOk(board, col, num)
                && isSubgridOk(board, row, col, num);
    }

    private static boolean isRowOk(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColOk(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSubgridOk(int[][] board, int row, int col, int num) {
        int startRow = row - (row % SUBGRID);
        int startCol = col - (col % SUBGRID);

        for (int i = startRow; i < startRow + SUBGRID; i++) {
            for (int j = startCol; j < startCol + SUBGRID; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Utility: Print Sudoku grid.
     */
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {

            if (row % SUBGRID == 0 && row != 0) {
                System.out.println("-----------");
            }

            for (int col = 0; col < SIZE; col++) {

                if (col % SUBGRID == 0 && col != 0) {
                    System.out.print("|");
                }

                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
