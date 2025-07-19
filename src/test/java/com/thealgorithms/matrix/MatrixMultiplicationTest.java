package com.thealgorithms.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MatrixMultiplicationTest {

    private static final double EPSILON = 1e-9; // for floating point comparison


    @Test
    void testMultiply2by2(){
        double[][] matrixA = {{1.0,2.0},{3.0,4.0}};
        double[][] matrixB = {{5.0,6.0},{7.0,8.0}};
        double[][] expected = {{19.0, 22.0}, {43.0, 50.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);   // Because assertEquals can fails due to floating point precision issues, Therfore use assertMatrixEquals
    }

    @Test
    void testMultiply3by2and1(){
        double[][] matrixA = {{1.0,2.0},{3.0,4.0},{5.0,6.0}};
        double[][] matrixB = {{7.0},{8.0}};
        double[][] expected = {{23.0}, {53.0}, {83.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);   // Because assertEquals can fails due to floating point precision issues, Therfore use assertMatrixEquals
    }


    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length, "Column count mismatch at row " + i);   // Check if the number of columns in each row matches
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j], EPSILON,
                        "Mismatch at (" + i + "," + j + ")");
            }
        }
    }

}
