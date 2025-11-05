package com.thealgorithms.conversions;

/**
 * Utility class for converting temperatures between Celsius, Fahrenheit, and Kelvin.
 *
 * <p>Formulas reference:
 * <a href="https://en.wikipedia.org/wiki/Conversion_of_scales_of_temperature">...</a>
 * <p>Note: Some of the formulas presented on the wikipedia page are not correct
 *
 * <p>Examples:
 * <ul>
 *   <li>0 °C = 32 °F = 273.15 K</li>
 *   <li>-40 °C = -40 °F</li>
 * </ul>
 */
public class TemperatureConverter {

    private TemperatureConverter() {
    }

    /**
     * Converts Celsius to Fahrenheit.
     * Formula: °C × 9/5 + 32
     */
    public static Double celsiusToFahrenheit(Double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Temperature below absolute zero (-273.15°C).");
        }
        return (celsius * 1.8) + 32;
    }

    public static String celsiusToFahrenheit(String celsius) {
        String[] temp = celsius.replace("°C", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < -273.15) {
            throw new IllegalArgumentException("Temperature below absolute zero (-273.15°C).");
        }
        return ((value * 1.8) + 32) + "°F";
    }

    /**
     * Converts Celsius to Kelvin.
     * Formula: °C + 273.15
     */
    public static Double celsiusToKelvin(Double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Temperature below absolute zero (-273.15°C).");
        }
        return celsius + 273.15;
    }

    public static String celsiusToKelvin(String celsius) {
        String[] temp = celsius.replace("°C", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < -273.15) {
            throw new IllegalArgumentException("Temperature below absolute zero (-273.15°C).");
        }
        return (value + 273.15) + " K";
    }

    /**
     * Converts: Fahrenheit to Celsius.
     * Formula: (°F - 32) * 5/9
     */
    public static Double fahrenheitToCelsius(Double fahrenheit) {
        if (fahrenheit < -459.67) {
            throw new IllegalArgumentException("Temperature below absolute zero (-459.67°F).");
        }
        return (fahrenheit - 32) * (5.0 / 9.0);
    }

    public static String fahrenheitToCelsius(String fahrenheit) {
        String[] temp = fahrenheit.replace("°F", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < -459.67) {
            throw new IllegalArgumentException("Temperature below absolute zero (-459.67°F).");
        }
        return ((value - 32) * (5.0 / 9.0)) + "°C";
    }

    /**
     * Converts: Fahrenheit to Kelvin.
     * Formula: (°F - 32) * 5/9 + 273.15
     */
    public static Double fahrenheitToKelvin(Double fahrenheit) {
        if (fahrenheit < -459.67) {
            throw new IllegalArgumentException("Temperature below absolute zero (-459.67°F).");
        }
        return (fahrenheit - 32) * (5.0 / 9.0) + 273.15;
    }

    public static String fahrenheitToKelvin(String fahrenheit) {
        String[] temp = fahrenheit.replace("°F", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < -459.67) {
            throw new IllegalArgumentException("Temperature below absolute zero (-459.67°F).");
        }
        return ((value - 32) * (5.0 / 9.0) + 273.15) + " K";
    }

    /**
     * Converts: Kelvin to Celsius.
     * Formula: K - 273.15
     */
    public static Double kelvinToCelsius(Double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return kelvin - 273.15;
    }

    public static String kelvinToCelsius(String kelvin) {
        String[] temp = kelvin.replace("K", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return (value - 273.15) + "°C";
    }

    /**
     * Converts: Kelvin to Fahrenheit.
     * Formula: (K - 273.15) * 9/5 + 32
     */
    public static Double kelvinToFahrenheit(Double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return 1.8 * (kelvin - 273.15) + 32;
    }

    public static String kelvinToFahrenheit(String kelvin) {
        String[] temp = kelvin.replace("K", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return (1.8 * (value - 273.15) + 32) + "°F";
    }

}
