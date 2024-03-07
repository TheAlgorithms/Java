package com.thealgorithms.conversions;

/**
 * Converts temperature from one scale to another.
 * All conversions were accessed from Google search page.
 *
 * @author Mark Myers
 */
public class Temperature {

    // For non-scientific cases these approximations are accurate enough.
    double nineDivideByFive = (double) 9 / 5;
    double fiveDivideByNine = (double) 5 / 9;

    /**
     * Converts from Celsius to Fahrenheit scales
     *
     * @param Celsius: the temperature in Celsius
     * @return temperature in Fahrenheit
     */
    double CelsiusToFahrenheit(double Celsius) {
        return (Celsius * nineDivideByFive) + 32;
    }

    /**
     * Converts from Fahrenheit to Kelvin scales
     *
     * @param Celsius: the temperature in Celsius
     * @return temperature in Kelvin
     */
    double CelsiusToKelvin(double Celsius) {
        return Celsius + 273.15;
    }

    /**
     * Converts from Fahrenheit to Celsius scales
     *
     * @param Fahrenheit: the temperature in Fahrenheit
     * @return temperature in Celsius
     */
    double FahrenheitToCelsius(double Fahrenheit) {
        return ((Fahrenheit - 32) * fiveDivideByNine);
    }

    /**
     * Converts from Fahrenheit to Kelvin scales
     *
     * @param Fahrenheit: the temperature in Fahrenheit
     * @return temperature in Kelvin
     */
    double FahrenheitToKelvin(double Fahrenheit) {
        return ((Fahrenheit - 32) * fiveDivideByNine + 273.15);
    }

    /**
     * Converts from Kelvin to Fahrenheit scales
     *
     * @param Kelvin: the temperature in Kelvin
     * @return temperature in Celsius
     */
    double KelvinToCelsius(double Kelvin) {
        return Kelvin - 273.15;
    }

    /**
     * Converts from Kelvin to Fahrenheit scales
     *
     * @param Kelvin: the temperature in Kelvin
     * @return temperature in Kelvin
     */
    double KelvinToFahrenheit(double Kelvin) {
        return ((Kelvin - 273.15) * nineDivideByFive + 32);
    }

}