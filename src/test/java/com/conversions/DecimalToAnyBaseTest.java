package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecimalToAnyBaseTest {

    @Test
    public void testDecimalToAnyBase() {
        DecimalToAnyBase decimalToAnyBase = new DecimalToAnyBase();
        assertEquals("100", decimalToAnyBase.convertToAnyBase(4, 2));
        assertEquals("11", decimalToAnyBase.convertToAnyBase(4, 3));
    }
}
