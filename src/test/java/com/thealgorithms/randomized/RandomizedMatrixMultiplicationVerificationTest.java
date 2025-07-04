package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandomizedMatrixMultiplicationVerificationTest {

    @Test
    void testCorrectMultiplication() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] c = {{19, 22}, {43, 50}};
        assertTrue(RandomizedMatrixMultiplicationVerification.verify(a, b, c, 10));
    }

    @Test
    void testIncorrectMultiplication() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] wrongC = {{20, 22}, {43, 51}};
        assertFalse(RandomizedMatrixMultiplicationVerification.verify(a, b, wrongC, 10));
    }

    @Test
    void testLargeMatrix() {
        int size = 100;
        int[][] a = new int[size][size];
        int[][] b = new int[size][size];
        int[][] c = new int[size][size];

        for (int i = 0; i < size; i++) {
            a[i][i] = 1;
            b[i][i] = 1;
            c[i][i] = 1;
        }

        assertTrue(RandomizedMatrixMultiplicationVerification.verify(a, b, c, 15));
    }
}
