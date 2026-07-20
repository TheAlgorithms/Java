package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LibrarySortTest {

    @Test
    public void testBasicSort() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, LibrarySort.sort(new int[] {5, 3, 1, 4, 2}));
    }

    @Test
    public void testAlreadySorted() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, LibrarySort.sort(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    public void testReverseSorted() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, LibrarySort.sort(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    public void testDuplicates() {
        assertArrayEquals(new int[] {1, 2, 2, 3, 3}, LibrarySort.sort(new int[] {3, 2, 1, 3, 2}));
    }

    @Test
    public void testSingleElement() {
        assertArrayEquals(new int[] {1}, LibrarySort.sort(new int[] {1}));
    }

    @Test
    public void testEmptyArray() {
        assertArrayEquals(new int[] {}, LibrarySort.sort(new int[] {}));
    }

    @Test
    public void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> LibrarySort.sort(null));
    }

    // --- Added to cover branches the tests above never reach ---

    @Test
    public void testShiftLeftWhenRightSideIsFull() {
        // Right side of the target slot is completely occupied, forcing a left shift.
        assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6}, LibrarySort.sort(new int[] {0, 1, 2, 6, 4, 5, 3}));
    }

    @Test
    public void testTieBreakPrefersRightWhenDistancesEqual() {
        // A gap exists on both sides at equal distance; algorithm should favor the right shift.
        assertArrayEquals(new int[] {0, 1, 2, 3}, LibrarySort.sort(new int[] {0, 1, 3, 2}));
    }

    @Test
    public void testRightSearchRunsOffTheEnd() {
        // No gap anywhere to the right of the target slot, all the way to the array's end.
        assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7}, LibrarySort.sort(new int[] {0, 1, 2, 3, 4, 5, 7, 6}));
    }

    @Test
    public void testInsertAtEndWithNoTrailingGap() {
        // A new global maximum arrives with no trailing gap left, forcing insertAtEnd().
        assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7}, LibrarySort.sort(new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
    }
}
