package main.java.com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm
 *
 * Solves a 9x9 Sudoku puzzle. All methods are static and class is final to
 * match
 * TheAlgorithms/Java repo conventions.
 */
public final class SudokuSolver {

    private SudokuSolver() {
    } // Prevent instantiation

    /**
     * Solves the Sudoku puzzle using backtracking.
     *
     * @param sudoku 9x9 Sudoku grid, empty cells are 0
     * @param row    current row
     * @param col    current column
     * @return true if solved, false otherwise
     */
    public static boolean sudokuSolver(int[][] sudoku, int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow = row;
        int nextCol = col + 1;
        if (nextCol == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }

        if (sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextRow, nextCol);
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(sudoku, row, col, num)) {
                sudoku[row][col] = num;
                if (sudokuSolver(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = 0; // backtrack
            }
        }

        return false;
    }

    /**
     * Checks if placing num at (row, col) is safe.
     *
     * @param sudoku 9x9 Sudoku grid
     * @param row    current row
     * @param col    current column
     * @param num    number to place
     * @return true if safe, false otherwise
     */
    private static boolean isSafe(int[][] sudoku, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == num || sudoku[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku grid.
     *
     * @param sudoku 9x9 Sudoku grid
     */
    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
