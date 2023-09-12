package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.UniquePaths;
import org.junit.jupiter.api.Test;

public class UniquePathsTests {

    @Test
    public void testUniquePaths() {
        // Test case 1: A 1x1 grid should have 1 unique path (start and finish are the same).
        assertEquals(1, UniquePaths.uniquePaths(1, 1));

        // Test case 2: A 3x3 grid should have 6 unique paths.
        assertEquals(6, UniquePaths.uniquePaths(3, 3));

        // Test case 3: A 7x3 grid should have 28 unique paths.
        assertEquals(28, UniquePaths.uniquePaths(7, 3));

        // Test case 4: A 3x7 grid should have 28 unique paths.
        assertEquals(28, UniquePaths.uniquePaths(3, 7));
    }

    @Test
    public void testUniquePaths2() {
        // Test case 1: A 1x1 grid should have 1 unique path (start and finish are the same).
        assertEquals(1, UniquePaths.uniquePaths2(1, 1));

        // Test case 2: A 3x3 grid should have 6 unique paths.
        assertEquals(6, UniquePaths.uniquePaths2(3, 3));

        // Test case 3: A 7x3 grid should have 28 unique paths.
        assertEquals(28, UniquePaths.uniquePaths2(7, 3));

        // Test case 4: A 3x7 grid should have 28 unique paths.
        assertEquals(28, UniquePaths.uniquePaths2(3, 7));
    }
}
