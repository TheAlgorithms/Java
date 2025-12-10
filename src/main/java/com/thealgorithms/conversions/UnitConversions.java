package com.thealgorithms.conversions;

import static java.util.Map.entry;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A utility class to perform unit conversions between different measurement systems.
 *
 * <p>Currently, the class supports temperature conversions between several scales:
 * Celsius, Fahrenheit, Kelvin, Réaumur, Delisle, and Rankine.
 *
 * <h2>Example Usage</h2>
 * <pre>
 *   double result = UnitConversions.TEMPERATURE.convert("Celsius", "Fahrenheit", 100.0);
 *   // Output: 212.0 (Celsius to Fahrenheit conversion of 100°C)
 * </pre>
 *
 * <p>This class makes use of an {@link UnitsConverter} that handles the conversion logic
 * based on predefined affine transformations. These transformations include scaling factors
 * and offsets for temperature conversions.
 *
 * <h2>Temperature Scales Supported</h2>
 * <ul>
 *   <li>Celsius</li>
 *   <li>Fahrenheit</li>
 *   <li>Kelvin</li>
 *   <li>Réaumur</li>
 *   <li>Delisle</li>
 *   <li>Rankine</li>
 * </ul>
 */
public final class UnitConversions {
    private UnitConversions() {
    }

    /**
     * A preconfigured instance of {@link UnitsConverter} for temperature conversions.
     * The converter handles conversions between the following temperature units:
     * <ul>
     *   <li>Kelvin to Celsius</li>
     *   <li>Celsius to Fahrenheit</li>
     *   <li>Réaumur to Celsius</li>
     *   <li>Delisle to Celsius</li>
     *   <li>Rankine to Kelvin</li>
     * </ul>
     */
    public static final UnitsConverter TEMPERATURE = new UnitsConverter(Map.ofEntries(entry(Pair.of("Kelvin", "Celsius"), new AffineConverter(1.0, -273.15)), entry(Pair.of("Celsius", "Fahrenheit"), new AffineConverter(9.0 / 5.0, 32.0)),
        entry(Pair.of("Réaumur", "Celsius"), new AffineConverter(5.0 / 4.0, 0.0)), entry(Pair.of("Delisle", "Celsius"), new AffineConverter(-2.0 / 3.0, 100.0)), entry(Pair.of("Rankine", "Kelvin"), new AffineConverter(5.0 / 9.0, 0.0))));
}
