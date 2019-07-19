package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecimalToOctalTest {
    @Test
    void testDecimalToOctalTest() {
        DecimalToOctal decimalToOctal = new DecimalToOctal();
        Assertions.assertEquals("41", decimalToOctal.decimalToOctal("33"), "Incorrect Conversion");
        Assertions.assertEquals("5512", decimalToOctal.decimalToOctal("2890"), "Incorrect Conversion");
        Assertions.assertEquals("12525252525252525252525241", decimalToOctal.decimalToOctal("50371909150609548946081"), "Incorrect Conversion");
        Assertions.assertEquals("13", decimalToOctal.decimalToOctal("11"), "Incorrect Conversion");
        Assertions.assertEquals("46703754", decimalToOctal.decimalToOctal("10192876"), "Incorrect Conversion");
    }
}
