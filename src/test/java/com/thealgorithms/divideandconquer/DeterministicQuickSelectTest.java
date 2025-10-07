package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeterministicQuickSelectTest {

    @Test
    void testSelectKthSmallestBasic() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 1)); // smallest
        assertEquals(4, DeterministicQuickSelect.select(arr.clone(), 2));
        assertEquals(7, DeterministicQuickSelect.select(arr.clone(), 3));
        assertEquals(10, DeterministicQuickSelect.select(arr.clone(), 4));
        assertEquals(15, DeterministicQuickSelect.select(arr.clone(), 5));
        assertEquals(20, DeterministicQuickSelect.select(arr.clone(), 6)); // largest
    }

    @Test
    void testSelectSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicQuickSelect.select(arr, 1));
    }

    @Test
    void testSelectWithDuplicates() {
        int[] arr = {5, 3, 8, 5, 2, 3};
        assertEquals(2, DeterministicQuickSelect.select(arr.clone(), 1));
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 2));
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 3));
        assertEquals(5, DeterministicQuickSelect.select(arr.clone(), 4));
        assertEquals(5, DeterministicQuickSelect.select(arr.clone(), 5));
        assertEquals(8, DeterministicQuickSelect.select(arr.clone(), 6));
    }

    @Test
    void testSelectEmptyArray() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(arr, 1));
    }

    @Test
    void testSelectInvalidK() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(arr, 0)); // k < 1
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(arr, 4)); // k > length
    }
}
