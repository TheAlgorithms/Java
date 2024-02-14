package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TemperatureConversionTest {

    @Test
    public void testTemperatureConversion() {
        assertEquals("326.15", TemperatureConversion.convertCelsiusToKelvin(53));
        assertEquals("89.60", TemperatureConversion.convertCelsiusToFahrenheit(32));
        assertEquals("179.85", TemperatureConversion.convertKelvinToCelsius(453));
        assertEquals("137.93", TemperatureConversion.convertKelvinToFahrenheit(332));
        assertEquals("8.33", TemperatureConversion.convertFahrenheitToCelsius(47));
        assertEquals("273.71", TemperatureConversion.convertFahrenheitToKelvin(33));
    }
}
