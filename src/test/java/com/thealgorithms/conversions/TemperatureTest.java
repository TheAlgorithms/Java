package com.thealgorithms.conversions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemperatureTest {
    Temperature temperature = new Temperature();

    @Test
    public void VerifyTemperatureConversionsZero() {
        assertEquals(32.0, temperature.CelsiusToFahrenheit(0.0), "VerifyTemperatureConversionsZero: Temperature from Celsius To Fahrenheit incorrect");
        assertEquals(273.15, temperature.CelsiusToKelvin(0.0), "VerifyTemperatureConversionsZero: Temperature from Celsius To Kelvin incorrect");
        assertEquals(-17.77, temperature.FahrenheitToCelsius(0.0), 0.01, "VerifyTemperatureConversionsZero: Temperature from Fahrenheit To Celsius incorrect");
        assertEquals(255.37, temperature.FahrenheitToKelvin(0.0), 0.01, "VerifyTemperatureConversionsZero: Temperature from Fahrenheit to Kelvin incorrect");
        assertEquals(-273.15, temperature.KelvinToCelsius(0.0), "VerifyTemperatureConversionsZero: Temperature from Kelvin to Fahrenheit incorrect");
        assertEquals(-459.67, temperature.KelvinToFahrenheit(0.0), 0.01, "VerifyTemperatureConversionsZero: Temperature from Kelvin to Fahrenheit incorrect");
    }

    @Test
    // At -40 degrees Fahrenheit and Celsius are the same
    public void VerifyTemperatureConversionsNeg40() {
        assertEquals(-40.0, temperature.CelsiusToFahrenheit(-40.0), "VerifyTemperatureConversionsNeg40: Temperature from Celsius To Fahrenheit incorrect");
        assertEquals(233.15, temperature.CelsiusToKelvin(-40.0), 0.01, "VerifyTemperatureConversionsNeg40: Temperature from Celsius To Kelvin incorrect");
        assertEquals(-40.0, temperature.FahrenheitToCelsius(-40.0), 0.01, "VerifyTemperatureConversionsNeg40: Temperature from Fahrenheit To Celsius incorrect");
        assertEquals(233.15, temperature.FahrenheitToKelvin(-40.0), 0.01, "VerifyTemperatureConversionsNeg40: Temperature from Fahrenheit to Kelvin incorrect");
        assertEquals(-313.15, temperature.KelvinToCelsius(-40.0), "VerifyTemperatureConversionsNeg40: Temperature from Kelvin to Fahrenheit incorrect");
        assertEquals(-531.67, temperature.KelvinToFahrenheit(-40.0), 0.01, "VerifyTemperatureConversionsNeg40: Temperature from Kelvin to Fahrenheit incorrect");
    }

    @Test
    public void VerifyTemperatureConversionsPlainValue() {
        assertEquals(33.8, temperature.CelsiusToFahrenheit(1.0), "VerifyTemperatureConversionsPlainValue: Temperature from Celsius To Fahrenheit incorrect");
        assertEquals(275.15, temperature.CelsiusToKelvin(2.0), "VerifyTemperatureConversionsPlainValue: Temperature from Celsius To Kelvin incorrect");
        assertEquals(-16.11, temperature.FahrenheitToCelsius(3.0), 0.01, "VerifyTemperatureConversionsPlainValue: Temperature from Fahrenheit To Celsius incorrect");
        assertEquals(257.59, temperature.FahrenheitToKelvin(4.0), 0.01, "VerifyTemperatureConversionsPlainValue:Temperature from Fahrenheit to Kelvin incorrect");
        assertEquals(-268.15, temperature.KelvinToCelsius(5.0), "VerifyTemperatureConversionsPlainValue:Temperature from Kelvin to Fahrenheit incorrect");
        assertEquals(-448.87, temperature.KelvinToFahrenheit(6.0), 0.01, "VerifyTemperatureConversionsPlainValue: Temperature from Kelvin to Fahrenheit incorrect");
    }
}
