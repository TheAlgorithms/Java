package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DeterministicQuickSelectTest {

    @Test
    void testSelectKthSmallestBasic() {
        int[] arr = {7, 10, 4, 3, 20, 15};

        // Test all valid k values
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 1));
        assertEquals(4, DeterministicQuickSelect.select(arr.clone(), 2));
        assertEquals(7, DeterministicQuickSelect.select(arr.clone(), 3));
        assertEquals(10, DeterministicQuickSelect.select(arr.clone(), 4));
        assertEquals(15, DeterministicQuickSelect.select(arr.clone(), 5));
        assertEquals(20, DeterministicQuickSelect.select(arr.clone(), 6));
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicQuickSelect.select(arr.clone(), 1));
    }

    @Test
    void testInvalidK() {
        int[] arr = {1, 2, 3};
        // k = 0 and k > length should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(arr.clone(), 0));
        assertThrows(IllegalArgumentException.class, () -> DeterministicQuickSelect.select(arr.clone(), 4));
    }

    @Test
    void testUnsortedArray() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 1));
        assertEquals(4, DeterministicQuickSelect.select(arr.clone(), 2));
        assertEquals(5, DeterministicQuickSelect.select(arr.clone(), 3));
        assertEquals(7, DeterministicQuickSelect.select(arr.clone(), 4));
        assertEquals(12, DeterministicQuickSelect.select(arr.clone(), 5));
        assertEquals(19, DeterministicQuickSelect.select(arr.clone(), 6));
        assertEquals(26, DeterministicQuickSelect.select(arr.clone(), 7));
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {4, 2, 5, 2, 3, 4};
        assertEquals(2, DeterministicQuickSelect.select(arr.clone(), 1));
        assertEquals(2, DeterministicQuickSelect.select(arr.clone(), 2));
        assertEquals(3, DeterministicQuickSelect.select(arr.clone(), 3));
        assertEquals(4, DeterministicQuickSelect.select(arr.clone(), 4));
        assertEquals(4, DeterministicQuickSelect.select(arr.clone(), 5));
        assertEquals(5, DeterministicQuickSelect.select(arr.clone(), 6));
    }
}
