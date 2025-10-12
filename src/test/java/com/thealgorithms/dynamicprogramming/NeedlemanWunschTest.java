package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit Tests for the {@code NeedlemanWunsch} class
 */
class NeedlemanWunschTest {

    @Test
    void testIdenticalStrings() {
        int score = NeedlemanWunsch.align("GATTACA", "GATTACA", 1, -1, -2);
        assertEquals(7, score); // All matches, 7*1
    }

    @Test
    void testSimpleMismatch() {
        int score = NeedlemanWunsch.align("GATTACA", "GACTATA", 1, -1, -2);
        assertEquals(3, score);
    }

    @Test
    void testInsertion() {
        int score = NeedlemanWunsch.align("GATTACA", "GATACA", 1, -1, -2);
        // One deletion (gap penalty)
        assertEquals(4, score);
    }

    @Test
    void testEmptyStrings() {
        assertEquals(0, NeedlemanWunsch.align("", "", 1, -1, -2));
    }

    @Test
    void testOneEmpty() {
        assertEquals(-14, NeedlemanWunsch.align("GATTACA", "", 1, -1, -2)); // 7 gaps × -2
    }

    @Test
    void testGapHeavyAlignment() {
        int score = NeedlemanWunsch.align("AAAA", "AA", 1, -1, -2);
        assertEquals(-2, score); // Two matches (2*1) + two gaps (2*-2)
    }

    @ParameterizedTest
    @CsvSource({"null,ABC", "ABC,null", "null,null"})
    void testNullInputs(String s1, String s2) {
        // Interpret "null" literal as Java null
        String first = "null".equals(s1) ? null : s1;
        String second = "null".equals(s2) ? null : s2;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> NeedlemanWunsch.align(first, second, 1, -1, -2));
        assertEquals("Input strings must not be null.", ex.getMessage());
    }
}
