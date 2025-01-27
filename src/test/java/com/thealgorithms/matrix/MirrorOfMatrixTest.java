package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MirrorOfMatrixTest {

    @Test
    void testMirrorMatrixRegularMatrix() {
        double[][] originalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMirrorMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixEmptyMatrix() {
        double[][] originalMatrix = {};
        Exception e = assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(originalMatrix));
        assertEquals("The input matrix cannot be empty", e.getMessage());
    }

    @Test
    void testMirrorMatrixSingleElementMatrix() {
        double[][] originalMatrix = {{42}};
        double[][] expectedMirrorMatrix = {{42}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixMultipleRowsOneColumnMatrix() {
        double[][] originalMatrix = {{1}, {2}, {3}, {4}};
        double[][] expectedMirrorMatrix = {{1}, {2}, {3}, {4}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixNullInput() {
        double[][] originalMatrix = null;
        Exception e = assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(originalMatrix));
        assertEquals("The input matrix cannot be null", e.getMessage());
    }

    @Test
    void testMirrorMatrixThrows() {
        assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(new double[][] {{1}, {2, 3}}));
    }
}
