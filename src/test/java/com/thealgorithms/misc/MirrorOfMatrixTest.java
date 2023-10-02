package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MirrorOfMatrixTest {

    @Test
    public void testMirrorMatrix() {
        // Test case 1: Regular matrix
        int[][] originalMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] expectedMirroredMatrix = {
                {3, 2, 1},
                {6, 5, 4},
                {9, 8, 7}
        };

        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirroredMatrix, mirroredMatrix);

        // Test case 2: Empty matrix
        int[][] emptyMatrix = new int[0][0];
        int[][] mirroredEmptyMatrix = MirrorOfMatrix.mirrorMatrix(emptyMatrix);
        assertArrayEquals(emptyMatrix, mirroredEmptyMatrix);

        // Test case 3: Single-row matrix
        int[][] singleRowMatrix = {{1, 2, 3}};
        int[][] expectedSingleRowMirroredMatrix = {{3, 2, 1}};
        int[][] mirroredSingleRowMatrix = MirrorOfMatrix.mirrorMatrix(singleRowMatrix);
        assertArrayEquals(expectedSingleRowMirroredMatrix, mirroredSingleRowMatrix);

        // Test case 4: Single-column matrix
        int[][] singleColumnMatrix = {{1}, {2}, {3}};
        int[][] expectedSingleColumnMirroredMatrix = {{1}, {2}, {3}};
        int[][] mirroredSingleColumnMatrix = MirrorOfMatrix.mirrorMatrix(singleColumnMatrix);
        assertArrayEquals(expectedSingleColumnMirroredMatrix, mirroredSingleColumnMatrix);
    }
}
