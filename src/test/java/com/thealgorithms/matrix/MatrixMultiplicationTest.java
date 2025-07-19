package com.thealgorithms.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MatrixMultiplicationTest {
    @Test
    void testMultiply2by2(){
        double[][] matrixA = {{1.0,2.0},{3.0,4.0}};
        double[][] matrixB = {{5.0,6.0},{7.0,8.0}};
        double[][] expected = {{19.0, 22.0}, {43.0, 50.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);   // Because assertEquals can fails due to floating point precision issues, Therfore use assertMatrixEquals
    }

}
