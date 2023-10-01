// This code defines a SudokuSolver class that can solve Sudoku puzzles using a backtracking algorithm.
// The solveSudoku method initiates the solving process, and solveSudokuHelper is a recursive function that tries to fill in the Sudoku cells one by one,
// backtracking when necessary. The isValid method checks if a number can be legally placed in a specific cell.
// Finally, the printBoard method is used to print the solved Sudoku board.

// Author name : AKASH BAG
//  GitHub link : https://github.com/Akashbag2001
// Wiki link: https://en.wikipedia.org/wiki/Sudoku_solving_algorithms

package main.java.com.thealgorithms.backtracking;
import java.util.*;
public class SudokuSolver {
    public static boolean solveSudoku(int[][] board) {
        return solveSudokuHelper(board, 0, 0);
    }

    private static boolean solveSudokuHelper(int[][] board, int row, int col) {
        if (row == 9) {
            return true; // We have successfully filled all cells (0-8)
        }

        if (col == 9) {
            return solveSudokuHelper(board, row + 1, 0); // Move to the next row
        }

        if (board[row][col] != 0) {
            return solveSudokuHelper(board, row, col + 1); // Cell is already filled, move to the next column
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num; // Place the valid number in the cell

                if (solveSudokuHelper(board, row, col + 1)) {
                    return true; // Continue solving
                }

                board[row][col] = 0; // Backtrack if no solution is found
            }
        }

        return false; // No valid number could be placed in this cell
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    
    }
}
