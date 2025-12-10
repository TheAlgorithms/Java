package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinomialCoefficientTest {

    @Test
    void testBinomialCoefficient() {
        assertEquals(190, BinomialCoefficient.binomialCoefficient(20, 2));
        assertEquals(792, BinomialCoefficient.binomialCoefficient(12, 5));
        assertEquals(84, BinomialCoefficient.binomialCoefficient(9, 3));
        assertEquals(1, BinomialCoefficient.binomialCoefficient(17, 17));
    }
}
