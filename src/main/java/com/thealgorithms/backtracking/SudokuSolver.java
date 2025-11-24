package com.thealgorithms.backtracking;

/**
 * Sudoku Solver using Backtracking Algorithm
 *
 * This class solves a partially filled 9×9 Sudoku board by finding a valid
 * arrangement where every row, column, and 3×3 subgrid contains digits 1-9
 * exactly once.
 *
 * Algorithm References:
 * - Backtracking: https://en.wikipedia.org/wiki/Backtracking
 * - Sudoku: https://en.wikipedia.org/wiki/Sudoku
 *
 * Time Complexity: O(9^(n*n)) in worst case, where n=9
 * Space Complexity: O(n*n) for recursion stack
 *
 * @author TheAlgorithms
 */
public class SudokuSolver {

  private static final int SIZE = 9;
  private static final int SUBGRID_SIZE = 3;
  private static final int EMPTY = 0;

  /**
   * Solves the given Sudoku board using backtracking. Modifies the board
   * in-place.
   *
   * @param board 9×9 2D array representing the Sudoku board (0 = empty cell)
   * @return true if a valid solution exists, false otherwise
   */
  public static boolean solveSudoku(int[][] board) {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (board[row][col] == EMPTY) {
          for (int num = 1; num <= SIZE; num++) {
            if (isValid(board, row, col, num)) {
              board[row][col] = num;

              if (solveSudoku(board)) {
                return true;
              }

              board[row][col] = EMPTY;
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks if placing a number at a given position is valid.
   *
   * @param board the Sudoku board
   * @param row row index
   * @param col column index
   * @param num number to place (1-9)
   * @return true if placement is valid
   */
  private static boolean isValid(int[][] board, int row, int col, int num) {
    return isRowValid(board, row, num)
        && isColumnValid(board, col, num)
        && isSubgridValid(board, row, col, num);
  }

  /**
   * Checks if a number already exists in the given row.
   *
   * @param board the Sudoku board
   * @param row row index
   * @param num number to check
   * @return true if number is not in row
   */
  private static boolean isRowValid(int[][] board, int row, int num) {
    for (int col = 0; col < SIZE; col++) {
      if (board[row][col] == num) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if a number already exists in the given column.
   *
   * @param board the Sudoku board
   * @param col column index
   * @param num number to check
   * @return true if number is not in column
   */
  private static boolean isColumnValid(int[][] board, int col, int num) {
    for (int row = 0; row < SIZE; row++) {
      if (board[row][col] == num) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if a number already exists in the 3×3 subgrid.
   *
   * @param board the Sudoku board
   * @param row row index
   * @param col column index
   * @param num number to check
   * @return true if number is not in subgrid
   */
  private static boolean isSubgridValid(
      int[][] board, int row, int col, int num) {
    int subgridRow = row - row % SUBGRID_SIZE;
    int subgridCol = col - col % SUBGRID_SIZE;

    for (int i = subgridRow; i < subgridRow + SUBGRID_SIZE; i++) {
      for (int j = subgridCol; j < subgridCol + SUBGRID_SIZE; j++) {
        if (board[i][j] == num) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Prints the Sudoku board in a readable format.
   *
   * @param board the Sudoku board to print
   */
  public static void printBoard(int[][] board) {
    for (int row = 0; row < SIZE; row++) {
      if (row % SUBGRID_SIZE == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int col = 0; col < SIZE; col++) {
        if (col % SUBGRID_SIZE == 0 && col != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }

  /**
   * Main method demonstrating Sudoku solver functionality.
   *
   * @param args command line arguments (not used)
   */
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

    System.out.println("Sudoku Puzzle:");
    printBoard(board);

    if (solveSudoku(board)) {
      System.out.println("\nSolved Sudoku:");
      printBoard(board);
    } else {
      System.out.println("\nNo solution exists for this Sudoku puzzle.");
    }
  }
}