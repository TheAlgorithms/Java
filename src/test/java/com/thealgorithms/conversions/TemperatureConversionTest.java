package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TemperatureConversionTest {
    
    @Test
    public void testTemperatureConversion()
    {
        assertEquals(211.6, TemperatureConversion.convertTemperature(79, "C", "F"));
        assertEquals(411.15, TemperatureConversion.convertTemperature(138, "C", "K"));
        assertEquals(11.67, TemperatureConversion.convertTemperature(53, "F", "C"));
        assertEquals(293.7055555555556, TemperatureConversion.convertTemperature(69, "F", "K"));
        assertEquals(-123.15, TemperatureConversion.convertTemperature(150, "K", "C"));
        assertEquals(-99.67, TemperatureConversion.convertTemperature(200, "K", "F"));
    }
}
