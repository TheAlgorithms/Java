package com.thealgorithms.conversions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UnitConversionsTest {
    private static void addData(Stream.Builder<Arguments> builder, Map<String, Double> values) {
        for (final var first : values.entrySet()) {
            for (final var second : values.entrySet()) {
                if (!first.getKey().equals(second.getKey())) {
                    builder.add(Arguments.of(first.getKey(), second.getKey(), first.getValue(), second.getValue()));
                }
            }
        }
    }

    private static Stream<Arguments> temperatureData() {
        final Map<String, Double> boilingPointOfWater = Map.ofEntries(entry("Celsius", 99.9839), entry("Fahrenheit", 211.97102), entry("Kelvin", 373.1339), entry("Réaumur", 79.98712), entry("Delisle", 0.02415), entry("Rankine", 671.64102));

        final Map<String, Double> freezingPointOfWater = Map.ofEntries(entry("Celsius", 0.0), entry("Fahrenheit", 32.0), entry("Kelvin", 273.15), entry("Réaumur", 0.0), entry("Delisle", 150.0), entry("Rankine", 491.67));

        Stream.Builder<Arguments> builder = Stream.builder();
        addData(builder, boilingPointOfWater);
        addData(builder, freezingPointOfWater);
        return builder.build();
    }

    @ParameterizedTest
    @MethodSource("temperatureData")
    void testTemperature(String inputUnit, String outputUnit, double value, double expected) {
        final double result = UnitConversions.TEMPERATURE.convert(inputUnit, outputUnit, value);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void testTemperatureUnits() {
        final Set<String> expectedUnits = Set.of("Celsius", "Fahrenheit", "Kelvin", "Réaumur", "Rankine", "Delisle");
        assertEquals(expectedUnits, UnitConversions.TEMPERATURE.availableUnits());
    }
}
