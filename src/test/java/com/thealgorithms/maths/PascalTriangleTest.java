package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PascalTriangleTest {

    @Test
    void testForOne() {
        int[][] result = PascalTriangle.pascal(1);
        int[][] expected = { { 1 } };
        assertArrayEquals(result, expected);
    }

    @Test
    void testForTwo() {
        int[][] result = PascalTriangle.pascal(2);
        int[][] expected = { { 1, 0 }, { 1, 1 } };
        assertArrayEquals(result, expected);
    }

    @Test
    void testForFive() {
        int[][] result = PascalTriangle.pascal(5);
        int[][] expected = {
            { 1, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 1, 2, 1, 0, 0 },
            { 1, 3, 3, 1, 0 },
            { 1, 4, 6, 4, 1 },
        };
        assertArrayEquals(result, expected);
    }

    @Test
    void testForEight() {
        int[][] result = PascalTriangle.pascal(8);
        int[][] expected = {
            { 1, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0 },
            { 1, 2, 1, 0, 0, 0, 0, 0 },
            { 1, 3, 3, 1, 0, 0, 0, 0 },
            { 1, 4, 6, 4, 1, 0, 0, 0 },
            { 1, 5, 10, 10, 5, 1, 0, 0 },
            { 1, 6, 15, 20, 15, 6, 1, 0 },
            { 1, 7, 21, 35, 35, 21, 7, 1 },
        };
        assertArrayEquals(expected, result);
    }
}
