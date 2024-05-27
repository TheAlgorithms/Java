package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PythagoreanTripleTest {

    @Test
    public void testPythagoreanTriple() {
        assertTrue(PythagoreanTriple.isPythagTriple(3, 4, 5));
        assertTrue(PythagoreanTriple.isPythagTriple(6, 8, 10));
        assertTrue(PythagoreanTriple.isPythagTriple(9, 12, 15));
        assertTrue(PythagoreanTriple.isPythagTriple(12, 16, 20));
        assertTrue(PythagoreanTriple.isPythagTriple(15, 20, 25));
        assertTrue(PythagoreanTriple.isPythagTriple(18, 24, 30));
        assertFalse(PythagoreanTriple.isPythagTriple(5, 20, 30));
        assertFalse(PythagoreanTriple.isPythagTriple(6, 8, 100));
        assertFalse(PythagoreanTriple.isPythagTriple(-2, -2, 2));
    }
}
