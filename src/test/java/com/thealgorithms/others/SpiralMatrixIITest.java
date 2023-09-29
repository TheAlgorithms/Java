package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SpiralMatrixIITest {

    @Test
    public void testGeneratedMatrixSize() {
        int size = 3; // Change this to test different matrix sizes
        int[][] result = SpiralMatrixII.generateMatrix(size);
        assertEquals(size, result.length);
        for (int i = 0; i < size; i++) {
            assertEquals(size, result[i].length);
        }
    }

    @Test
    public void testGeneratedMatrixCorrectness() {
        int[][] expectedMatrix = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};

        int size = 3;
        int[][] result = SpiralMatrixII.generateMatrix(size);

        assertArrayEquals(expectedMatrix, result);
    }
}
