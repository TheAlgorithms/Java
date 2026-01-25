package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm
 * Solves a 9x9 Sudoku puzzle by filling empty cells with valid digits (1-9)
 *
 * @author Navadeep0007
 */
public final class SudokuSolver {

    private static final int GRID_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    private SudokuSolver() {
        // Utility class, prevent instantiation
    }

    /**
     * Solves the Sudoku puzzle using backtracking
     *
     * @param board 9x9 Sudoku board with 0 representing empty cells
     * @return true if puzzle is solved, false otherwise
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
     * Recursive helper method to solve the Sudoku puzzle
     *
     * @param board the Sudoku board
     * @return true if solution is found, false otherwise
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

                            // Backtrack
                            board[row][col] = EMPTY_CELL;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if placing a number at given position is valid
     *
     * @param board the Sudoku board
     * @param row row index
     * @param col column index
     * @param number number to place (1-9)
     * @return true if placement is valid, false otherwise
     */
    private static boolean isValidPlacement(int[][] board, int row, int col, int number) {
        return !isNumberInRow(board, row, number) && !isNumberInColumn(board, col, number) && !isNumberInSubgrid(board, row, col, number);
    }

    /**
     * Checks if number exists in the given row
     *
     * @param board the Sudoku board
     * @param row row index
     * @param number number to check
     * @return true if number exists in row, false otherwise
     */
    private static boolean isNumberInRow(int[][] board, int row, int number) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if number exists in the given column
     *
     * @param board the Sudoku board
     * @param col column index
     * @param number number to check
     * @return true if number exists in column, false otherwise
     */
    private static boolean isNumberInColumn(int[][] board, int col, int number) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if number exists in the 3x3 subgrid
     *
     * @param board the Sudoku board
     * @param row row index
     * @param col column index
     * @param number number to check
     * @return true if number exists in subgrid, false otherwise
     */
    private static boolean isNumberInSubgrid(int[][] board, int row, int col, int number) {
        int subgridRowStart = row - row % SUBGRID_SIZE;
        int subgridColStart = col - col % SUBGRID_SIZE;

        for (int i = subgridRowStart; i < subgridRowStart + SUBGRID_SIZE; i++) {
            for (int j = subgridColStart; j < subgridColStart + SUBGRID_SIZE; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints the Sudoku board
     *
     * @param board the Sudoku board
     */
    public static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % SUBGRID_SIZE == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % SUBGRID_SIZE == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
