package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for the Haversine formula implementation.
 * This class uses parameterized tests to verify the distance calculation
 * between various geographical coordinates.
 */
final class HaversineTest {

    // A small tolerance for comparing double values, since floating-point
    // arithmetic is not always exact. A 1km tolerance is reasonable for these distances.
    private static final double DELTA = 1.0;

    /**
     * Provides test cases for the haversine distance calculation.
     * Each argument contains: lat1, lon1, lat2, lon2, and the expected distance in kilometers.
     *
     * @return a stream of arguments for the parameterized test.
     */
    static Stream<Arguments> haversineTestProvider() {
        return Stream.of(
            // Case 1: Distance between Paris, France and Tokyo, Japan
            Arguments.of(48.8566, 2.3522, 35.6895, 139.6917, 9712.0),

            // Case 2: Distance between New York, USA and London, UK
            Arguments.of(40.7128, -74.0060, 51.5074, -0.1278, 5570.0),

            // Case 3: Zero distance (same point)
            Arguments.of(52.5200, 13.4050, 52.5200, 13.4050, 0.0),

            // Case 4: Antipodal points (opposite sides of the Earth)
            // Should be approximately half the Earth's circumference (PI * radius)
            Arguments.of(0.0, 0.0, 0.0, 180.0, 20015.0));
    }

    /**
     * Tests the haversine method with various sets of coordinates.
     *
     * @param lat1             Latitude of the first point.
     * @param lon1             Longitude of the first point.
     * @param lat2             Latitude of the second point.
     * @param lon2             Longitude of the second point.
     * @param expectedDistance The expected distance in kilometers.
     */
    @ParameterizedTest
    @MethodSource("haversineTestProvider")
    @DisplayName("Test Haversine distance calculation for various coordinates")
    void testHaversine(double lat1, double lon1, double lat2, double lon2, double expectedDistance) {
        double actualDistance = Haversine.haversine(lat1, lon1, lat2, lon2);
        assertEquals(expectedDistance, actualDistance, DELTA);
    }
}
