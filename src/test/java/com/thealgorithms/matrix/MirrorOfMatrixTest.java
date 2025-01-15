package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
=======
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
import org.junit.jupiter.api.Test;

class MirrorOfMatrixTest {

    @Test
    void testMirrorMatrixRegularMatrix() {
<<<<<<< HEAD
        double[][] originalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMirrorMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
=======
        int[][] originalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] expectedMirrorMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixEmptyMatrix() {
<<<<<<< HEAD
        double[][] originalMatrix = {};
        Exception e = assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(originalMatrix));
        assertEquals("The input matrix cannot be empty", e.getMessage());
=======
        int[][] originalMatrix = {};
        int[][] expectedMirrorMatrix = {};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
    }

    @Test
    void testMirrorMatrixSingleElementMatrix() {
<<<<<<< HEAD
        double[][] originalMatrix = {{42}};
        double[][] expectedMirrorMatrix = {{42}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
=======
        int[][] originalMatrix = {{42}};
        int[][] expectedMirrorMatrix = {{42}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixMultipleRowsOneColumnMatrix() {
<<<<<<< HEAD
        double[][] originalMatrix = {{1}, {2}, {3}, {4}};
        double[][] expectedMirrorMatrix = {{1}, {2}, {3}, {4}};
        double[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
=======
        int[][] originalMatrix = {{1}, {2}, {3}, {4}};
        int[][] expectedMirrorMatrix = {{1}, {2}, {3}, {4}};
        int[][] mirroredMatrix = MirrorOfMatrix.mirrorMatrix(originalMatrix);
>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
        assertArrayEquals(expectedMirrorMatrix, mirroredMatrix);
    }

    @Test
    void testMirrorMatrixNullInput() {
<<<<<<< HEAD
        double[][] originalMatrix = null;
        Exception e = assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(originalMatrix));
        assertEquals("The input matrix cannot be null", e.getMessage());    }

    @Test
    void testMirrorMatrixThrows() {
        assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(new double[][] {{1}, {2, 3}}));
=======
        int[][] originalMatrix = null;
        assertNull(MirrorOfMatrix.mirrorMatrix(originalMatrix));
    }

    @Test
    void testMirrotMarixThrows() {
        assertThrows(IllegalArgumentException.class, () -> MirrorOfMatrix.mirrorMatrix(new int[][] {{1}, {2, 3}}));
>>>>>>> 754bf6c5f8f55b758bdee2667f6cadf4f0ab659f
    }
}
