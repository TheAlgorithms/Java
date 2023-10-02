package com.thealgorithms.backtracking;

import java.util.*;

public class SudokuSolver {

    // Method to solve a Sudoku puzzle using backtracking
    public boolean solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    // Try placing digits '1' to '9' in the empty cell
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValidMove(board, row, col, num)) {
                            // If the move is valid, place the digit and proceed recursively
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true; // Successfully solved the puzzle
                            } else {
                                board[row][col] = '.'; // Undo the move if it leads to an invalid solution
                            }
                        }
                    }
                    return false; // No valid move for this cell, backtrack
                }
            }
        }
        return true; // All cells filled, puzzle solved
    }

    // Method to check if placing 'num' at (row, col) is a valid move
    private boolean isValidMove(char[][] board, int row, int col, char num) {
        // Check the row and column for duplicates of 'num'
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false; // Invalid move
            }
        }

        // Check the 3x3 subgrid for duplicates of 'num'
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false; // Invalid move
                }
            }
        }

        return true; // Valid move
    }
}
