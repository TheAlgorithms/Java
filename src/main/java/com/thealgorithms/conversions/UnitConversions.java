package com.thealgorithms.conversions;

import static java.util.Map.entry;

import java.util.Map;

/**
 * A utility class to perform unit conversions between different measurement systems.
 */
public final class UnitConversions {

    /**
     * Enum managing supported temperature scales to eliminate magic strings
     * and enforce structural type safety internally.
     */
    private enum TemperatureScale {
        CELSIUS("Celsius"),
        FAHRENHEIT("Fahrenheit"),
        KELVIN("Kelvin"),
        REAUMUR("Réaumur"),
        DELISLE("Delisle"),
        RANKINE("Rankine");

        private final String unitName;

        TemperatureScale(String unitName) {
            this.unitName = unitName;
        }

        public String getName() {
            return unitName;
        }
    }

    // Mathematical Constants for Conversions (Eliminating Magic Numbers)
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;

    private static final double CELSIUS_TO_FAHRENHEIT_SCALE = 9.0 / 5.0;
    private static final double CELSIUS_TO_FAHRENHEIT_OFFSET = 32.0;

    private static final double REAUMUR_TO_CELSIUS_SCALE = 5.0 / 4.0;

    private static final double DELISLE_TO_CELSIUS_SCALE = -2.0 / 3.0;
    private static final double DELISLE_TO_CELSIUS_OFFSET = 100.0;

    private static final double RANKINE_TO_KELVIN_SCALE = 5.0 / 9.0;

    /**
     * Preconfigured instance of UnitsConverter for temperature conversions.
     */
    public static final UnitsConverter TEMPERATURE = new UnitsConverter(Map.ofEntries(
            entry(
                    new UnitPair(TemperatureScale.KELVIN.getName(), TemperatureScale.CELSIUS.getName()),
                    new AffineConverter(1.0, ABSOLUTE_ZERO_CELSIUS)
            ),
            entry(
                    new UnitPair(TemperatureScale.CELSIUS.getName(), TemperatureScale.FAHRENHEIT.getName()),
                    new AffineConverter(CELSIUS_TO_FAHRENHEIT_SCALE, CELSIUS_TO_FAHRENHEIT_OFFSET)
            ),
            entry(
                    new UnitPair(TemperatureScale.REAUMUR.getName(), TemperatureScale.CELSIUS.getName()),
                    new AffineConverter(REAUMUR_TO_CELSIUS_SCALE, 0.0)
            ),
            entry(
                    new UnitPair(TemperatureScale.DELISLE.getName(), TemperatureScale.CELSIUS.getName()),
                    new AffineConverter(DELISLE_TO_CELSIUS_SCALE, DELISLE_TO_CELSIUS_OFFSET)
            ),
            entry(
                    new UnitPair(TemperatureScale.RANKINE.getName(), TemperatureScale.KELVIN.getName()),
                    new AffineConverter(RANKINE_TO_KELVIN_SCALE, 0.0)
            )
    ));

    private UnitConversions() {
        // Prevent instantiation
    }
}