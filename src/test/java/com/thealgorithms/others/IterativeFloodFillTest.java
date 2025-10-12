package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class IterativeFloodFillTest {

    @Test
    void testForEmptyImage() {
        int[][] image = {};
        int[][] expected = {};

        IterativeFloodFill.floodFill(image, 4, 5, 3, 2);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForSingleElementImage() {
        int[][] image = {{1}};
        int[][] expected = {{3}};

        IterativeFloodFill.floodFill(image, 0, 0, 3, 1);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForEmptyRow() {
        int[][] image = {{}};
        int[][] expected = {{}};

        IterativeFloodFill.floodFill(image, 4, 5, 3, 2);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForImageOne() {
        int[][] image = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 3, 3, 3, 3, 0, 0},
            {0, 3, 1, 1, 5, 0, 0},
            {0, 3, 1, 1, 5, 5, 3},
            {0, 3, 5, 5, 1, 1, 3},
            {0, 0, 0, 5, 1, 1, 3},
            {0, 0, 0, 3, 3, 3, 3},
        };

        int[][] expected = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 3, 3, 3, 3, 0, 0},
            {0, 3, 2, 2, 5, 0, 0},
            {0, 3, 2, 2, 5, 5, 3},
            {0, 3, 5, 5, 2, 2, 3},
            {0, 0, 0, 5, 2, 2, 3},
            {0, 0, 0, 3, 3, 3, 3},
        };

        IterativeFloodFill.floodFill(image, 2, 2, 2, 1);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForImageTwo() {
        int[][] image = {
            {0, 0, 1, 1, 0, 0, 0},
            {1, 1, 3, 3, 3, 0, 0},
            {1, 3, 1, 1, 5, 0, 0},
            {0, 3, 1, 1, 5, 5, 3},
            {0, 3, 5, 5, 1, 1, 3},
            {0, 0, 0, 5, 1, 1, 3},
            {0, 0, 0, 1, 3, 1, 3},
        };

        int[][] expected = {
            {0, 0, 2, 2, 0, 0, 0},
            {2, 2, 3, 3, 3, 0, 0},
            {2, 3, 2, 2, 5, 0, 0},
            {0, 3, 2, 2, 5, 5, 3},
            {0, 3, 5, 5, 2, 2, 3},
            {0, 0, 0, 5, 2, 2, 3},
            {0, 0, 0, 2, 3, 2, 3},
        };

        IterativeFloodFill.floodFill(image, 2, 2, 2, 1);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForImageThree() {
        int[][] image = {
            {1, 1, 2, 3, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 0, 3, 1, 2},
        };

        int[][] expected = {
            {4, 4, 2, 3, 4, 4, 4},
            {4, 0, 0, 4, 0, 0, 4},
            {4, 4, 4, 0, 3, 4, 2},
        };

        IterativeFloodFill.floodFill(image, 0, 1, 4, 1);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForSameNewAndOldColor() {
        int[][] image = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        int[][] expected = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        IterativeFloodFill.floodFill(image, 0, 1, 1, 1);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForBigImage() {
        int[][] image = new int[100][100];

        assertDoesNotThrow(() -> IterativeFloodFill.floodFill(image, 0, 0, 1, 0));
    }

    @Test
    void testForBelowZeroX() {
        int[][] image = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        int[][] expected = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        IterativeFloodFill.floodFill(image, -1, 1, 1, 0);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForBelowZeroY() {
        int[][] image = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        int[][] expected = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        IterativeFloodFill.floodFill(image, 1, -1, 1, 0);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForAboveBoundaryX() {
        int[][] image = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        int[][] expected = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        IterativeFloodFill.floodFill(image, 100, 1, 1, 0);
        assertArrayEquals(expected, image);
    }

    @Test
    void testForAboveBoundaryY() {
        int[][] image = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        int[][] expected = {{1, 1, 2}, {1, 0, 0}, {1, 1, 1}};

        IterativeFloodFill.floodFill(image, 1, 100, 1, 0);
        assertArrayEquals(expected, image);
    }
}
