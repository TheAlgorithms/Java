package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FindMinTest {

    @Test
    public void testFindMinValue() {
        assertEquals(1, FindMin.findMin(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(1, FindMin.findMin(new int[] {5, 5, 5, 5, 5}));
        assertEquals(0, FindMin.findMin(new int[] {0, 192, 384, 576}));
        assertEquals(-1, FindMin.findMin(new int[] {-1, 2, 5, 10}));
        assertEquals(-10, FindMin.findMin(new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}));
        assertThrows(IllegalArgumentException.class, () -> FindMin.findMin(new int[] {}));
    }
}
