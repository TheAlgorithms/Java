package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FindMaxTest {

    @Test
    public void testFindMax0() {
        assertEquals(10, FindMax.findMax(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testFindMax1() {
        assertEquals(7, FindMax.findMax(new int[] {6, 3, 5, 1, 7, 4, 1}));
    }

    @Test
    public void testFindMax2() {
        assertEquals(10, FindMax.findMax(new int[] {10, 0}));
    }

    @Test
    public void testFindMaxThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> FindMax.findMax(new int[] {}));
    }
}
