package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SudokuTest {

    @Test
    void testIsSafe2() {
        int[][] board = {{3, 0, 6, 5, 0, 8, 4, 0, 0}, {5, 2, 0, 0, 0, 0, 0, 0, 0}, {0, 8, 7, 0, 0, 0, 0, 3, 1}, {0, 0, 3, 0, 1, 0, 0, 8, 0}, {9, 0, 0, 8, 6, 3, 0, 0, 5}, {0, 5, 0, 0, 9, 0, 6, 0, 0}, {1, 3, 0, 0, 0, 0, 2, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 4}, {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        assertFalse(Sudoku.isSafe(board, 0, 1, 3));
        assertTrue(Sudoku.isSafe(board, 1, 2, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { Sudoku.isSafe(board, 10, 10, 5); });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { Sudoku.isSafe(board, -1, 0, 5); });
    }

    @Test
    void testSolveSudoku() {
        int[][] board = {{3, 0, 6, 5, 0, 8, 4, 0, 0}, {5, 2, 0, 0, 0, 0, 0, 0, 0}, {0, 8, 7, 0, 0, 0, 0, 3, 1}, {0, 0, 3, 0, 1, 0, 0, 8, 0}, {9, 0, 0, 8, 6, 3, 0, 0, 5}, {0, 5, 0, 0, 9, 0, 6, 0, 0}, {1, 3, 0, 0, 0, 0, 2, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 4}, {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        assertTrue(Sudoku.solveSudoku(board, board.length));
        assertEquals(1, board[0][1]);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { Sudoku.solveSudoku(board, 10); });
        assertTrue(Sudoku.solveSudoku(board, -1));
    }

    @Test
    void testUnsolvableSudoku() {
        int[][] unsolvableBoard = {{5, 1, 6, 8, 4, 9, 7, 3, 2}, {3, 0, 7, 6, 0, 5, 0, 0, 0}, {8, 0, 9, 7, 0, 0, 0, 6, 5}, {1, 3, 5, 0, 6, 0, 9, 0, 7}, {4, 7, 2, 5, 9, 1, 0, 0, 6}, {9, 6, 8, 3, 7, 0, 0, 5, 0}, {2, 5, 3, 1, 8, 6, 0, 7, 4}, {6, 8, 4, 2, 5, 7, 3, 9, 0}, {7, 9, 1, 4, 3, 0, 5, 0, 0}};

        assertFalse(Sudoku.solveSudoku(unsolvableBoard, unsolvableBoard.length));
    }
}
