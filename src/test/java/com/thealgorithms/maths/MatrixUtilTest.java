package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class MatrixUtilTest {

    @Test
    void add() {
        final BigDecimal[][] matrix1 = {
            {new BigDecimal(3), BigDecimal.TWO},
            {BigDecimal.ZERO, BigDecimal.ONE},
        };

        final BigDecimal[][] matrix2 = {
            {BigDecimal.ONE, new BigDecimal(3)},
            {BigDecimal.TWO, BigDecimal.ZERO},
        };

        final BigDecimal[][] actual = MatrixUtil.add(matrix1, matrix2).orElseThrow(() -> new AssertionError("Could not compute matrix!"));

        final BigDecimal[][] expected = {
            {new BigDecimal(4), new BigDecimal(5)},
            {BigDecimal.TWO, BigDecimal.ONE},
        };

        assertTrue(Objects.deepEquals(actual, expected));
    }
    @Test
    void subtract() {
        final BigDecimal[][] matrix1 = {
            {BigDecimal.ONE, new BigDecimal(4)},
            {new BigDecimal(5), new BigDecimal(6)},
        };

        final BigDecimal[][] matrix2 = {
            {BigDecimal.TWO, BigDecimal.ZERO},
            {new BigDecimal(-2), new BigDecimal(-3)},
        };

        final BigDecimal[][] actual = MatrixUtil.subtract(matrix1, matrix2).orElseThrow(() -> new AssertionError("Could not compute matrix!"));

        final BigDecimal[][] expected = {
            {new BigDecimal(-1), new BigDecimal(4)},
            {new BigDecimal(7), new BigDecimal(9)},
        };

        assertTrue(Objects.deepEquals(actual, expected));
    }

    @Test
    void multiply() {

        final BigDecimal[][] matrix1 = {
            {BigDecimal.ONE, BigDecimal.TWO, new BigDecimal(3)},
            {new BigDecimal(4), new BigDecimal(5), new BigDecimal(6)},
            {new BigDecimal(7), new BigDecimal(8), new BigDecimal(9)},
        };

        final BigDecimal[][] matrix2 = {
            {BigDecimal.ONE, BigDecimal.TWO},
            {new BigDecimal(3), new BigDecimal(4)},
            {new BigDecimal(5), new BigDecimal(6)},
        };

        final BigDecimal[][] actual = MatrixUtil.multiply(matrix1, matrix2).orElseThrow(() -> new AssertionError("Could not compute matrix!"));

        final BigDecimal[][] expected = {
            {new BigDecimal(22), new BigDecimal(28)},
            {new BigDecimal(49), new BigDecimal(64)},
            {new BigDecimal(76), new BigDecimal(100)},
        };

        assertTrue(Objects.deepEquals(actual, expected));
    }
}
