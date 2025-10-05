package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals; // Explicitly importing assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows; // Explicitly importing assertThrows

import org.junit.jupiter.api.Test;


public final class DeterministicQuickSelectTest {

    @Test
    public void testBasicCases() {
        assertEquals(7, QuickSelectMedianOfMedians.quickSelect(new int[] {12, 3, 5, 7, 4, 19, 26}, 3));
        assertEquals(4, QuickSelectMedianOfMedians.quickSelect(new int[] {4, 2, 5, 2, 7, 4, 3}, 4));
        assertEquals(0, QuickSelectMedianOfMedians.quickSelect(new int[] {-5, -10, 0, 5, 10}, 2));
        assertEquals(3, QuickSelectMedianOfMedians.quickSelect(new int[] {8, 3, 1}, 1));
        assertEquals(10, QuickSelectMedianOfMedians.quickSelect(new int[] {10, 20, 30, 40, 50}, 0));
        assertEquals(50, QuickSelectMedianOfMedians.quickSelect(new int[] {10, 20, 30, 40, 50}, 4));
        assertEquals(42, QuickSelectMedianOfMedians.quickSelect(new int[] {42}, 0));
    }

    @Test
    public void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> QuickSelectMedianOfMedians.quickSelect(null, 0));
        assertThrows(IllegalArgumentException.class, () -> QuickSelectMedianOfMedians.quickSelect(new int[] {1, 2, 3}, -1));
        assertThrows(IllegalArgumentException.class, () -> QuickSelectMedianOfMedians.quickSelect(new int[] {1, 2, 3}, 3));
        assertThrows(IllegalArgumentException.class, () -> QuickSelectMedianOfMedians.quickSelect(new int[] {}, 0));
    }
}
