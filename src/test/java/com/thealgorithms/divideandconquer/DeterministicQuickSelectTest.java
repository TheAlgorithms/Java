package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DeterministicQuickSelectTest {

    @Test
    void testSelectKthSmallest() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        assertEquals(3, DeterministicQuickSelect.select(arr, 1));
        assertEquals(4, DeterministicQuickSelect.select(arr, 2));
        assertEquals(7, DeterministicQuickSelect.select(arr, 3));
        assertEquals(10, DeterministicQuickSelect.select(arr, 4));
        assertEquals(15, DeterministicQuickSelect.select(arr, 5));
        assertEquals(20, DeterministicQuickSelect.select(arr, 6));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(null, 1));
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(new int[] {1, 2}, 0));
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(new int[] {1, 2}, 3));
    }
}
