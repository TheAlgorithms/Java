package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UniquePathsTests {

    @Test
    public void testUniquePaths_3x3() {
        int result = UniquePaths.uniquePaths(3, 3);
        assertEquals(6, result);
    }

    @Test
    public void testUniquePaths_1x1() {
        int result = UniquePaths.uniquePaths(1, 1);
        assertEquals(1, result);
    }

    @Test
    public void testUniquePaths_3x7() {
        int result = UniquePaths.uniquePaths(3, 7);
        assertEquals(28, result);
    }

    @Test
    public void testUniquePaths_7x3() {
        int result = UniquePaths.uniquePaths(7, 3);
        assertEquals(28, result);
    }

    @Test
    public void testUniquePaths2_3x3() {
        int result = UniquePaths.uniquePaths2(3, 3);
        assertEquals(6, result);
    }

    @Test
    public void testUniquePaths2_1x1() {
        int result = UniquePaths.uniquePaths2(1, 1);
        assertEquals(1, result);
    }

    @Test
    public void testUniquePaths2_3x7() {
        int result = UniquePaths.uniquePaths2(3, 7);
        assertEquals(28, result);
    }

    @Test
    public void testUniquePaths2_5x5() {
        int result = UniquePaths.uniquePaths2(5, 5);
        assertEquals(70, result);
    }

    @Test
    public void testUniquePaths2_7x3() {
        int result = UniquePaths.uniquePaths2(7, 3);
        assertEquals(28, result);
    }
}
