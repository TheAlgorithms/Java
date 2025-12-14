package com.thealgorithms.conversions;

/**
 * A utility class to convert between different temperature units.
 *
 * <p>This class supports conversions between the following units:
 * <ul>
 *   <li>Celsius</li>
 *   <li>Fahrenheit</li>
 *   <li>Kelvin</li>
 * </ul>
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @author krishna-medapati (https://github.com/krishna-medapati)
 * @see <a href="https://en.wikipedia.org/wiki/Conversion_of_scales_of_temperature">Wikipedia: Temperature Conversion</a>
 */
public final class TemperatureConverter {

    private TemperatureConverter() {
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0 + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9.0 / 5.0 + 32.0;
    }
}
