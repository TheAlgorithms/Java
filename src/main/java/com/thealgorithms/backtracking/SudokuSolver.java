package com.thealgorithms.backtracking;

/**
 * Program description - Solves a given 9x9 Sudoku puzzle using backtracking.
 * <a href="https://en.wikipedia.org/wiki/Sudoku">Wikipedia</a>
 *
 * @author <a href="https://github.com/codewithRakshita">Rakshita</a>
 */
public class SudokuSolver {

    private static final int SIZE = 9; // size of the Sudoku grid

    /**
     * Solves the Sudoku puzzle by filling empty cells.
     *
     * @param board The 9x9 Sudoku puzzle grid
     * @return true if the Sudoku puzzle is solvable, false otherwise
     */
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                // Check for an empty cell
                if (board[row][col] == 0) {

                    // Try numbers 1 to 9
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively try filling the rest
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    // No valid number found — backtrack
                    return false;
                }
            }
        }

        return true; // solved
    }

    /**
     * Checks whether placing a number at a specific position is valid.
     */
    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku grid.
     */
    public void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Example usage (for testing)
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

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            solver.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
