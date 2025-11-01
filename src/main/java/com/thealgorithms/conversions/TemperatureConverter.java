package com.thealgorithms.conversions;

/**
 * A utility class for converting temperatures between
 * Celsius, Fahrenheit, and Kelvin scales.
 *
 * This class provides static methods, so it can be used directly
 * without creating an instance.
 */
public class TemperatureConverter {

    /**
     * Converts temperature from Celsius to Fahrenheit.
     *
     * Formula: (°C × 9/5) + 32 = °F
     *
//     * @param celsius temperature in Celsius
     * @return equivalent temperature in Fahrenheit
     */
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0) / 5.0 + 32.0;
    }

    /**
     * Converts temperature from Celsius to Kelvin.
     *
     * Formula: °C + 273.15 = K
     *
//     * @param celsius temperature in Celsius
     * @return equivalent temperature in Kelvin
     */
    static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    /**
     * Converts temperature from Fahrenheit to Celsius.
     *
     * Formula: (°F − 32) × 5/9 = °C
     *
//     * @param fahrenheit temperature in Fahrenheit
     * @return equivalent temperature in Celsius
     */
    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }

    /**
     * Converts temperature from Fahrenheit to Kelvin.
     *
     * Formula: (°F − 32) × 5/9 + 273.15 = K
     *
//     * @param fahrenheit temperature in Fahrenheit
     * @return equivalent temperature in Kelvin
     */
    static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0 + 273.15;
    }

    /**
     * Converts temperature from Kelvin to Celsius.
     *
     * Formula: K − 273.15 = °C
     *
//     * @param kelvin temperature in Kelvin
     * @return equivalent temperature in Celsius
     */
    static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    /**
     * Converts temperature from Kelvin to Fahrenheit.
     *
     * Formula: (K − 273.15) × 9/5 + 32 = °F
     *
//     * @param kelvin temperature in Kelvin
     * @return equivalent temperature in Fahrenheit
     */
    static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9.0 / 5.0 + 32.0;
    }
}
