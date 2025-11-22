package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SudokuSolverTest {

  @Test
  public void testSolvableSudoku() {
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

    assertTrue(SudokuSolver.solveSudoku(board),
        "Sudoku should be solvable");
    assertBoardValid(board);
  }

  @Test
  public void testUnsolvableSudoku() {
    int[][] board = {
        {5, 3, 5, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    assertFalse(SudokuSolver.solveSudoku(board),
        "Sudoku should not be solvable");
  }

  @Test
  public void testCompleteBoard() {
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

    assertTrue(SudokuSolver.solveSudoku(board),
        "Already solved Sudoku should return true");
    assertBoardValid(board);
  }

  /**
   * Helper method to validate that the board is correctly solved.
   *
   * @param board the solved Sudoku board
   */
  private void assertBoardValid(int[][] board) {
    // Check rows
    for (int row = 0; row < 9; row++) {
      boolean[] seen = new boolean[10];
      for (int col = 0; col < 9; col++) {
        int num = board[row][col];
        assertTrue(num >= 1 && num <= 9, "Invalid number in board");
        assertFalse(seen[num], "Duplicate in row " + row);
        seen[num] = true;
      }
    }

    // Check columns
    for (int col = 0; col < 9; col++) {
      boolean[] seen = new boolean[10];
      for (int row = 0; row < 9; row++) {
        int num = board[row][col];
        assertFalse(seen[num], "Duplicate in column " + col);
        seen[num] = true;
      }
    }

    // Check 3×3 subgrids
    for (int boxRow = 0; boxRow < 3; boxRow++) {
      for (int boxCol = 0; boxCol < 3; boxCol++) {
        boolean[] seen = new boolean[10];
        for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
          for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
            int num = board[i][j];
            assertFalse(seen[num], "Duplicate in subgrid");
            seen[num] = true;
          }
        }
      }
    }
  }
}