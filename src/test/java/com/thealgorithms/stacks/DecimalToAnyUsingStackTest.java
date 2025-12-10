package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DecimalToAnyUsingStackTest {

    @Test
    void testConvertToBinary() {
        assertEquals("0", DecimalToAnyUsingStack.convert(0, 2));
        assertEquals("11110", DecimalToAnyUsingStack.convert(30, 2));
    }

    @Test
    void testConvertToOctal() {
        assertEquals("36", DecimalToAnyUsingStack.convert(30, 8));
    }

    @Test
    void testConvertToDecimal() {
        assertEquals("30", DecimalToAnyUsingStack.convert(30, 10));
    }

    @Test
    void testConvertToHexadecimal() {
        assertEquals("1E", DecimalToAnyUsingStack.convert(30, 16));
    }

    @Test
    void testInvalidRadix() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> DecimalToAnyUsingStack.convert(30, 1));
        assertEquals("Invalid radix: 1. Radix must be between 2 and 16.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> DecimalToAnyUsingStack.convert(30, 17));
        assertEquals("Invalid radix: 17. Radix must be between 2 and 16.", thrown.getMessage());
    }

    @Test
    void testNegativeNumber() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> DecimalToAnyUsingStack.convert(-30, 2));
        assertEquals("Number must be non-negative.", thrown.getMessage());
    }
}
