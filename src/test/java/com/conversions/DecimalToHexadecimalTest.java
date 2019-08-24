package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecimalToHexadecimalTest {

    @Test
    public void testDecimalToHexadecimalTest() {
        DecimalToHexadecimal decimalToHexadecimal = new DecimalToHexadecimal();
        assertEquals("F", decimalToHexadecimal.decimalToHex("15"));
        assertEquals("121", decimalToHexadecimal.decimalToHex("289"));
        assertEquals("AAAAAAAAAAAAAAAAAA1", decimalToHexadecimal.decimalToHex("50371909150609548946081"));
        assertEquals("A", decimalToHexadecimal.decimalToHex("10"));
        assertEquals("8B2F", decimalToHexadecimal.decimalToHex("35631"));
    }
}
