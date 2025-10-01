package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for MiniMaxAlgorithm
 * Tests the minimax algorithm implementation for game tree evaluation
 */
class MiniMaxAlgorithmTest {

    private MiniMaxAlgorithm miniMax;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        miniMax = new MiniMaxAlgorithm();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testConstructorCreatesValidScores() {
        // The default constructor should create scores array of length 8 (2^3)
        assertEquals(8, miniMax.getScores().length);
        assertEquals(3, miniMax.getHeight());

        // All scores should be positive (between 1 and 99)
        for (int score : miniMax.getScores()) {
            assertTrue(score >= 1 && score <= 99);
        }
    }

    @Test
    void testSetScoresWithValidPowerOfTwo() {
        int[] validScores = {10, 20, 30, 40};
        miniMax.setScores(validScores);

        assertArrayEquals(validScores, miniMax.getScores());
        assertEquals(2, miniMax.getHeight()); // log2(4) = 2
    }

    @Test
    void testSetScoresWithInvalidLength() {
        int[] invalidScores = {10, 20, 30}; // Length 3 is not a power of 2
        miniMax.setScores(invalidScores);

        // Should print error message and not change the scores
        String output = outputStream.toString();
        assertTrue(output.contains("The number of scores must be a power of 2."));

        // Scores should remain unchanged (original length 8)
        assertEquals(8, miniMax.getScores().length);
    }

    @Test
    void testSetScoresWithZeroLength() {
        int[] emptyScores = {}; // Length 0 is not a power of 2
        miniMax.setScores(emptyScores);

        // Should print error message and not change the scores
        String output = outputStream.toString();
        assertTrue(output.contains("The number of scores must be a power of 2."));

        // Scores should remain unchanged (original length 8)
        assertEquals(8, miniMax.getScores().length);
    }

    @Test
    void testSetScoresWithVariousInvalidLengths() {
        // Test multiple invalid lengths to ensure isPowerOfTwo function is fully covered
        int[][] invalidScoreArrays = {
            {1, 2, 3, 4, 5}, // Length 5
            {1, 2, 3, 4, 5, 6}, // Length 6
            {1, 2, 3, 4, 5, 6, 7}, // Length 7
            new int[9], // Length 9
            new int[10], // Length 10
            new int[15] // Length 15
        };

        for (int[] invalidScores : invalidScoreArrays) {
            // Clear the output stream for each test
            outputStream.reset();
            miniMax.setScores(invalidScores);

            // Should print error message for each invalid length
            String output = outputStream.toString();
            assertTrue(output.contains("The number of scores must be a power of 2."), "Failed for array length: " + invalidScores.length);
        }

        // Scores should remain unchanged (original length 8)
        assertEquals(8, miniMax.getScores().length);
    }

    @Test
    void testSetScoresWithSingleElement() {
        int[] singleScore = {42};
        miniMax.setScores(singleScore);

        assertArrayEquals(singleScore, miniMax.getScores());
        assertEquals(0, miniMax.getHeight()); // log2(1) = 0
    }

    @Test
    void testMiniMaxWithKnownScores() {
        // Test with a known game tree: [3, 12, 8, 2]
        int[] testScores = {3, 12, 8, 2};
        miniMax.setScores(testScores);

        // Maximizer starts: should choose max(min(3,12), min(8,2)) = max(3, 2) = 3
        int result = miniMax.miniMax(0, true, 0, false);
        assertEquals(3, result);
    }

    @Test
    void testMiniMaxWithMinimizerFirst() {
        // Test with minimizer starting first
        int[] testScores = {3, 12, 8, 2};
        miniMax.setScores(testScores);

        // Minimizer starts: should choose min(max(3,12), max(8,2)) = min(12, 8) = 8
        int result = miniMax.miniMax(0, false, 0, false);
        assertEquals(8, result);
    }

    @Test
    void testMiniMaxWithLargerTree() {
        // Test with 8 elements: [5, 6, 7, 4, 5, 3, 6, 2]
        int[] testScores = {5, 6, 7, 4, 5, 3, 6, 2};
        miniMax.setScores(testScores);

        // Maximizer starts
        int result = miniMax.miniMax(0, true, 0, false);
        // Expected: max(min(max(5,6), max(7,4)), min(max(5,3), max(6,2)))
        //         = max(min(6, 7), min(5, 6)) = max(6, 5) = 6
        assertEquals(6, result);
    }

