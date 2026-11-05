package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BinomialCoefficientTest {

    @Test
    void testBinomialCoefficient() {
        assertEquals(190, BinomialCoefficient.binomialCoefficient(20, 2));
        assertEquals(792, BinomialCoefficient.binomialCoefficient(12, 5));
        assertEquals(84, BinomialCoefficient.binomialCoefficient(9, 3));
        assertEquals(1, BinomialCoefficient.binomialCoefficient(17, 17));
        assertEquals(0, BinomialCoefficient.binomialCoefficient(5, 6));
        assertEquals(1, BinomialCoefficient.binomialCoefficient(0, 0));
    }

    @Test
    void testNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> BinomialCoefficient.binomialCoefficient(5, -1));
        assertThrows(IllegalArgumentException.class, () -> BinomialCoefficient.binomialCoefficient(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> BinomialCoefficient.binomialCoefficient(-5, -2));
    }
}
