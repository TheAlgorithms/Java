package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ComplexNumberMultiplyTest {

    @Test
    void testExample() {
        assertEquals("0+2i", ComplexNumberMultiply.multiply("1+1i", "1+1i"));
    }

    @Test
    void testNegative() {
        assertEquals("0+-2i", ComplexNumberMultiply.multiply("1+-1i", "1+-1i"));
    }

    @Test
    void testZero() {
        assertEquals("0+0i", ComplexNumberMultiply.multiply("0+0i", "5+3i"));
    }
}
