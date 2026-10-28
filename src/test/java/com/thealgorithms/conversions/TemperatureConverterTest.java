package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TemperatureConverterTest {

    private static final double DELTA = 0.01;

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(32.0, TemperatureConverter.celsiusToFahrenheit(0.0), DELTA);
        assertEquals(212.0, TemperatureConverter.celsiusToFahrenheit(100.0), DELTA);
        assertEquals(-40.0, TemperatureConverter.celsiusToFahrenheit(-40.0), DELTA);
        assertEquals(98.6, TemperatureConverter.celsiusToFahrenheit(37.0), DELTA);
    }

    @Test
    void testCelsiusToKelvin() {
        assertEquals(273.15, TemperatureConverter.celsiusToKelvin(0.0), DELTA);
        assertEquals(373.15, TemperatureConverter.celsiusToKelvin(100.0), DELTA);
        assertEquals(233.15, TemperatureConverter.celsiusToKelvin(-40.0), DELTA);
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(0.0, TemperatureConverter.fahrenheitToCelsius(32.0), DELTA);
        assertEquals(100.0, TemperatureConverter.fahrenheitToCelsius(212.0), DELTA);
        assertEquals(-40.0, TemperatureConverter.fahrenheitToCelsius(-40.0), DELTA);
        assertEquals(37.0, TemperatureConverter.fahrenheitToCelsius(98.6), DELTA);
    }

    @Test
    void testFahrenheitToKelvin() {
        assertEquals(273.15, TemperatureConverter.fahrenheitToKelvin(32.0), DELTA);
        assertEquals(373.15, TemperatureConverter.fahrenheitToKelvin(212.0), DELTA);
        assertEquals(233.15, TemperatureConverter.fahrenheitToKelvin(-40.0), DELTA);
    }

    @Test
    void testKelvinToCelsius() {
        assertEquals(0.0, TemperatureConverter.kelvinToCelsius(273.15), DELTA);
        assertEquals(100.0, TemperatureConverter.kelvinToCelsius(373.15), DELTA);
        assertEquals(-273.15, TemperatureConverter.kelvinToCelsius(0.0), DELTA);
    }

    @Test
    void testKelvinToFahrenheit() {
        assertEquals(32.0, TemperatureConverter.kelvinToFahrenheit(273.15), DELTA);
        assertEquals(212.0, TemperatureConverter.kelvinToFahrenheit(373.15), DELTA);
        assertEquals(-40.0, TemperatureConverter.kelvinToFahrenheit(233.15), DELTA);
    }
}
