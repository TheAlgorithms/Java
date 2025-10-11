package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ComplexNumberMultiplicationTest {

    @Test
    void testBasicMultiplication() {
        assertEquals("0+2i", ComplexNumberMultiplication.complexNumberMultiply("1+1i", "1+1i"));
    }

    @Test
    void testMultiplicationWithZero() {
        assertEquals("0+0i", ComplexNumberMultiplication.complexNumberMultiply("0+0i", "1+1i"));
    }

    @Test
    void testRealNumbersOnly() {
        assertEquals("6+0i", ComplexNumberMultiplication.complexNumberMultiply("2+0i", "3+0i"));
    }

    @Test
    void testImaginaryNumbersOnly() {
        assertEquals("-6+0i", ComplexNumberMultiplication.complexNumberMultiply("0+2i", "0+3i"));
    }

    @Test
    void testNegativeRealPart() {
        assertEquals("-3+4i", ComplexNumberMultiplication.complexNumberMultiply("1+1i", "-1+3i"));
    }

    @Test
    void testNegativeImaginaryPart() {
        assertEquals("0+-2i", ComplexNumberMultiplication.complexNumberMultiply("1-1i", "1-1i"));
    }

    @Test
    void testLargerNumbers() {
        assertEquals("0+20i", ComplexNumberMultiplication.complexNumberMultiply("2+3i", "4+2i"));
    }

    @Test
    void testNegativeResult() {
        assertEquals("-7+22i", ComplexNumberMultiplication.complexNumberMultiply("3+4i", "1+6i"));
    }

    @Test
    void testIdentityMultiplication() {
        assertEquals("5+3i", ComplexNumberMultiplication.complexNumberMultiply("5+3i", "1+0i"));
    }

    @Test
    void testNullInput() {
        assertEquals("0+0i", ComplexNumberMultiplication.complexNumberMultiply(null, "1+1i"));
    }

    @Test
    void testEmptyString() {
        assertEquals("0+0i", ComplexNumberMultiplication.complexNumberMultiply("", "1+1i"));
    }

    @Test
    void testBothNegativeParts() {
        assertEquals("0+-2i", ComplexNumberMultiplication.complexNumberMultiply("-1+-1i", "1+0i"));
    }
}
