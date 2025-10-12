package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the {@code SmithWaterman} class.
 */
class SmithWatermanTest {

    @Test
    void testIdenticalStrings() {
        int score = SmithWaterman.align("GATTACA", "GATTACA", 2, -1, -2);
        assertEquals(14, score); // full match, 7*2
    }

    @Test
    void testPartialMatch() {
        int score = SmithWaterman.align("GATTACA", "TTAC", 2, -1, -2);
        assertEquals(8, score); // best local alignment "TTAC"
    }

    @Test
    void testNoMatch() {
        int score = SmithWaterman.align("AAAA", "TTTT", 1, -1, -2);
        assertEquals(0, score); // no alignment worth keeping
    }

    @Test
    void testInsertionDeletion() {
        int score = SmithWaterman.align("ACGT", "ACGGT", 1, -1, -2);
        assertEquals(3, score); // local alignment "ACG"
    }

    @Test
    void testEmptyStrings() {
        assertEquals(0, SmithWaterman.align("", "", 1, -1, -2));
    }

    @ParameterizedTest
    @CsvSource({"null,ABC", "ABC,null", "null,null"})
    void testNullInputs(String s1, String s2) {
        String first = "null".equals(s1) ? null : s1;
        String second = "null".equals(s2) ? null : s2;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> SmithWaterman.align(first, second, 1, -1, -2));
        assertEquals("Input strings must not be null.", ex.getMessage());
    }
}
