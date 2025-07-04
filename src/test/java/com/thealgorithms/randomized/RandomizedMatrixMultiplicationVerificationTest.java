package com.thealgorithms.randomized;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RandomizedMatrixMultiplicationVerificationTest {

    @Test
    void testCorrectMultiplication() {
        int[][] A = {{ 1, 2 },{ 3, 4 }};
        int[][] B = {{ 5, 6 }, { 7, 8 }};
        int[][] C = {{ 19, 22 }, { 43, 50 }};
        assertTrue(RandomizedMatrixMultiplicationVerification.verify(A, B, C, 10));
    }

    @Test
    void testIncorrectMultiplication() {
        int[][] A = {{ 1, 2 }, { 3, 4 }};
        int[][] B = {{ 5, 6 }, { 7, 8 }};
        int[][] wrongC = {{ 20, 22 }, { 43, 51 }};
        assertFalse(RandomizedMatrixMultiplicationVerification.verify(A, B, wrongC, 10));
    }

    @Test
    void testLargeMatrix() {
        int size = 100;
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

        for (int i = 0; i < size; i++) {
            A[i][i] = 1;
            B[i][i] = 1;
            C[i][i] = 1;
        }

        assertTrue(RandomizedMatrixMultiplicationVerification.verify(A, B, C, 15));
    }
}
