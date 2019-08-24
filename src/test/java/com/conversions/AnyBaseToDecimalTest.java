package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnyBaseToDecimalTest {

    @Test
    public void testAnyBaseToDecimal() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("2", 2));
        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("3", 2));
        assertEquals("3", anyBaseToDecimal.convertToDecimal("11", 2));
        assertEquals("4", anyBaseToDecimal.convertToDecimal("100", 2));
        assertEquals("5", anyBaseToDecimal.convertToDecimal("101", 2));
        assertEquals("10", anyBaseToDecimal.convertToDecimal("1010", 2));
        assertEquals("1024", anyBaseToDecimal.convertToDecimal("10000000000", 2));

        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("8", 8));
        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("9", 8));
        assertEquals("7", anyBaseToDecimal.convertToDecimal("7", 8));
        assertEquals("8", anyBaseToDecimal.convertToDecimal("10", 8));
        assertEquals("9", anyBaseToDecimal.convertToDecimal("11", 8));
        assertEquals("10", anyBaseToDecimal.convertToDecimal("12", 8));
        assertEquals("1024", anyBaseToDecimal.convertToDecimal("2000", 8));

        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("A", 10));
        assertEquals("10", anyBaseToDecimal.convertToDecimal("10", 10));
        assertEquals("1024", anyBaseToDecimal.convertToDecimal("1024", 10));

        assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("G", 16));
        assertEquals("16", anyBaseToDecimal.convertToDecimal("10", 16));
        assertEquals("17", anyBaseToDecimal.convertToDecimal("11", 16));
        assertEquals("100", anyBaseToDecimal.convertToDecimal("64", 16));
        assertEquals("225", anyBaseToDecimal.convertToDecimal("E1", 16));
        assertEquals("1024", anyBaseToDecimal.convertToDecimal("400", 16));
    }
}
