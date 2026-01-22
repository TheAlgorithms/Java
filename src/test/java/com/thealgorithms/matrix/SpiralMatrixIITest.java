package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SpiralMatrixIITest {

    private final SpiralMatrixII spiral = new SpiralMatrixII();

    @Test
    void testNEquals1() {
        int[][] expected = {{1}};
        assertArrayEquals(expected, spiral.generateMatrix(1));
    }

    @Test
    void testNEquals3() {
        int[][] expected = {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
        };
        assertArrayEquals(expected, spiral.generateMatrix(3));
    }

    @Test
    void testNEquals4() {
        int[][] expected = {
            {1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 16, 15, 6},
            {10, 9, 8, 7}
        };
        assertArrayEquals(expected, spiral.generateMatrix(4));
    }
}
