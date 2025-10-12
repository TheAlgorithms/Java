package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link WusLine} class.
 */
class WusLineTest {

    @Test
    void testSimpleLineProducesPixels() {
        List<WusLine.Pixel> pixels = WusLine.drawLine(2, 2, 6, 4);
        assertFalse(pixels.isEmpty(), "Line should produce non-empty pixel list");
    }

    @Test
    void testEndpointsIncluded() {
        List<WusLine.Pixel> pixels = WusLine.drawLine(0, 0, 5, 3);
        boolean hasStart = pixels.stream().anyMatch(p -> p.point.equals(new java.awt.Point(0, 0)));
        boolean hasEnd = pixels.stream().anyMatch(p -> p.point.equals(new java.awt.Point(5, 3)));
        assertTrue(hasStart, "Start point should be represented in the pixel list");
        assertTrue(hasEnd, "End point should be represented in the pixel list");
    }

    @Test
    void testIntensityInRange() {
        List<WusLine.Pixel> pixels = WusLine.drawLine(1, 1, 8, 5);
        for (WusLine.Pixel pixel : pixels) {
            assertTrue(pixel.intensity >= 0.0 && pixel.intensity <= 1.0, "Intensity must be clamped between 0.0 and 1.0");
        }
    }

    @Test
    void testReversedEndpointsProducesSameLine() {
        List<WusLine.Pixel> forward = WusLine.drawLine(2, 2, 10, 5);
        List<WusLine.Pixel> backward = WusLine.drawLine(10, 5, 2, 2);

        // They should cover same coordinates (ignoring order)
        var forwardPoints = forward.stream().map(p -> p.point).collect(java.util.stream.Collectors.toSet());
        var backwardPoints = backward.stream().map(p -> p.point).collect(java.util.stream.Collectors.toSet());

        assertEquals(forwardPoints, backwardPoints, "Reversing endpoints should yield same line pixels");
    }

    @Test
    void testSteepLineHasProperCoverage() {
        // Steep line: Δy > Δx
        List<WusLine.Pixel> pixels = WusLine.drawLine(3, 2, 5, 10);
        assertFalse(pixels.isEmpty());
        // Expect increasing y values
        long increasing = 0;
        for (int i = 1; i < pixels.size(); i++) {
            if (pixels.get(i).point.y >= pixels.get(i - 1).point.y) {
                increasing++;
            }
        }
        assertTrue(increasing > pixels.size() / 2, "Steep line should have increasing y coordinates");
    }

    @Test
    void testZeroLengthLineUsesDefaultGradient() {
        // same start and end -> dx == 0 -> gradient should take the (dx == 0) ? 1.0 branch
        List<WusLine.Pixel> pixels = WusLine.drawLine(3, 3, 3, 3);

        // sanity checks: we produced pixels and the exact point is present
        assertFalse(pixels.isEmpty(), "Zero-length line should produce at least one pixel");
        assertTrue(pixels.stream().anyMatch(p -> p.point.equals(new java.awt.Point(3, 3))), "Pixel list should include the single-point coordinate (3,3)");
    }

    @Test
    void testHorizontalLineIntensityStable() {
        List<WusLine.Pixel> pixels = WusLine.drawLine(1, 5, 8, 5);

        // For each x, take the max intensity among pixels with that x (the visible intensity for the column)
        java.util.Map<Integer, Double> maxIntensityByX = pixels.stream()
                                                             .collect(java.util.stream.Collectors.groupingBy(p -> p.point.x, java.util.stream.Collectors.mapping(p -> p.intensity, java.util.stream.Collectors.maxBy(Double::compareTo))))
                                                             .entrySet()
                                                             .stream()
                                                             .collect(java.util.stream.Collectors.toMap(java.util.Map.Entry::getKey, e -> e.getValue().orElse(0.0)));

        double avgMaxIntensity = maxIntensityByX.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        assertTrue(avgMaxIntensity > 0.5, "Average of the maximum per-x intensities should be > 0.5 for a horizontal line");
    }
}
