package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MirrorOfMatrixTest {

    @Test
    void testMirrorMatrixRegularMatrix() {
        int[][] originalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] expectedMirrorMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixEmptyMatrix() {
        int[][] originalMatrix = {};
        int[][] expectedMirrorMatrix = {};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixSingleElementMatrix() {
        int[][] originalMatrix = {{42}};
        int[][] expectedMirrorMatrix = {{42}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixMultipleRowsOneColumnMatrix() {
        int[][] originalMatrix = {{1}, {2}, {3}, {4}};
        int[][] expectedMirrorMatrix = {{1}, {2}, {3}, {4}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixNullInput() {
        int[][] originalMatrix = null;
        assertNull(MirrorOfMatrix.mirrorMatrix(originalMatrix));
    }

    @Test
    void testMirrotMarixThrows() {
        assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(new int[][] {{1}, {2, 3}}));
    }
}
