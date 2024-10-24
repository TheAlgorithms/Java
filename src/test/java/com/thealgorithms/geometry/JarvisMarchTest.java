package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for the {@link JarvisMarch} class, which implements the Jarvis March algorithm
 * for computing the convex hull of a set of points.
 */
class JarvisMarchTest {

    /**
     * Tests the equals method of the Point class with an object of a different type.
     * It verifies that a Point instance is not equal to a non-Point object.
     */
    @Test
    void testEqualsMethodWithDifferentType() {
        JarvisMarch.Point pointA = new JarvisMarch.Point(1, 1);
        String notAPoint = "I am not a Point";

        // Assert that pointA is not equal to a String object
        assertNotEquals(pointA, notAPoint);
    }

    /**
     * Provides test cases for the convex hull computation.
     * Each case consists of a list of input points and the expected convex hull result.
     *
     * @return a stream of arguments containing input points and expected hull points
     */
    private static Stream<Arguments> providePointsForConvexHull() {
        return Stream.of(
            // Test case 1: Simple triangle
            Arguments.of(Arrays.asList(new JarvisMarch.Point(0, 0), new JarvisMarch.Point(1, 1), new JarvisMarch.Point(1, 0)), Arrays.asList(new JarvisMarch.Point(0, 0), new JarvisMarch.Point(1, 1), new JarvisMarch.Point(1, 0))),
            // Test case 2: Points with one point inside the hull
            Arguments.of(Arrays.asList(new JarvisMarch.Point(1, 1), new JarvisMarch.Point(0, 0), new JarvisMarch.Point(2, 2), new JarvisMarch.Point(3, 1), new JarvisMarch.Point(2, 0)),
                Arrays.asList(new JarvisMarch.Point(0, 0), new JarvisMarch.Point(2, 2), new JarvisMarch.Point(3, 1), new JarvisMarch.Point(2, 0))),
            // Test case 3: Single point (no hull)
            Arguments.of(Arrays.asList(new JarvisMarch.Point(0, 0)), Arrays.asList()));
    }

    /**
     * Parameterized test for the jarvisMarch method.
     * It checks if the actual convex hull computed matches the expected hull
     * for various sets of input points.
     *
     * @param inputPoints a list of points to compute the convex hull from
     * @param expectedHull a list of expected points forming the convex hull
     */
    @ParameterizedTest
    @MethodSource("providePointsForConvexHull")
    void testConvexHull(List<JarvisMarch.Point> inputPoints, List<JarvisMarch.Point> expectedHull) {
        List<JarvisMarch.Point> actualHull = JarvisMarch.jarvisMarch(inputPoints);

        // Assert that the size of actual hull matches the expected hull size
        assertEquals(expectedHull.size(), actualHull.size());

        // Assert that each point in the expected hull matches the corresponding point in the actual hull
        for (int i = 0; i < expectedHull.size(); i++) {
            assertEquals(expectedHull.get(i), actualHull.get(i));
        }
    }
}
