package com.thealgorithms.conversions;

/**
 * Convets the temperatures from user to Celsius/Fahrenheit/Kelvin
 *
 * @author Tharun S.M.
 */

public class TemperatureConversion {
    private static final double CELSIUS_SLOPE = 1.0;
    private static final double CELSIUS_INTERCEPT = 0.0;
    private static final double FAHRENHEIT_SLOPE = 1.8;
    private static final double FAHRENHEIT_INTERCEPT = 32.0;
    private static final double KELVIN_SLOPE = 1.0;
    private static final double KELVIN_INTERCEPT = 273.15;

    /**
     * This function convert current temperature type to required temperature type
     *
     * @param temperature double, fromType String & toType String
     * @return double
     */
    public static double convertTemperature(double temperature, String fromType, String toType) {

        if (fromType.equalsIgnoreCase(toType)) {
            return temperature;
        }

        double[] slopeAndIntercept = getSlopeAndIntercept(fromType, toType);

        double fromSlope = slopeAndIntercept[0];
        double fromIntercept = slopeAndIntercept[1];
        double toSlope = slopeAndIntercept[2];
        double toIntercept = slopeAndIntercept[3];
        // converting to celsius temperature first, then to our required temperature
        double celsiusTemperature = (temperature - fromIntercept) / fromSlope;
        return celsiusTemperature * toSlope + toIntercept;
    }

    /**
     * This function returns Slopes and Intercepts of from and to temperature types
     *
     * @param fromType String & toType String
     * @return double array of Slopes and Intercepts
     */
    public static double[] getSlopeAndIntercept(String fromType, String toType) {

        double[] slopeAndIntercept = new double[4];
        if (fromType.equalsIgnoreCase("Celsius") && toType.equalsIgnoreCase("Fahrenheit")) {
            slopeAndIntercept[0] = CELSIUS_SLOPE;
            slopeAndIntercept[1] = CELSIUS_INTERCEPT;
            slopeAndIntercept[2] = FAHRENHEIT_SLOPE;
            slopeAndIntercept[3] = FAHRENHEIT_INTERCEPT;
            return slopeAndIntercept;
        } else if (fromType.equalsIgnoreCase("Fahrenheit") && toType.equalsIgnoreCase("Celsius")) {
            slopeAndIntercept[0] = FAHRENHEIT_SLOPE;
            slopeAndIntercept[1] = FAHRENHEIT_INTERCEPT;
            slopeAndIntercept[2] = CELSIUS_SLOPE;
            slopeAndIntercept[3] = CELSIUS_INTERCEPT;
            return slopeAndIntercept;
        } else if (fromType.equalsIgnoreCase("Celsius") && toType.equalsIgnoreCase("Kelvin")) {
            slopeAndIntercept[0] = CELSIUS_SLOPE;
            slopeAndIntercept[1] = CELSIUS_INTERCEPT;
            slopeAndIntercept[2] = KELVIN_SLOPE;
            slopeAndIntercept[3] = KELVIN_INTERCEPT;
            return slopeAndIntercept;
        } else if (fromType.equalsIgnoreCase("Kelvin") && toType.equalsIgnoreCase("Celsius")) {
            slopeAndIntercept[0] = KELVIN_SLOPE;
            slopeAndIntercept[1] = KELVIN_INTERCEPT;
            slopeAndIntercept[2] = CELSIUS_SLOPE;
            slopeAndIntercept[3] = CELSIUS_INTERCEPT;
            return slopeAndIntercept;
        } else if (fromType.equalsIgnoreCase("Kelvin") && toType.equalsIgnoreCase("Fahrenheit")) {
            slopeAndIntercept[0] = KELVIN_SLOPE;
            slopeAndIntercept[1] = KELVIN_INTERCEPT;
            slopeAndIntercept[2] = FAHRENHEIT_SLOPE;
            slopeAndIntercept[3] = FAHRENHEIT_INTERCEPT;
            return slopeAndIntercept;
        } else if (fromType.equalsIgnoreCase("Fahrenheit") && toType.equalsIgnoreCase("Kelvin")) {
            slopeAndIntercept[0] = FAHRENHEIT_SLOPE;
            slopeAndIntercept[1] = FAHRENHEIT_INTERCEPT;
            slopeAndIntercept[2] = KELVIN_SLOPE;
            slopeAndIntercept[3] = KELVIN_INTERCEPT;
            return slopeAndIntercept;
        } else {
            throw new IllegalArgumentException("Unsupported temperature conversion");
        }
    }

    public static void main(String[] args) {

        double temperature = 100;
        String fromType = "Celsius";
        String toType = "Fahrenheit";

        double convertedTemperature = convertTemperature(temperature, fromType, toType);

        System.out.println("Converted temperature: " + convertedTemperature);
    }
}
