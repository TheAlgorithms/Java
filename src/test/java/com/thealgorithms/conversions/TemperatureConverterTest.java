package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TemperatureConverterTest {

    @Test
    public void testCelsiusToFahrenheit() {
        assertEquals(32.0, TemperatureConverter.celsiusToFahrenheit(0), 0.001);
        assertEquals(212.0, TemperatureConverter.celsiusToFahrenheit(100), 0.001);
    }

    @Test
    public void testCelsiusToKelvin() {
        assertEquals(273.15, TemperatureConverter.celsiusToKelvin(0), 0.001);
        assertEquals(373.15, TemperatureConverter.celsiusToKelvin(100), 0.001);
    }

    @Test
    public void testFahrenheitToCelsius() {
        assertEquals(0.0, TemperatureConverter.fahrenheitToCelsius(32.0), 0.001);
        assertEquals(100.0, TemperatureConverter.fahrenheitToCelsius(212.0), 0.001);
    }

    @Test
    public void testFahrenheitToKelvin() {
        assertEquals(273.15, TemperatureConverter.fahrenheitToKelvin(32.0), 0.001);
        assertEquals(373.15, TemperatureConverter.fahrenheitToKelvin(212.0), 0.001);
    }

    @Test
    public void testKelvinToCelsius() {
        assertEquals(0.0, TemperatureConverter.kelvinToCelsius(273.15), 0.001);
        assertEquals(100.0, TemperatureConverter.kelvinToCelsius(373.15), 0.001);
    }

    @Test
    public void testKelvinToFahrenheit() {
        assertEquals(32.0, TemperatureConverter.kelvinToFahrenheit(273.15), 0.001);
        assertEquals(212.0, TemperatureConverter.kelvinToFahrenheit(373.15), 0.001);
    }
}
