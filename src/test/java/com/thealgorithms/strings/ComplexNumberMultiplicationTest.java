package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ComplexNumberMultiplicationTest {

    @Test
    void testExample() {
        assertEquals("0+2i", ComplexNumberMultiplication.multiplyComplexNumbers("1+1i", "1+1i"));
    }

    @Test
    void testNegative() {
        assertEquals("2+0i", ComplexNumberMultiplication.multiplyComplexNumbers("1+1i", "1+-1i"));
    }

    @Test
    void testZero() {
        assertEquals("0+0i", ComplexNumberMultiplication.multiplyComplexNumbers("0+0i", "5+5i"));
    }

    @Test
    void testDifferentValues() {
        assertEquals("5+5i", ComplexNumberMultiplication.multiplyComplexNumbers("1+2i", "3+-1i"));
    }
}
