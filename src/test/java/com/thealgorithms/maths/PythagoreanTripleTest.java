package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PythagoreanTripleTest {

    @ParameterizedTest
    @CsvSource({"3, 4, 5, true", "6, 8, 10, true", "9, 12, 15, true", "12, 16, 20, true", "15, 20, 25, true", "18, 24, 30, true", "5, 20, 30, false", "6, 8, 100, false", "-2, -2, 2, false", "0, 0, 0, false", "5, 5, 5, false"})
    void testIsPythagoreanTriple(int a, int b, int c, boolean expected) {
        assertEquals(expected, PythagoreanTriple.isPythagTriple(a, b, c));
    }

    @Test
    void testUnorderedInputStillValid() {
        // Should still detect Pythagorean triples regardless of argument order
        assertTrue(PythagoreanTriple.isPythagTriple(5, 3, 4));
        assertTrue(PythagoreanTriple.isPythagTriple(13, 12, 5));
    }
}
