package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class StrassenMatrixMultiplicationTest {

    StrassenMatrixMultiplication smm = new StrassenMatrixMultiplication();

    // Strassen Matrix Multiplication can only be allplied to matrices of size 2^n
    // and has to be a Square Matrix

    @Test
    public void strassenMatrixMultiplicationTest2x2() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] expResult = {{19, 22}, {43, 50}};
        int[][] actResult = smm.multiply(a, b);
        assertArrayEquals(expResult, actResult);
    }

    @Test
    void strassenMatrixMultiplicationTest4x4() {
        int[][] a = {{1, 2, 5, 4}, {9, 3, 0, 6}, {4, 6, 3, 1}, {0, 2, 0, 6}};
        int[][] b = {{1, 0, 4, 1}, {1, 2, 0, 2}, {0, 3, 1, 3}, {1, 8, 1, 2}};
        int[][] expResult = {{7, 51, 13, 28}, {18, 54, 42, 27}, {11, 29, 20, 27}, {8, 52, 6, 16}};
        int[][] actResult = smm.multiply(a, b);
        assertArrayEquals(expResult, actResult);
    }

    @Test

    void strassenMatrixMultiplicationTestNegetiveNumber4x4() {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] b = {{1, -2, -3, 4}, {4, -3, -2, 1}, {5, -6, -7, 8}, {8, -7, -6, -5}};
        int[][] expResult = {{56, -54, -52, 10}, {128, -126, -124, 42}, {200, -198, -196, 74}, {272, -270, -268, 106}};
        int[][] actResult = smm.multiply(a, b);
        assertArrayEquals(expResult, actResult);
    }
}
