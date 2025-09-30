package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class TimeConverterTest {

    @ParameterizedTest(name = "{0} {1} -> {2} {3}")
    @CsvSource({"60, seconds, minutes, 1", "120, seconds, minutes, 2", "2, minutes, seconds, 120", "2, hours, minutes, 120", "1, days, hours, 24", "1, weeks, days, 7", "1, months, days, 30.438", "1, years, days, 365.25", "3600, seconds, hours, 1", "86400, seconds, days, 1",
        "604800, seconds, weeks, 1", "31557600, seconds, years, 1"})
    void
    testValidConversions(double value, String from, String to, double expected) {
        assertEquals(expected, TimeConverter.convertTime(value, from, to));
    }

    @Test
    @DisplayName("Zero conversion returns zero")
    void testZeroValue() {
        assertEquals(0.0, TimeConverter.convertTime(0, "seconds", "hours"));
    }

    @Test
    @DisplayName("Same-unit conversion returns original value")
    void testSameUnitConversion() {
        assertEquals(123.456, TimeConverter.convertTime(123.456, "minutes", "minutes"));
    }

    @Test
    @DisplayName("Negative value throws exception")
    void testNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> TimeConverter.convertTime(-5, "seconds", "minutes"));
    }

    @ParameterizedTest
    @CsvSource({"lightyears, seconds", "minutes, centuries"})
    void testInvalidUnits(String from, String to) {
        assertThrows(IllegalArgumentException.class, () -> TimeConverter.convertTime(10, from, to));
    }

    @Test
    @DisplayName("Null unit throws exception")
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> TimeConverter.convertTime(10, null, "seconds"));

        assertThrows(IllegalArgumentException.class, () -> TimeConverter.convertTime(10, "minutes", null));

        assertThrows(IllegalArgumentException.class, () -> TimeConverter.convertTime(10, null, null));
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> roundTripCases() {
        return Stream.of(org.junit.jupiter.params.provider.Arguments.of(1.0, "hours", "minutes"), org.junit.jupiter.params.provider.Arguments.of(2.5, "days", "hours"), org.junit.jupiter.params.provider.Arguments.of(1000, "seconds", "minutes"));
    }

    @ParameterizedTest
    @MethodSource("roundTripCases")
    @DisplayName("Round-trip conversion returns original value")
    void testRoundTripConversion(double value, String from, String to) {
        double converted = TimeConverter.convertTime(value, from, to);
        double roundTrip = TimeConverter.convertTime(converted, to, from);
        assertEquals(Math.round(value * 1000.0) / 1000.0, Math.round(roundTrip * 1000.0) / 1000.0, 0.05);
    }
}
