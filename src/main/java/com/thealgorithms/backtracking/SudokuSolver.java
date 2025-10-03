package main.java.com.thealgorithms.backtracking;

public class SudokuSolver {
      public static boolean sudokuSolver(int sudoku[][], int row, int col) {
            // base case
            if (row == 9) {
                  return true;
            }
            // recursion
            int nextRow = row, nextCol = col + 1;
            if (col + 1 == 9) {
                  nextRow = row + 1;
                  nextCol = 0;
            }
            if (sudoku[row][col] != 0) {
                  return sudokuSolver(sudoku, nextRow, nextCol);
            }
            for (int digit = 1; digit <= 9; digit++) {
                  if (isSafe(sudoku, row, col, digit)) {
                        sudoku[row][col] = digit;

                        if (sudokuSolver(sudoku, nextRow, nextCol)) {
                              return true;
                        }
                        sudoku[row][col] = 0;
                  }
            }

            return false;
      }

      public static boolean isSafe(int sudoku[][], int row, int col, int digit) {
            // col
            for (int i = 0; i <= 8; i++) {
                  if (sudoku[i][col] == digit) {
                        return false;
                  }
            }
            // row
            for (int j = 0; j <= 8; j++) {
                  if (sudoku[row][j] == digit) {
                        return false;
                  }
            }
            // grid
            int sr = (row / 3) * 3;
            int sc = (col / 3) * 3;
            for (int i = sr; i < sr + 3; i++) {
                  for (int j = sc; i < sr + 3; i++) {
                        if (sudoku[i][j] == digit) {
                              return false;
                        }
                  }
            }
            return true;
      }

      public static void print(int sudoku[][]) {
            for (int i = 0; i < 9; i++) {
                  for (int j = 0; j < 9; j++) {
                        System.out.print(sudoku[i][j] + " ");
                  }
                  System.out.println();
            }
      }

      public static void main(String[] args) {
            int sudoku[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                        { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                        { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                        { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                        { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                        { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                        { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
            if (sudokuSolver(sudoku, 0, 0)) {

                  print(sudoku);
            } else {
                  System.out.println("not valid");
            }
      }

}
