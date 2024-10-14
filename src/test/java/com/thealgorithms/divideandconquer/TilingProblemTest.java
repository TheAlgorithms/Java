package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class TilingProblemTest {

    @Test
    public void testTilingSize2() {
        int[][] expected = {{1, 1}, {1, 0}};
        int[][] result = TilingProblem.solveTiling(2, 1, 1);
        assertArrayEquals(expected, result);
    }
}
