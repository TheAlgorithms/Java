package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecimalToAnyBaseTest {

    @Test
    void testDecimalToAnyBase() {
        DecimalToAnyBase decimalToAnyBase = new DecimalToAnyBase();
        Assertions.assertEquals("100", decimalToAnyBase.convertToAnyBase(4, 2), "Incorrect Conversion");
        Assertions.assertEquals("11", decimalToAnyBase.convertToAnyBase(4, 3), "Incorrect Conversion");
    }
}
