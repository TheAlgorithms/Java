package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecimalToHexadecimalTest {

    @Test
    void testDecimalToHexadecimalTest() {
        DecimalToHexadecimal decimalToHexadecimal = new DecimalToHexadecimal();
        Assertions.assertEquals("F", decimalToHexadecimal.decimalToHex("15"), "Incorrect Conversion");
        Assertions.assertEquals("121", decimalToHexadecimal.decimalToHex("289"), "Incorrect Conversion");
        Assertions.assertEquals("AAAAAAAAAAAAAAAAAA1", decimalToHexadecimal.decimalToHex("50371909150609548946081"), "Incorrect Conversion");
        Assertions.assertEquals("A", decimalToHexadecimal.decimalToHex("10"), "Incorrect Conversion");
        Assertions.assertEquals("8B2F", decimalToHexadecimal.decimalToHex("35631"), "Incorrect Conversion");
    }
}
