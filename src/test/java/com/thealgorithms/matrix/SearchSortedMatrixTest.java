package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

class SearchSortedMatrixTest {

    @Test
    void nullMatrixReturnsFalse() {
        final int[][] matrix = null;
        assertFalse(SearchSortedMatrix.search(matrix, 42));
    }

    @Test
    void emptyMatrixReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new int[0][], 42));
    }

    @Test
    void emptyFirstRowReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new int[][] {{}}, 42));
    }

    @Test
    void nullFirstRowReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new int[][] {null}, 42));
    }

    @Test
    void findsExistingTargetInTypicalMatrix() {
        final int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 5));
        assertTrue(SearchSortedMatrix.search(matrix, 30));
        assertTrue(SearchSortedMatrix.search(matrix, 1));
    }

    @Test
    void intSearchCoversAllComparisonBranches() {
        final int[][] matrix = {
            {1, 4},
            {2, 5},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 2));
    }

    @Test
    void genericSearchFindsExistingTarget() {
        final Integer[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 16, Comparator.naturalOrder()));
        assertFalse(SearchSortedMatrix.search(matrix, 20, Comparator.naturalOrder()));
    }

    @Test
    void genericTargetSmallerThanAllValuesReturnsFalse() {
        final Integer[][] matrix = {
            {1, 4},
            {2, 5},
        };

        assertFalse(SearchSortedMatrix.search(matrix, 0, Comparator.naturalOrder()));
    }

    @Test
    void genericSearchCoversAllComparisonBranches() {
        final Integer[][] matrix = {
            {1, 4},
            {2, 5},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 2, Comparator.naturalOrder()));
    }

    @Test
    void genericNullMatrixReturnsFalse() {
        assertFalse(SearchSortedMatrix.search((Integer[][]) null, 42, Comparator.naturalOrder()));
    }

    @Test
    void genericEmptyMatrixReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new Integer[0][], 42, Comparator.naturalOrder()));
    }

    @Test
    void genericEmptyFirstRowReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new Integer[][] {{}}, 42, Comparator.naturalOrder()));
    }

    @Test
    void genericNullFirstRowReturnsFalse() {
        assertFalse(SearchSortedMatrix.search(new Integer[][] {null}, 42, Comparator.naturalOrder()));
    }

    @Test
    void genericRejectsJaggedMatrix() {
        final Integer[][] jagged = {
            {1, 2, 3},
            {4, 5},
        };

        assertThrows(IllegalArgumentException.class, () -> SearchSortedMatrix.search(jagged, 5, Comparator.naturalOrder()));
    }

    @Test
    void genericRejectsNullRow() {
        final Integer[][] hasNullRow = {
            {1, 2, 3},
            null,
        };

        assertThrows(IllegalArgumentException.class, () -> SearchSortedMatrix.search(hasNullRow, 2, Comparator.naturalOrder()));
    }

    @Test
    void genericNullComparatorThrows() {
        final Integer[][] matrix = {
            {1, 2, 3},
        };

        assertThrows(NullPointerException.class, () -> SearchSortedMatrix.search(matrix, 2, null));
    }

    @Test
    void returnsFalseWhenTargetDoesNotExist() {
        final int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };

        assertFalse(SearchSortedMatrix.search(matrix, 0));
        assertFalse(SearchSortedMatrix.search(matrix, 10));
    }

    @Test
    void worksForSingleRowMatrix() {
        final int[][] matrix = {
            {1, 3, 5, 7, 9},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 7));
        assertFalse(SearchSortedMatrix.search(matrix, 8));
    }

    @Test
    void worksForSingleColumnMatrix() {
        final int[][] matrix = {
            {1},
            {3},
            {5},
            {7},
        };

        assertTrue(SearchSortedMatrix.search(matrix, 1));
        assertTrue(SearchSortedMatrix.search(matrix, 7));
        assertFalse(SearchSortedMatrix.search(matrix, 2));
    }

    @Test
    void handlesNegativesAndDuplicates() {
        final int[][] matrix = {
            {-5, -3, -3, 0},
            {-4, -2, 1, 2},
            {-1, 3, 3, 4},
        };

        assertTrue(SearchSortedMatrix.search(matrix, -3));
        assertTrue(SearchSortedMatrix.search(matrix, 3));
        assertFalse(SearchSortedMatrix.search(matrix, 6));
    }

    @Test
    void rejectsJaggedMatrix() {
        final int[][] jagged = {
            {1, 2, 3},
            {4, 5},
        };

        assertThrows(IllegalArgumentException.class, () -> SearchSortedMatrix.search(jagged, 5));
    }

    @Test
    void rejectsNullRow() {
        final int[][] hasNullRow = {
            {1, 2, 3},
            null,
        };

        assertThrows(IllegalArgumentException.class, () -> SearchSortedMatrix.search(hasNullRow, 2));
    }
}
