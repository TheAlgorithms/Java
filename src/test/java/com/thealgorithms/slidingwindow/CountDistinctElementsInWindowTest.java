package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CountDistinctElementsInWindowTest {

    @Test
    public void testBasicCase() {
        assertArrayEquals(new int[] {3, 2, 2}, CountDistinctElementsInWindow.countDistinct(new int[] {1, 2, 3, 2, 3}, 3));
    }

    @Test
    public void testAllSame() {
        assertArrayEquals(new int[] {1, 1, 1}, CountDistinctElementsInWindow.countDistinct(new int[] {2, 2, 2, 2}, 2));
    }

    @Test
    public void testAllDistinct() {
        assertArrayEquals(new int[] {3, 3}, CountDistinctElementsInWindow.countDistinct(new int[] {1, 2, 3, 4}, 3));
    }

    @Test
    public void testWindowSizeEqualsArray() {
        assertArrayEquals(new int[] {4}, CountDistinctElementsInWindow.countDistinct(new int[] {1, 2, 3, 4}, 4));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> CountDistinctElementsInWindow.countDistinct(new int[] {}, 2));
    }
}
