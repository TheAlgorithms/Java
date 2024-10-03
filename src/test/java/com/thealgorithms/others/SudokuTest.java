package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


public class SudokuTest {

    /**
     * This is a helper method to create test cases for Sudoku boards.
     * It generates a stream of arguments to be used in parameterized tests.
     */
    static Stream<Arguments> provideSudokuBoards() {
        return Stream.of(
            Arguments.of(
                new int[][] {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}}, true),

            Arguments.of(
                new int[][] {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 7} // Invalid board (duplicate '7' in the last row)
                },
                false));
    }

    /**
     * Parameterized test to check the solution of a Sudoku board.
     *
     * @param board          The initial Sudoku board to solve.
     * @param expectedResult Expected result - true if solvable, false otherwise.
     */
    @ParameterizedTest
    @MethodSource("provideSudokuBoards")
    void testSolveSudoku(int[][] board, boolean expectedResult) {
        int n = board.length;
        boolean result = Sudoku.solveSudoku(board, n);
        assertEquals(expectedResult, result);
    }

    /**
     * Parameterized test to check if the number can be safely placed at a given
     * cell.
     *
     * @param board          The Sudoku board to check.
     * @param row            The row index of the cell.
     * @param col            The column index of the cell.
     * @param num            The number to place.
     * @param expectedResult True if the placement is valid, false otherwise.
     */
    @ParameterizedTest
    @CsvSource({"'5,3,0,0,7,0,0,0,0;6,0,0,1,9,5,0,0,0;0,9,8,0,0,0,0,6,0;8,0,0,0,6,0,0,0,3;4,0,0,8,0,3,0,0,1;7,0,0,0,2,0,0,0,6;0,6,0,0,0,0,2,8,0;0,0,0,4,1,9,0,0,5;0,0,0,0,8,0,0,7,9', 0, 2, 4, false",
    "'5,3,0,0,7,0,0,0,0;6,0,0,1,9,5,0,0,0;0,9,8,0,0,0,0,6,0;8,0,0,0,6,0,0,0,3;4,0,0,8,0,3,0,0,1;7,0,0,0,2,0,0,0,6;0,6,0,0,0,0,2,8,0;0,0,0,4,1,9,0,0,5;0,0,0,0,8,0,0,7,9', 0, 2, 9, true"})
    void
    testIsSafe(String boardString, int row, int col, int num, boolean expectedResult) {
        int[][] board = parseBoard(boardString);
        assertEquals(expectedResult, Sudoku.isSafe(board, row, col, num));
    }

    /**
     * Helper method to parse a board from a string.
     * The board string is expected to have semicolon-separated rows with
     * comma-separated values.
     *
     * @param boardString The board represented as a string.
     * @return The parsed 2D int array representing the Sudoku board.
     */
    private int[][] parseBoard(String boardString) {
        String[] rows = boardString.split(";");
        int[][] board = new int[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            for (int j = 0; j < cols.length; j++) {
                board[i][j] = Integer.parseInt(cols[j]);
            }
        }
        return board;
    }
}
