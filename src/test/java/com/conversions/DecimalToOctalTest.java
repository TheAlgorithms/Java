package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecimalToOctalTest {

    @Test
    public void testDecimalToOctalTest() {
        DecimalToOctal decimalToOctal = new DecimalToOctal();
        assertEquals("41", decimalToOctal.decimalToOctal("33"));
        assertEquals("5512", decimalToOctal.decimalToOctal("2890"));
        assertEquals("12525252525252525252525241", decimalToOctal.decimalToOctal("50371909150609548946081"));
        assertEquals("13", decimalToOctal.decimalToOctal("11"));
        assertEquals("46703754", decimalToOctal.decimalToOctal("10192876"));
    }
}
