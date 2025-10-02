package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MiniMaxAlgorithmTest {

    private MiniMaxAlgorithm miniMax;

    @BeforeEach
    void setUp() {
        miniMax = new MiniMaxAlgorithm();
    }

    @Test
    void testRandomScoresLengthIsPowerOfTwo() {
        int[] scores = MiniMaxAlgorithm.getRandomScores(3, 100); // 2^3 = 8
        assertEquals(8, scores.length);
    }

    @Test
    void testSetScoresValidPowerOfTwo() {
        int[] validScores = {1, 2, 3, 4};
        miniMax.setScores(validScores);
        assertArrayEquals(validScores, miniMax.getScores());
        assertEquals(2, miniMax.getHeight()); // log2(4) = 2
    }

    @Test
    void testSetScoresInvalidLengthThrowsException() {
        int[] invalidScores = {1, 2, 3}; // length 3, not a power of 2
        assertThrows(IllegalArgumentException.class, () -> miniMax.setScores(invalidScores));
    }

    @Test
    void testSetScoresNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> miniMax.setScores(null));
    }

    @Test
    void testSetScoresEmptyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> miniMax.setScores(new int[]{}));
    }

    @Test
    void testLog2ValidInputs() {
        // indirectly tested through setScores, but also test directly
        assertEquals(0, invokeLog2(1));
        assertEquals(1, invokeLog2(2));
        assertEquals(2, invokeLog2(4));
        assertEquals(3, invokeLog2(8));
    }

    @Test
    void testLog2InvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> invokeLog2(0));
        assertThrows(IllegalArgumentException.class, () -> invokeLog2(-4));
    }

    @Test
    void testMiniMaxComputation() {
        int[] customScores = {3, 5, 2, 9}; // Tree height = 2
        miniMax.setScores(customScores);
        int bestScore = miniMax.miniMax(0, true, 0, false);
        assertEquals(5, bestScore); // Correct result from minimax
    }

    // Utility to call private log2 method using reflection
    private int invokeLog2(int n) {
        try {
            var method = MiniMaxAlgorithm.class.getDeclaredMethod("log2", int.class);
            method.setAccessible(true);
            return (int) method.invoke(miniMax, n);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
