package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SegmentTreeTest {

    private static SegmentTree treeOf(int... values) {
        return new SegmentTree(values.length, values);
    }

    @ParameterizedTest
    @CsvSource({"0, 4, 15", "0, 0, 1", "4, 4, 5", "1, 3, 9", "2, 4, 12"})
    void testRangeSums(int start, int end, int expected) {
        assertEquals(expected, treeOf(1, 2, 3, 4, 5).getSum(start, end));
    }

    @Test
    void testSingleElementTree() {
        SegmentTree tree = treeOf(42);
        assertEquals(42, tree.getSum(0, 0));
        tree.update(0, 7);
        assertEquals(7, tree.getSum(0, 0));
    }

    @Test
    void testUpdateIsReflectedInSubsequentQueries() {
        SegmentTree tree = treeOf(1, 2, 3, 4, 5);
        tree.update(2, 10);
        assertEquals(22, tree.getSum(0, 4));
        assertEquals(16, tree.getSum(1, 3));
        tree.update(0, -1);
        assertEquals(20, tree.getSum(0, 4));
    }

    @Test
    void testNegativeValues() {
        SegmentTree tree = treeOf(-5, 3, -2, 8);
        assertEquals(4, tree.getSum(0, 3));
        assertEquals(-4, tree.getSum(0, 2));
    }

    /**
     * index == n is past the last element, so it must be rejected by the guard instead of reaching
     * the backing array and throwing {@link ArrayIndexOutOfBoundsException}.
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 100, -1})
    void testUpdateOutOfRangeIndexIsIgnored(int index) {
        SegmentTree tree = treeOf(1, 2, 3, 4, 5);
        assertDoesNotThrow(() -> tree.update(index, 99));
        assertEquals(15, tree.getSum(0, 4), "out of range update must not modify the tree");
    }

    @ParameterizedTest
    @CsvSource({"0, 5", "0, 6", "3, 2", "-1, 3", "5, 5"})
    void testOutOfRangeQueriesReturnZero(int start, int end) {
        assertEquals(0, treeOf(1, 2, 3, 4, 5).getSum(start, end));
    }

    @Test
    void testConstructorRejectsInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new SegmentTree(0, new int[] {1, 2, 3}));
        assertThrows(IllegalArgumentException.class, () -> new SegmentTree(-1, new int[] {1, 2, 3}));
        assertThrows(IllegalArgumentException.class, () -> new SegmentTree(4, new int[] {1, 2, 3}));
    }

    @Test
    void testConstructorRejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SegmentTree(3, null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17})
    void testMatchesBruteForceForVariousSizes(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = i * 3 - 4;
        }
        SegmentTree tree = new SegmentTree(size, values.clone());

        for (int start = 0; start < size; start++) {
            int expected = 0;
            for (int end = start; end < size; end++) {
                expected += values[end];
                assertEquals(expected, tree.getSum(start, end), "sum of [" + start + ", " + end + "] with size " + size);
            }
        }
    }
}
