package com.thealgorithms.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    @Test
    void testSolvableSudoku() {
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

        assertTrue(SudokuSolver.solveSudoku(board), "The Sudoku puzzle should be solvable");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(board[i][j] >= 1 && board[i][j] <= 9, "All cells should contain numbers 1-9");
            }
        }

        int[][] expectedSolution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertArrayEquals(expectedSolution, board, "The solved board should match the expected solution");
    }

    @Test
    void testUnsolvableSudoku() {
        int[][] board = {
            {5, 5, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        assertFalse(SudokuSolver.solveSudoku(board), "Invalid Sudoku puzzle should be unsolvable");
    }

    @Test
    void testAlreadySolvedSudoku() {
        int[][] board = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertTrue(SudokuSolver.solveSudoku(board), "Already solved Sudoku should return true");
        
        int[][] expected = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        
        assertArrayEquals(expected, board, "Already solved board should remain unchanged");
    }

    @Test
    void testEmptySudoku() {
        int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        assertTrue(SudokuSolver.solveSudoku(board), "Empty Sudoku should be solvable");
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(board[i][j] >= 1 && board[i][j] <= 9, "All cells should be filled with 1-9");
            }
        }
    }

    @Test
    void testDifficultSudoku() {
        int[][] board = {
            {0, 0, 0, 0, 0, 0, 6, 8, 0},
            {0, 0, 0, 0, 7, 3, 0, 0, 9},
            {3, 0, 9, 0, 0, 0, 0, 4, 5},
            {4, 9, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 3, 0, 5, 0, 9, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 3, 6},
            {9, 6, 0, 0, 0, 0, 3, 0, 8},
            {7, 0, 0, 6, 8, 0, 0, 0, 0},
            {0, 2, 8, 0, 0, 0, 0, 0, 0}
        };

        assertTrue(SudokuSolver.solveSudoku(board), "Difficult Sudoku puzzle should be solvable");
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(board[i][j] >= 1 && board[i][j] <= 9, "All cells should contain numbers 1-9");
            }
        }
    }
}
