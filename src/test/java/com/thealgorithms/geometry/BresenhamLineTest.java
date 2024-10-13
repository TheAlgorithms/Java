package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The {@code BresenhamLineTest} class contains unit tests for the {@code BresenhamLine} class.
 * It verifies the correctness of the findLine method, which generates a list of points
 * representing a straight line between two specified endpoints.
 *
 * <p>This test class uses JUnit 5 for testing and covers various scenarios including horizontal,
 * vertical, and diagonal lines, as well as lines with negative coordinates.</p>
 */
class BresenhamLineTest {

    // Instance of BresenhamLine to be tested
    private final BresenhamLine bresenhamLine = new BresenhamLine();

    /**
     * Tests the generation of a horizontal line from (0, 0) to (5, 0).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testHorizontalLine() {
        List<Point> line = bresenhamLine.findLine(0, 0, 5, 0);
        List<Point> expected = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0));
        assertEquals(expected, line);
    }

    /**
     * Tests the generation of a vertical line from (0, 0) to (0, 5).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testVerticalLine() {
        List<Point> line = bresenhamLine.findLine(0, 0, 0, 5);
        List<Point> expected = List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(0, 4), new Point(0, 5));
        assertEquals(expected, line);
    }

    /**
     * Tests the generation of a diagonal line from (0, 0) to (5, 5).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testDiagonalLine() {
        List<Point> line = bresenhamLine.findLine(0, 0, 5, 5);
        List<Point> expected = List.of(new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5));
        assertEquals(expected, line);
    }

    /**
     * Tests the generation of a diagonal line with a negative slope from (5, 5) to (0, 0).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testNegativeSlopeDiagonal() {
        List<Point> line = bresenhamLine.findLine(5, 5, 0, 0);
        List<Point> expected = List.of(new Point(5, 5), new Point(4, 4), new Point(3, 3), new Point(2, 2), new Point(1, 1), new Point(0, 0));
        assertEquals(expected, line);
    }

    /**
     * Tests the generation of a steep vertical line from (1, 1) to (1, 4).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testSteepDiagonal() {
        List<Point> line = bresenhamLine.findLine(1, 1, 1, 4);
        List<Point> expected = List.of(new Point(1, 1), new Point(1, 2), new Point(1, 3), new Point(1, 4));
        assertEquals(expected, line);
    }

    /**
     * Tests the generation of a diagonal line with negative coordinates from (-3,-3) to (-1,-1).
     * Asserts that the generated line matches the expected list of points.
     */
    @Test
    void testMixedCoordinates() {
        List<Point> line = bresenhamLine.findLine(-3, -3, -1, -1);
        List<Point> expected = List.of(new Point(-3, -3), new Point(-2, -2), new Point(-1, -1));
        assertEquals(expected, line);
    }
}
