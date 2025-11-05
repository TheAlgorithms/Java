package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TemperatureConverterTest {

    @Test
    public void testValidCelsiusToFahrenheit() {
        assertEquals(33.8, TemperatureConverter.celsiusToFahrenheit(1.0));
        assertEquals(34.718, TemperatureConverter.celsiusToFahrenheit(1.51));
        assertEquals(14.0, TemperatureConverter.celsiusToFahrenheit(-10.0));
        assertEquals("33.8°F", TemperatureConverter.celsiusToFahrenheit("1°C"));
        assertEquals("34.718°F", TemperatureConverter.celsiusToFahrenheit("1.51°C"));
        assertEquals("14.0°F", TemperatureConverter.celsiusToFahrenheit("-10.0°C"));
    }

    @Test
    public void testValidCelsiusToKevin() {
        assertEquals(274.15, TemperatureConverter.celsiusToKelvin(1.0));
        assertEquals(274.65999999999997, TemperatureConverter.celsiusToKelvin(1.51));
        assertEquals(263.15, TemperatureConverter.celsiusToKelvin(-10.0));
        assertEquals("274.15 K", TemperatureConverter.celsiusToKelvin("1°C"));
        assertEquals("274.65999999999997 K", TemperatureConverter.celsiusToKelvin("1.51°C"));
        assertEquals("263.15 K", TemperatureConverter.celsiusToKelvin("-10.0°C"));
    }

    @Test
    public void testValidFahrenheitToCelsius() {
        assertEquals(-17.22222222222222, TemperatureConverter.fahrenheitToCelsius(1.0));
        assertEquals(65.55555555555556, TemperatureConverter.fahrenheitToCelsius(150.0));
        assertEquals(-23.333333333333336, TemperatureConverter.fahrenheitToCelsius(-10.0));
        assertEquals("-17.22222222222222°C", TemperatureConverter.fahrenheitToCelsius("1°F"));
        assertEquals("65.55555555555556°C", TemperatureConverter.fahrenheitToCelsius("150.0°F"));
        assertEquals("-23.333333333333336°C", TemperatureConverter.fahrenheitToCelsius("-10.0°F"));
    }

    @Test
    public void testValidFahrenheitToKelvin() {
        assertEquals(255.92777777777775, TemperatureConverter.fahrenheitToKelvin(1.0));
        assertEquals(328.7055555555555, TemperatureConverter.fahrenheitToKelvin(132.0));
        assertEquals(249.81666666666663, TemperatureConverter.fahrenheitToKelvin(-10.0));
        assertEquals("255.92777777777775 K", TemperatureConverter.fahrenheitToKelvin("1°F"));
        assertEquals("328.7055555555555 K", TemperatureConverter.fahrenheitToKelvin("132°F"));
        assertEquals("249.81666666666663 K", TemperatureConverter.fahrenheitToKelvin("-10.0°F"));
    }

    @Test
    public void testValidKelvinToCelsius() {
        assertEquals(-272.15, TemperatureConverter.kelvinToCelsius(1.0));
        assertEquals(-141.14999999999998, TemperatureConverter.kelvinToCelsius(132.0));
        assertEquals(-263.15, TemperatureConverter.kelvinToCelsius(10.0));
        assertEquals("-272.15°C", TemperatureConverter.kelvinToCelsius("1.0 K"));
        assertEquals("-141.14999999999998°C", TemperatureConverter.kelvinToCelsius("132.0 K"));
        assertEquals("-263.15°C", TemperatureConverter.kelvinToCelsius("10.0 K"));
    }

    @Test
    public void testValidKelvinToFahrenheit() {
        assertEquals(-457.86999999999995, TemperatureConverter.kelvinToFahrenheit(1.0));
        assertEquals(-222.06999999999996, TemperatureConverter.kelvinToFahrenheit(132.0));
        assertEquals(-441.66999999999996, TemperatureConverter.kelvinToFahrenheit(10.0));
        assertEquals("-457.86999999999995°F", TemperatureConverter.kelvinToFahrenheit("1.0 K"));
        assertEquals("-222.06999999999996°F", TemperatureConverter.kelvinToFahrenheit("132.0 K"));
        assertEquals("-441.66999999999996°F", TemperatureConverter.kelvinToFahrenheit("10.0 K"));
    }

    @Test
    public void testAbsoluteZeroAndBelow() {
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.celsiusToFahrenheit(-300.0));
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.celsiusToKelvin("-300°C"));
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.fahrenheitToCelsius(-500.0));
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.fahrenheitToKelvin("-500°F"));
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.kelvinToCelsius(-1.0));
        assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.kelvinToFahrenheit("-1 K"));
    }
}