    @Test
    void testMiniMaxVerboseOutput() {
        int[] testScores = {3, 12, 8, 2};
        miniMax.setScores(testScores);

        miniMax.miniMax(0, true, 0, true);

        String output = outputStream.toString();
        assertTrue(output.contains("Maximizer"));
        assertTrue(output.contains("Minimizer"));
        assertTrue(output.contains("chooses"));
    }

    @Test
    void testGetRandomScoresLength() {
        int[] randomScores = MiniMaxAlgorithm.getRandomScores(4, 50);
        assertEquals(16, randomScores.length); // 2^4 = 16

        // All scores should be between 1 and 50
        for (int score : randomScores) {
            assertTrue(score >= 1 && score <= 50);
        }
    }

    @Test
    void testGetRandomScoresWithDifferentParameters() {
        int[] randomScores = MiniMaxAlgorithm.getRandomScores(2, 10);
        assertEquals(4, randomScores.length); // 2^2 = 4

        // All scores should be between 1 and 10
        for (int score : randomScores) {
            assertTrue(score >= 1 && score <= 10);
        }
    }

    @Test
    void testMainMethod() {
        // Test that main method runs without errors
        assertDoesNotThrow(() -> MiniMaxAlgorithm.main(new String[] {}));

        String output = outputStream.toString();
        assertTrue(output.contains("The best score for"));
        assertTrue(output.contains("Maximizer"));
    }

    @Test
    void testHeightCalculation() {
        // Test height calculation for different array sizes
        int[] scores2 = {1, 2};
        miniMax.setScores(scores2);
        assertEquals(1, miniMax.getHeight()); // log2(2) = 1

        int[] scores16 = new int[16];
        miniMax.setScores(scores16);
        assertEquals(4, miniMax.getHeight()); // log2(16) = 4
    }

    @Test
    void testEdgeCaseWithZeroScores() {
        int[] zeroScores = {0, 0, 0, 0};
        miniMax.setScores(zeroScores);

        int result = miniMax.miniMax(0, true, 0, false);
        assertEquals(0, result);
    }

    @Test
    void testEdgeCaseWithNegativeScores() {
        int[] negativeScores = {-5, -2, -8, -1};
        miniMax.setScores(negativeScores);

        // Tree evaluation with maximizer first:
        // Level 1 (minimizer): min(-5,-2) = -5, min(-8,-1) = -8
        // Level 0 (maximizer): max(-5, -8) = -5
        int result = miniMax.miniMax(0, true, 0, false);
        assertEquals(-5, result);
    }

    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testSetScoresWithNegativeLength() {
        // This test ensures the first condition of isPowerOfTwo (n > 0) is tested
        // Although we can't directly create an array with negative length,
        // we can test edge cases around zero and ensure proper validation

        // Test with array length 0 (edge case for n > 0 condition)
        int[] emptyArray = new int[0];
        outputStream.reset();
        miniMax.setScores(emptyArray);

        String output = outputStream.toString();
        assertTrue(output.contains("The number of scores must be a power of 2."));
        assertEquals(8, miniMax.getScores().length); // Should remain unchanged
    }

    @Test
    void testSetScoresWithLargePowerOfTwo() {
        // Test with a large power of 2 to ensure the algorithm works correctly
        int[] largeValidScores = new int[32]; // 32 = 2^5
        for (int i = 0; i < largeValidScores.length; i++) {
            largeValidScores[i] = i + 1;
        }

        miniMax.setScores(largeValidScores);
        assertArrayEquals(largeValidScores, miniMax.getScores());
        assertEquals(5, miniMax.getHeight()); // log2(32) = 5
    }

    @Test
    void testSetScoresValidEdgeCases() {
        // Test valid powers of 2 to ensure isPowerOfTwo returns true correctly
        int[][] validPowersOf2 = {
            new int[1], // 1 = 2^0
            new int[2], // 2 = 2^1
            new int[4], // 4 = 2^2
            new int[8], // 8 = 2^3
            new int[16], // 16 = 2^4
            new int[64] // 64 = 2^6
        };

        int[] expectedHeights = {0, 1, 2, 3, 4, 6};

        for (int i = 0; i < validPowersOf2.length; i++) {
            miniMax.setScores(validPowersOf2[i]);
            assertEquals(validPowersOf2[i].length, miniMax.getScores().length, "Failed for array length: " + validPowersOf2[i].length);
            assertEquals(expectedHeights[i], miniMax.getHeight(), "Height calculation failed for array length: " + validPowersOf2[i].length);
        }
    }
}
