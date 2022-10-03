package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PythagoreanTripleTest {

    @Test
    public void Testpythagoreantriple() {
        assertEquals(true, PythagoreanTriple.isPythagTriple(3, 4, 5));
        assertEquals(true, PythagoreanTriple.isPythagTriple(6, 8, 10));
        assertEquals(true, PythagoreanTriple.isPythagTriple(9, 12, 15));
        assertEquals(true, PythagoreanTriple.isPythagTriple(12, 16, 20));
        assertEquals(true, PythagoreanTriple.isPythagTriple(15, 20, 25));
        assertEquals(true, PythagoreanTriple.isPythagTriple(18, 24, 30));
        assertEquals(false, PythagoreanTriple.isPythagTriple(5, 20, 30));
        assertEquals(false, PythagoreanTriple.isPythagTriple(6, 8, 100));
        assertEquals(false, PythagoreanTriple.isPythagTriple(-2, -2, 2));
    }
}
