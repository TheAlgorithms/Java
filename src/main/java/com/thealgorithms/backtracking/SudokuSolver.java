package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm
 * 
 * This class implements a backtracking algorithm to solve a 9x9 Sudoku puzzle.
 * The algorithm systematically tries valid numbers in empty cells and backtracks
 * when it encounters an invalid state.
 * 
 * Time Complexity: O(9^m) where m is the number of empty cells
 * Space Complexity: O(m) for the recursion stack
 * 
 * @author Raghu0703
 */
public final class SudokuSolver {
    
    private static final int GRID_SIZE = 9;
    private static final int EMPTY_CELL = 0;

    private SudokuSolver() {
        // Utility class, no instantiation
    }

    /**
     * Checks if placing a number at a given position is valid according to Sudoku rules
     * 
     * @param board the Sudoku board
     * @param row the row index
     * @param col the column index
     * @param num the number to place
     * @return true if the placement is valid, false otherwise
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if num is already in the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if num is already in the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if num is already in the 3x3 subgrid
        int subgridRowStart = row - row % 3;
        int subgridColStart = col - col % 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Solves the Sudoku puzzle using backtracking
     * 
     * @param board the Sudoku board (0 represents empty cells)
     * @return true if the puzzle is solvable, false otherwise
     */
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == EMPTY_CELL) {
                    // Try numbers 1 through 9
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            // Place the number
                            board[row][col] = num;

                            // Recursively try to solve the rest
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack: undo the placement
                            board[row][col] = EMPTY_CELL;
                        }
                    }
                    // No valid number found, trigger backtracking
                    return false;
                }
            }
        }
        // All cells filled successfully
        return true;
    }

    /**
     * Prints the Sudoku board in a formatted manner
     * 
     * @param board the Sudoku board to print
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Example usage of the Sudoku Solver
     */
    public static void main(String[] args) {
        // Example Sudoku puzzle (0 represents empty cells)
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

        System.out.println("Original Sudoku Puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for this Sudoku puzzle.");
        }
    }
}
