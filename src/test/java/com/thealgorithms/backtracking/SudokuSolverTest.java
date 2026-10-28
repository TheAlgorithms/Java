package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SudokuSolverTest {

    @Test
    void testSolveSudokuEasyPuzzle() {
        int[][] board = {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        assertTrue(SudokuSolver.solveSudoku(board));

        int[][] expected = {{5, 3, 4, 6, 7, 8, 9, 1, 2}, {6, 7, 2, 1, 9, 5, 3, 4, 8}, {1, 9, 8, 3, 4, 2, 5, 6, 7}, {8, 5, 9, 7, 6, 1, 4, 2, 3}, {4, 2, 6, 8, 5, 3, 7, 9, 1}, {7, 1, 3, 9, 2, 4, 8, 5, 6}, {9, 6, 1, 5, 3, 7, 2, 8, 4}, {2, 8, 7, 4, 1, 9, 6, 3, 5}, {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        assertArrayEquals(expected, board);
    }

    @Test
    void testSolveSudokuHardPuzzle() {
        int[][] board = {{0, 0, 0, 0, 0, 0, 6, 8, 0}, {0, 0, 0, 0, 7, 3, 0, 0, 9}, {3, 0, 9, 0, 0, 0, 0, 4, 5}, {4, 9, 0, 0, 0, 0, 0, 0, 0}, {8, 0, 3, 0, 5, 0, 9, 0, 2}, {0, 0, 0, 0, 0, 0, 0, 3, 6}, {9, 6, 0, 0, 0, 0, 3, 0, 8}, {7, 0, 0, 6, 8, 0, 0, 0, 0}, {0, 2, 8, 0, 0, 0, 0, 0, 0}};

        assertTrue(SudokuSolver.solveSudoku(board));
    }

    @Test
    void testSolveSudokuAlreadySolved() {
        int[][] board = {{5, 3, 4, 6, 7, 8, 9, 1, 2}, {6, 7, 2, 1, 9, 5, 3, 4, 8}, {1, 9, 8, 3, 4, 2, 5, 6, 7}, {8, 5, 9, 7, 6, 1, 4, 2, 3}, {4, 2, 6, 8, 5, 3, 7, 9, 1}, {7, 1, 3, 9, 2, 4, 8, 5, 6}, {9, 6, 1, 5, 3, 7, 2, 8, 4}, {2, 8, 7, 4, 1, 9, 6, 3, 5}, {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        assertTrue(SudokuSolver.solveSudoku(board));
    }

    @Test
    void testSolveSudokuInvalidSize() {
        int[][] board = {{1, 2, 3}, {4, 5, 6}};
        assertFalse(SudokuSolver.solveSudoku(board));
    }

    @Test
    void testSolveSudokuNullBoard() {
        assertFalse(SudokuSolver.solveSudoku(null));
    }

    @Test
    void testSolveSudokuEmptyBoard() {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        assertTrue(SudokuSolver.solveSudoku(board));
    }
}
