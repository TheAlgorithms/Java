package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZellersCongruenceTest {

    static Stream<Arguments> validDates() {
        return Stream.of(Arguments.of("01-01-2000", "Saturday"), Arguments.of("12-25-2021", "Saturday"), Arguments.of("07-04-1776", "Thursday"), Arguments.of("02-29-2020", "Saturday"), Arguments.of("03-01-1900", "Thursday"), Arguments.of("03/01/1900", "Thursday"));
    }

    static Stream<Arguments> invalidDates() {
        return Stream.of(Arguments.of("13-01-2000", "Month must be between 1 and 12."), Arguments.of("02-30-2020", "Invalid date."), Arguments.of("00-15-2020", "Month must be between 1 and 12."), Arguments.of("01-01-0000", "Year must be between 46 and 8499."),
            Arguments.of("01/01/200", "Input date must be 10 characters long in the format MM-DD-YYYY or MM/DD/YYYY."), Arguments.of("01@01>2000", "Date separator must be '-' or '/'."), Arguments.of("aa-01-1900", "Invalid numeric part: aa"),
            Arguments.of(null, "Input date must be 10 characters long in the format MM-DD-YYYY or MM/DD/YYYY."));
    }

    @ParameterizedTest
    @MethodSource("validDates")
    void testValidDates(String inputDate, String expectedDay) {
        String result = ZellersCongruence.calculateDay(inputDate);
        assertEquals("The date " + inputDate + " falls on a " + expectedDay + ".", result);
    }

    @ParameterizedTest
    @MethodSource("invalidDates")
    void testInvalidDates(String inputDate, String expectedErrorMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ZellersCongruence.calculateDay(inputDate));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
