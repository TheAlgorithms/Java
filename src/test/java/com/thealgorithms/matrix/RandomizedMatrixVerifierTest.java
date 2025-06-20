package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RandomizedMatrixVerifierTest {

    @Test
    public void testCorrectMultiplication() {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };
        int[][] C = {
            {19, 22},
            {43, 50}
        };

        // Run multiple times to reduce chance of false positive
        boolean result = true;
        for (int i = 0; i < 5; i++) {
            if (!RandomizedMatrixVerifier.verify(A, B, C)) {
                result = false;
                break;
            }
        }
        assertTrue(result, "Verification should return true for correct C = A * B");
    }

    @Test
    public void testIncorrectMultiplication() {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };
        int[][] wrongC = {
            {19, 22},
            {43, 51} // incorrect value
        };

        // Even with randomness, wrong matrix should fail at least once in 5 tries
        boolean result = true;
        for (int i = 0; i < 5; i++) {
            if (!RandomizedMatrixVerifier.verify(A, B, wrongC)) {
                result = false;
                break;
            }
        }
        assertFalse(result, "Verification should return false for incorrect C");
    }
}
