package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SudokuSolverTest {

      @Test
      public void testSudokuSolverSolvesValidBoard() {
            int[][] sudoku = {
                        { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                        { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                        { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                        { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                        { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                        { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                        { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
            };

            boolean solved = SudokuSolver.sudokuSolver(sudoku, 0, 0);
            assertTrue(solved, "SudokuSolver should solve a valid board");
      }

      @Test
      public void testSudokuSolverRejectsInvalidBoard() {
            int[][] sudoku = new int[9][9];
            sudoku[0][0] = 5;
            sudoku[0][1] = 5; // invalid duplicate in row

            boolean solved = SudokuSolver.sudokuSolver(sudoku, 0, 0);
            assertFalse(solved, "SudokuSolver should not solve an invalid board");
      }
}
