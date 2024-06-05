package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UniquePathsTests {

    @Test
    public void testUniquePaths3x3() {
        assertEquals(6, UniquePaths.uniquePaths(3, 3));
    }

    @Test
    public void testUniquePaths1x1() {
        assertEquals(1, UniquePaths.uniquePaths(1, 1));
    }

    @Test
    public void testUniquePaths3x7() {
        assertEquals(28, UniquePaths.uniquePaths(3, 7));
    }

    @Test
    public void testUniquePaths7x3() {
        assertEquals(28, UniquePaths.uniquePaths(7, 3));
    }

    @Test
    public void testUniquePaths100x100() {
        assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths(100, 100));
    }

    @Test
    public void testUniquePathsII3x3() {
        assertEquals(6, UniquePaths.uniquePaths2(3, 3));
    }

    @Test
    public void testUniquePathsII1x1() {
        assertEquals(1, UniquePaths.uniquePaths2(1, 1));
    }

    @Test
    public void testUniquePathsII3x7() {
        assertEquals(28, UniquePaths.uniquePaths2(3, 7));
    }

    @Test
    public void testUniquePathsII7x3() {
        assertEquals(28, UniquePaths.uniquePaths2(7, 3));
    }

    @Test
    public void testUniquePathsII100x100() {
        assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths2(100, 100));
    }
}
