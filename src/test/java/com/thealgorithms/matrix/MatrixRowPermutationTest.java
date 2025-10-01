package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MatrixRowPermutationTest {

    @Test
    void testPermute2x2Matrix() {
        int[][] matrix = {{1, 2}, {3, 4}};

        List<int[][]> permutations = MatrixRowPermutation.permuteRows(matrix);
        assertEquals(2, permutations.size(), "Expected 2 permutations for 2x2 matrix");

        // Check that both permutations are present
        boolean foundOriginal = false;
        boolean foundSwapped = false;

        for (int[][] perm : permutations) {
            if (Arrays.deepEquals(perm, matrix)) {
                foundOriginal = true;
            } else if (Arrays.deepEquals(perm, new int[][] {{3, 4}, {1, 2}})) {
                foundSwapped = true;
            }
        }

        assertTrue(foundOriginal, "Original matrix permutation missing");
        assertTrue(foundSwapped, "Swapped matrix permutation missing");
    }

    @Test
    void testPermute3x1Matrix() {
        int[][] matrix = {{1}, {2}, {3}};

        List<int[][]> permutations = MatrixRowPermutation.permuteRows(matrix);
        assertEquals(6, permutations.size(), "Expected 6 permutations for 3x1 matrix");
    }

    @Test
    void testEmptyMatrixThrowsException() {
        int[][] empty = new int[0][0];
        assertThrows(IllegalArgumentException.class, () -> MatrixRowPermutation.permuteRows(empty));
    }

    @Test
    void testNullMatrixThrowsException() {
        assertThrows(NullPointerException.class, () -> MatrixRowPermutation.permuteRows(null));
    }

    @Test
    void testSingleRowMatrix() {
        int[][] matrix = {{42, 99}};

        List<int[][]> permutations = MatrixRowPermutation.permuteRows(matrix);
        assertEquals(1, permutations.size(), "Expected 1 permutation for single-row matrix");
        assertTrue(Arrays.deepEquals(permutations.get(0), matrix), "Single-row matrix should remain unchanged");
    }
}
