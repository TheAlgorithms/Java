package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TemperatureConversionTest {

    @Test
    void testTemperatureConversion() {
        assertEquals(326.15, TemperatureConversion.convertTemperature(53, "Celsius", "Kelvin"));
        assertEquals(127.4, TemperatureConversion.convertTemperature(53, "Celsius", "Fahrenheit"));
        assertEquals(-220.14999999999998, TemperatureConversion.convertTemperature(53, "Kelvin", "Celsius"));
        assertEquals(-364.27, TemperatureConversion.convertTemperature(53, "Kelvin", "Fahrenheit"));
        assertEquals(11.666666666666666, TemperatureConversion.convertTemperature(53, "Fahrenheit", "Celsius"));
        assertEquals(284.81666666666666, TemperatureConversion.convertTemperature(53, "Fahrenheit", "Kelvin"));
    }
}
