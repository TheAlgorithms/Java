package com.thealgorithms.sorts;
// author: Vraj Prajapati @Rosander0

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
}
