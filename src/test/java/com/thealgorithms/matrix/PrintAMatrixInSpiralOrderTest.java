package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class PrintAMatrixInSpiralOrderTest {

    private final PrintAMatrixInSpiralOrder spiralPrinter = new PrintAMatrixInSpiralOrder();

    @Test
    void testSquareMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertEquals(expected, spiralPrinter.print(matrix, 3, 3));
    }

    @Test
    void testRectangularMatrixMoreRows() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8);
        assertEquals(expected, spiralPrinter.print(matrix, 4, 3));
    }

    @Test
    void testRectangularMatrixMoreCols() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        assertEquals(expected, spiralPrinter.print(matrix, 3, 4));
    }

    @Test
    void testSingleRow() {
        int[][] matrix = {{1, 2, 3, 4}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertEquals(expected, spiralPrinter.print(matrix, 1, 4));
    }

    @Test
    void testSingleColumn() {
        int[][] matrix = {{1}, {2}, {3}};
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, spiralPrinter.print(matrix, 3, 1));
    }

    @Test
    void testEmptyMatrix() {
        int[][] matrix = new int[0][0];
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, spiralPrinter.print(matrix, 0, 0));
    }

    @Test
    void testOneElementMatrix() {
        int[][] matrix = {{42}};
        List<Integer> expected = Collections.singletonList(42);
        assertEquals(expected, spiralPrinter.print(matrix, 1, 1));
    }

    @Test
    void testMatrixWithNegativeNumbers() {
        int[][] matrix = {{-1, -2}, {-3, -4}};
        List<Integer> expected = Arrays.asList(-1, -2, -4, -3);
        assertEquals(expected, spiralPrinter.print(matrix, 2, 2));
    }

    @Test
    void testLargeSquareMatrix() {
        int[][] matrix = {{3, 4, 5, 6, 7}, {8, 9, 10, 11, 12}, {14, 15, 16, 17, 18}, {23, 24, 25, 26, 27}, {30, 31, 32, 33, 34}};
        List<Integer> expected = Arrays.asList(3, 4, 5, 6, 7, 12, 18, 27, 34, 33, 32, 31, 30, 23, 14, 8, 9, 10, 11, 17, 26, 25, 24, 15, 16);
        assertEquals(expected, spiralPrinter.print(matrix, 5, 5));
    }

    @Test
    void testSingleRowWithTwoElements() {
        int[][] matrix = {{2, 2}};
        List<Integer> expected = Arrays.asList(2, 2);
        assertEquals(expected, spiralPrinter.print(matrix, 1, 2));
    }
}
