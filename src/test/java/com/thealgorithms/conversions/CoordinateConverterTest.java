package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoordinateConverterTest {

    @ParameterizedTest
    @CsvSource({"0, 0, 0, 0", "1, 0, 1, 0", "0, 1, 1, 90", "-1, 0, 1, 180", "0, -1, 1, -90", "3, 4, 5, 53.13010235415599"})
    void testCartesianToPolar(double x, double y, double expectedR, double expectedTheta) {
        assertArrayEquals(new double[] {expectedR, expectedTheta}, CoordinateConverter.cartesianToPolar(x, y), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({"1, 0, 1, 0", "1, 90, 0, 1", "1, 180, -1, 0", "1, -90, 0, -1", "5, 53.13010235415599, 3, 4"})
    void testPolarToCartesian(double r, double theta, double expectedX, double expectedY) {
        assertArrayEquals(new double[] {expectedX, expectedY}, CoordinateConverter.polarToCartesian(r, theta), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({"NaN, 1", "1, NaN", "Infinity, 1", "1, Infinity", "-Infinity, 1", "1, -Infinity"})
    void testCartesianToPolarInvalidInputs(double x, double y) {
        assertThrows(IllegalArgumentException.class, () -> CoordinateConverter.cartesianToPolar(x, y));
    }

    @ParameterizedTest
    @CsvSource({"-1, 0", "1, NaN", "1, Infinity", "1, -Infinity"})
    void testPolarToCartesianInvalidInputs(double r, double theta) {
        assertThrows(IllegalArgumentException.class, () -> CoordinateConverter.polarToCartesian(r, theta));
    }
}
