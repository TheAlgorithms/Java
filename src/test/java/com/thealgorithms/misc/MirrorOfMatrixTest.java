package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class MirrorOfMatrixTest {

    @Test
    public void testMirrorWithEmptyMatrix() {
        int[][] arr = new int[0][0];
        MirrorOfMatrix.Mirror(arr);
        // Since the matrix is empty, there's nothing to mirror.
        // So, the expected result is also an empty matrix.
        int[][] expected = new int[0][0];
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMirrorWithSingleRowMatrix() {
        int[][] arr = {{1, 2, 3, 4}};
        MirrorOfMatrix.Mirror(arr);
        // The expected result is the reverse of the input row.
        int[][] expected = {{4, 3, 2, 1}};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMirrorWithMultipleRowMatrix() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        MirrorOfMatrix.Mirror(arr);
        // The expected result is each row reversed.
        int[][] expected = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMirrorWithOddLengthRows() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6, 7}, {8, 9}};
        MirrorOfMatrix.Mirror(arr);
        // The expected result is each row reversed.
        int[][] expected = {{3, 2, 1}, {7, 6, 5, 4}, {9, 8}};
        assertArrayEquals(expected, arr);
    }
}