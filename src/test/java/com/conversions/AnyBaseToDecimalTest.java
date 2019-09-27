package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnyBaseToDecimalTest {

    @Test
    void testAnyBaseToDecimal() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("2", 2));
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("3", 2));
        Assertions.assertEquals("3", anyBaseToDecimal.convertToDecimal("11", 2));
        Assertions.assertEquals("4", anyBaseToDecimal.convertToDecimal("100", 2));
        Assertions.assertEquals("5", anyBaseToDecimal.convertToDecimal("101", 2));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("1010", 2));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("10000000000", 2));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("8", 8));
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("9", 8));
        Assertions.assertEquals("7", anyBaseToDecimal.convertToDecimal("7", 8));
        Assertions.assertEquals("8", anyBaseToDecimal.convertToDecimal("10", 8));
        Assertions.assertEquals("9", anyBaseToDecimal.convertToDecimal("11", 8));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("12", 8));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("2000", 8));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("A", 10));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("10", 10));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("1024", 10));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("G", 16));
        Assertions.assertEquals("16", anyBaseToDecimal.convertToDecimal("10", 16));
        Assertions.assertEquals("17", anyBaseToDecimal.convertToDecimal("11", 16));
        Assertions.assertEquals("100", anyBaseToDecimal.convertToDecimal("64", 16));
        Assertions.assertEquals("225", anyBaseToDecimal.convertToDecimal("E1", 16));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("400", 16));

    }

    @Test
    void testConvertToDecimal2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("1", anyBaseToDecimal.convertToDecimal2("1", 2));
    }

    @Test
    void testConvertToDecimal2CheckInvalid() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("2", 2));
    }

    @Test
    void testConvertToDecimal2CoverageReturnInvalidNumber() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("12", 2));
    }

    @Test
    void testConvertToDecimal2CoverageReturnToString() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("3", anyBaseToDecimal.convertToDecimal2("11", 2));
    }
}
