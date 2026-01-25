package com.thealgorithms.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrefixSum2DTest {

    @Test
    @DisplayName("Test basic 3x3 square matrix")
    void testStandardSquare() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        // Sum of top-left 2x2: {1,2, 4,5} -> 12
        assertEquals(12L, ps.sumRegion(0, 0, 1, 1));
        // Sum of bottom-right 2x2: {5,6, 8,9} -> 28
        assertEquals(28L, ps.sumRegion(1, 1, 2, 2));
        // Full matrix -> 45
        assertEquals(45L, ps.sumRegion(0, 0, 2, 2));
    }

    @Test
    @DisplayName("Test rectangular matrix (more cols than rows)")
    void testRectangularWide() {
        int[][] matrix = {{1, 1, 1, 1}, {2, 2, 2, 2}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        // Sum of first 3 columns of both rows -> (1*3) + (2*3) = 9
        assertEquals(9L, ps.sumRegion(0, 0, 1, 2));
    }

    @Test
    @DisplayName("Test rectangular matrix (more rows than cols)")
    void testRectangularTall() {
        int[][] matrix = {{1}, {2}, {3}, {4}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        // Sum of middle two elements -> 2+3 = 5
        assertEquals(5L, ps.sumRegion(1, 0, 2, 0));
    }

    @Test
    @DisplayName("Test single element matrix")
    void testSingleElement() {
        int[][] matrix = {{100}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        assertEquals(100L, ps.sumRegion(0, 0, 0, 0));
    }

    @Test
    @DisplayName("Test large numbers for overflow (Integer -> Long)")
    void testLargeNumbers() {
        // 2 billion. Two of these sum to > MAX_INT
        int val = 2_000_000_000;
        int[][] matrix = {{val, val}, {val, val}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        // 4 * 2B = 8 Billion
        assertEquals(8_000_000_000L, ps.sumRegion(0, 0, 1, 1));
    }

    @Test
    @DisplayName("Test invalid inputs")
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new PrefixSum2D(null));
        assertThrows(IllegalArgumentException.class, () -> new PrefixSum2D(new int[][] {})); // empty
        assertThrows(IllegalArgumentException.class, () -> new PrefixSum2D(new int[][] {{}})); // empty row
    }

    @Test
    @DisplayName("Test invalid query ranges")
    void testInvalidRanges() {
        int[][] matrix = {{1, 2}, {3, 4}};
        PrefixSum2D ps = new PrefixSum2D(matrix);

        // Negative indices
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(-1, 0, 0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(0, -1, 0, 0));

        // Out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(0, 0, 2, 0)); // row2 too big
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(0, 0, 0, 2)); // col2 too big

        // Inverted ranges (start > end)
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(1, 0, 0, 0)); // row1 > row2
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRegion(0, 1, 0, 0)); // col1 > col2
    }
}
