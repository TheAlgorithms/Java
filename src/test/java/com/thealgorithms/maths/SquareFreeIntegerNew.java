package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SquareFreeIntegerTest {

    @Test
    void testSquareFreeNumbers() {
        assertTrue(SquareFreeInteger.isSquareFree(1));
        assertTrue(SquareFreeInteger.isSquareFree(2));
        assertTrue(SquareFreeInteger.isSquareFree(3));
        assertTrue(SquareFreeInteger.isSquareFree(6));
        assertTrue(SquareFreeInteger.isSquareFree(15));
    }

    @Test
    void testNonSquareFreeNumbers() {
        assertFalse(SquareFreeInteger.isSquareFree(4));
        assertFalse(SquareFreeInteger.isSquareFree(8));
        assertFalse(SquareFreeInteger.isSquareFree(9));
        assertFalse(SquareFreeInteger.isSquareFree(12));
        assertFalse(SquareFreeInteger.isSquareFree(18));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> SquareFreeInteger.isSquareFree(0));
        assertThrows(IllegalArgumentException.class, () -> SquareFreeInteger.isSquareFree(-5));
    }
}
