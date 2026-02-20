package com.thealgorithms.prefixsum;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DifferenceArrayTest {

    @Test
    void testStandardRangeUpdate() {
        int[] input = {10, 20, 30, 40, 50};
        DifferenceArray da = new DifferenceArray(input);

        da.update(1, 3, 5);

        long[] expected = {10, 25, 35, 45, 50};
        assertArrayEquals(expected, da.getResultArray());
    }

    @Test
    void testMultipleOverlappingUpdates() {
        int[] input = {10, 10, 10, 10, 10};
        DifferenceArray da = new DifferenceArray(input);

        da.update(0, 2, 10);
        da.update(2, 4, 20);

        long[] expected = {20, 20, 40, 30, 30};
        assertArrayEquals(expected, da.getResultArray());
    }

    @Test
    void testIntegerOverflowSafety() {
        int[] input = {Integer.MAX_VALUE, 100};
        DifferenceArray da = new DifferenceArray(input);

        da.update(0, 0, 100);

        long[] result = da.getResultArray();
        long expectedVal = (long) Integer.MAX_VALUE + 100;

        assertEquals(expectedVal, result[0]);
    }

    @Test
    void testFullRangeUpdate() {
        int[] input = {1, 2, 3};
        DifferenceArray da = new DifferenceArray(input);

        da.update(0, 2, 100);

        long[] expected = {101, 102, 103};
        assertArrayEquals(expected, da.getResultArray());
    }

    @Test
    void testBoundaryWriteOptimization() {
        int[] input = {5, 5};
        DifferenceArray da = new DifferenceArray(input);

        da.update(1, 1, 5);

        long[] expected = {5, 10};

        assertArrayEquals(expected, da.getResultArray());
    }

    @Test
    void testLargeMassiveUpdate() {
        int[] input = {0};
        DifferenceArray da = new DifferenceArray(input);

        int iterations = 100000;
        for (int i = 0; i < iterations; i++) {
            da.update(0, 0, 1);
        }

        assertEquals(100000L, da.getResultArray()[0]);
    }

    @Test
    void testNullInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new DifferenceArray(null));
    }

    @Test
    void testEmptyInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new DifferenceArray(new int[] {}));
    }

    @Test
    void testInvalidRangeNegativeIndex() {
        DifferenceArray da = new DifferenceArray(new int[] {1, 2, 3});
        assertThrows(IllegalArgumentException.class, () -> da.update(-1, 1, 5));
    }

    @Test
    void testInvalidRangeOutOfBounds() {
        DifferenceArray da = new DifferenceArray(new int[] {1, 2, 3});
        assertThrows(IllegalArgumentException.class, () -> da.update(0, 3, 5));
    }

    @Test
    void testInvalidRangeStartGreaterThanEnd() {
        DifferenceArray da = new DifferenceArray(new int[] {1, 2, 3});
        assertThrows(IllegalArgumentException.class, () -> da.update(2, 1, 5));
    }
}
