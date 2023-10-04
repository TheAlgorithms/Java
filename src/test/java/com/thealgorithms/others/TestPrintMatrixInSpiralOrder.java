package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TestPrintMatrixInSpiralOrder {
    @Test
    public void testOne() {
        int[][] matrix = {{3, 4, 5, 6, 7}, {8, 9, 10, 11, 12}, {14, 15, 16, 17, 18}, {23, 24, 25, 26, 27}, {30, 31, 32, 33, 34}};
        var printClass = new PrintAMatrixInSpiralOrder();
        List<Integer> res = printClass.print(matrix, matrix.length, matrix[0].length);
        List<Integer> list = List.of(3, 4, 5, 6, 7, 12, 18, 27, 34, 33, 32, 31, 30, 23, 14, 8, 9, 10, 11, 17, 26, 25, 24, 15, 16);
        assertIterableEquals(res, list);
    }

    @Test
    public void testTwo() {
        int[][] matrix = {{2, 2}};
        var printClass = new PrintAMatrixInSpiralOrder();
        List<Integer> res = printClass.print(matrix, matrix.length, matrix[0].length);
        List<Integer> list = List.of(2, 2);
        assertIterableEquals(res, list);
    }
}
