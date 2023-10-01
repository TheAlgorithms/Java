package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MirrorOfMatrixTest {

    @Test
    public void testMirrorMatrix() {
        int[][] originalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] expectedMirroredMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};

        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);

        // Assert that the mirrored matrix matches the expected result
        assertArrayEquals(expectedMirroredMatrix, mirroredMatrix);
    }
}