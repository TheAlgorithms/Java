package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking algorithm
 * Solves a 9x9 Sudoku puzzle using depth-first search with backtracking
  *
 * @author Your Name
 */
public final class SudokuSolver {
    private static final int BOARD_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    private SudokuSolver() {
    }

    /**
     * Solves the Sudoku puzzle using backtracking
     *
     * @param board 9x9 Sudoku board (0 represents empty cells)
     * @return true if puzzle is solvable, false otherwise
     */
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    for (int num = 1; num <= BOARD_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

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
     * Checks if placing a number at a given position is valid
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        return isRowOk(board, row, num) && isColOk(board, col, num) && isSubgridOk(board, row, col, num);
    }

    /**
     * Checks if number exists in the row
     */
    private static boolean isRowOk(int[][] board, int row, int num) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if number exists in the column
     */
    private static boolean isColOk(int[][] board, int col, int num) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if number exists in the 3x3 subgrid
     */
    private static boolean isSubgridOk(int[][] board, int row, int col, int num) {
        int subgridRowStart = row - row % SUBGRID_SIZE;
        int subgridColStart = col - col % SUBGRID_SIZE;

        for (int r = subgridRowStart; r < subgridRowStart + SUBGRID_SIZE; r++) {
            for (int c = subgridColStart; c < subgridColStart + SUBGRID_SIZE; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the Sudoku board
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Main method demonstrating Sudoku solver functionality
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int[][] board = {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        System.out.println("Sudoku Puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for this Sudoku puzzle.");
        }

    }
}
