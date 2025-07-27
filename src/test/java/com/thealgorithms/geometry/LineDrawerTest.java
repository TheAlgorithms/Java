package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * Unit tests for the LineDrawer using BresenhamLineStrategy.
 */
        class LineDrawerTest {

    /**
     * Provides test data for drawing lines using the Bresenham algorithm.
     */
    static Stream<Arguments> linePointsProvider() {
        return Stream.of(Arguments.of(0, 0, 5, 5, List.of(new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5))), Arguments.of(0, 0, 5, 0, List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0))),
            Arguments.of(0, 0, 0, 5, List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(0, 4), new Point(0, 5))), Arguments.of(-2, -2, -5, -5, List.of(new Point(-2, -2), new Point(-3, -3), new Point(-4, -4), new Point(-5, -5))),
            Arguments.of(-1, -1, 2, 2, List.of(new Point(-1, -1), new Point(0, 0), new Point(1, 1), new Point(2, 2))), Arguments.of(2, -1, -1, -4, List.of(new Point(2, -1), new Point(1, -2), new Point(0, -3), new Point(-1, -4))));
    }

    /**
     * Tests line drawing using the BresenhamLineStrategy through LineDrawer.
     */
    @ParameterizedTest
    @MethodSource("linePointsProvider")
    void testLineDrawingWithBresenhamStrategy(int x0, int y0, int x1, int y1, Collection<Point> expected) {
        LineDrawingStrategy strategy = new BresenhamLineStrategy();
        LineDrawer drawer = new LineDrawer(strategy);

        List<Point> actual = drawer.drawLine(x0, y0, x1, y1);

        Assertions.assertEquals(expected.size(), actual.size(), "Point count mismatch.");
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected), "Generated points do not match expected points.");
    }

    /**
     * Demonstrates dynamic strategy switching.
     */
    @ParameterizedTest
    @MethodSource("linePointsProvider")
    void testSetStrategyDynamicSwitch(int x0, int y0, int x1, int y1, Collection<Point> expected) {
        LineDrawer drawer = new LineDrawer(new BresenhamLineStrategy());

        // Simulate switching strategy at runtime (e.g., in future: drawer.setStrategy(new DdaLineStrategy()))
        drawer.setStrategy(new BresenhamLineStrategy()); // No-op here but shows extensibility

        List<Point> actual = drawer.drawLine(x0, y0, x1, y1);

        Assertions.assertEquals(expected.size(), actual.size(), "Point count mismatch after strategy switch.");
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected), "Generated points do not match expected points after strategy switch.");
    }
}
