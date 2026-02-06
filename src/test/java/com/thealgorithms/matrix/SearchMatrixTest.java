package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SearchMatrixTest {

    @Test
    void nullMatrixReturnsFalse() {
        assertFalse(SearchMatrix.contains(null, 1));
    }

    @Test
    void emptyMatrixReturnsFalse() {
        assertFalse(SearchMatrix.contains(new Integer[0][], 1));
    }

    @Test
    void findsElementInRectangularMatrix() {
        final Integer[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
        };

        assertTrue(SearchMatrix.contains(matrix, 5));
        assertFalse(SearchMatrix.contains(matrix, 7));
    }

    @Test
    void supportsNullTargetAndNullElements() {
        final String[][] matrix = {
            {"a", null},
            {"b", "c"},
        };

        assertTrue(SearchMatrix.contains(matrix, null));
        assertTrue(SearchMatrix.contains(matrix, "c"));
        assertFalse(SearchMatrix.contains(matrix, "d"));
    }

    @Test
    void supportsJaggedMatricesAndNullRows() {
        final Integer[][] matrix = {
            {1, 2, 3},
            null,
            {},
            {4},
        };

        assertTrue(SearchMatrix.contains(matrix, 4));
        assertFalse(SearchMatrix.contains(matrix, 5));
    }
}
