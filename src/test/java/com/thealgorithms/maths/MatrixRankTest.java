package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MatrixRankTest {

    @Test
    void computeRank() {

        double[][] identityMatrix = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        assertEquals(3, MatrixRank.computeRank(identityMatrix));

        double[][] zeroMatrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(0, MatrixRank.computeRank(zeroMatrix));

        double[][] square1x1Matrix = {{1}};
        assertEquals(1, MatrixRank.computeRank(square1x1Matrix));

        double[][] square2x2Matrix = {{1, 2}, {3, 4}};
        assertEquals(2, MatrixRank.computeRank(square2x2Matrix));

        double[][] square3x3Matrix = {{3, -1, 2}, {-3, 1, 2}, {-6, 2, 4}};
        assertEquals(2, MatrixRank.computeRank(square3x3Matrix));

        double[][] square4x4Matrix = {{2, 3, 0, 1}, {1, 0, 1, 2}, {-1, 1, 1, -2}, {1, 5, 3, -1}};
        assertEquals(3, MatrixRank.computeRank(square4x4Matrix));

        double[][] rectangular2X3Matrix = {{1, 2, 3}, {3, 6, 9}};
        assertEquals(1, MatrixRank.computeRank(rectangular2X3Matrix));

        double[][] rectangular3X4Matrix = {{0.25, 0.5, 0.75, 2}, {1.5, 3, 4.5, 6}, {1, 2, 3, 4}};
        assertEquals(2, MatrixRank.computeRank(rectangular3X4Matrix));
    }
}
