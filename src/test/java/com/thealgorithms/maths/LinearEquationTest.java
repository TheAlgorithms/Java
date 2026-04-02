package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LinearEquationTest {

    @Test
    void testSolveBasic() {
        assertEquals(3.0, LinearEquation.solve(2, -6));
    }

    @Test
    void testSolveNegativeResult() {
        assertEquals(-5.0, LinearEquation.solve(1, 5));
    }

    @Test
    void testSolveWithZeroB() {
        assertEquals(0.0, LinearEquation.solve(4, 0), 1e-9);
    }

    @Test
    void testSolveWithNegativeA() {
        assertEquals(3.0, LinearEquation.solve(-3, 9));
    }

    @Test
    void testAllIllegalInput() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> LinearEquation.solve(0, 5)), () -> assertThrows(IllegalArgumentException.class, () -> LinearEquation.solve(0, 0)), () -> assertThrows(IllegalArgumentException.class, () -> LinearEquation.solve(0, -3)));

    }
}