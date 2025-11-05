package com.thealgorithms.conversions;

public class TemperatureConverter {

    private TemperatureConverter() {
    }

    // =============================================================
    // Celsius to Fahrenheit
    // =============================================================
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

    // =============================================================
    // Celsius to Kelvin
    // =============================================================
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

    // =============================================================
    // Fahrenheit to Celsius
    // =============================================================
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

    // =============================================================
    // Fahrenheit to Kelvin
    // =============================================================
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

    // =============================================================
    // Kelvin to Celsius
    // =============================================================
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

    // =============================================================
    // Kelvin to Fahrenheit
    // =============================================================
    public static Double kelvinToFahrenheit(Double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return 1.8 * (kelvin - 273) + 32;
    }

    public static String kelvinToFahrenheit(String kelvin) {
        String[] temp = kelvin.replace("K", "").trim().split(" ");
        double value = Double.parseDouble(temp[0]);
        if (value < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero (0 K).");
        }
        return (1.8 * (value - 273) + 32) + "°F";
    }

}
