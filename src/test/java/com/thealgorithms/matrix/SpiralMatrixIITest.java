package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class SpiralMatrixIITest {

    // Instantiate the class to test
    SpiralMatrixII spiral = new SpiralMatrixII();

    @Test
    void testNEquals3() {
        int[][] expected = {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
        };
        int[][] actual = spiral.generateMatrix(3);

        // Compare each row
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Row " + i + " is incorrect for n=3");
        }
    }

    @Test
    void testNEquals4() {
        int[][] expected = {
            {1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 16, 15, 6},
            {10, 9, 8, 7}
        };
        int[][] actual = spiral.generateMatrix(4);

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Row " + i + " is incorrect for n=4");
        }
    }

    @Test
    void testNEquals2() {
        int[][] expected = {
            {1, 2},
            {4, 3}
        };
        int[][] actual = spiral.generateMatrix(2);

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Row " + i + " is incorrect for n=2");
        }
    }
}
