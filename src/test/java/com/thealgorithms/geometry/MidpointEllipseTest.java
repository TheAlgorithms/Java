package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * The {@code MidpointEllipseTest} class contains unit tests for the
 * {@code MidpointEllipse} class, specifically testing the
 * {@code drawEllipse} method.
 *
 * <p>This class uses parameterized tests to validate the output of
 * Midpoint Ellipse algorithm for various input points.</p>
 */
class MidpointEllipseTest {

    /**
     * Provides test cases for the drawEllipse method.
     * Each argument contains: centerX, centerY, a, b, and expected points.
     *
     * @return a stream of arguments for parameterized testing
     */
    static Stream<Arguments> ellipseTestProvider() {
        return Stream.of(
            Arguments.of(0, 0, 5, 3, new int[][] {{0, 3}, {0, 3}, {0, -3}, {0, -3}, {1, 3}, {-1, 3}, {1, -3}, {-1, -3}, {2, 3}, {-2, 3}, {2, -3}, {-2, -3}, {3, 2}, {-3, 2}, {3, -2}, {-3, -2}, {4, 2}, {-4, 2}, {4, -2}, {-4, -2}, {5, 1}, {-5, 1}, {5, -1}, {-5, -1}, {5, 0}, {-5, 0}, {5, 0}, {-5, 0}}),
            Arguments.of(0, 0, 0, 5,
                new int[][] {
                    {0, -5}, {0, -4}, {0, -3}, {0, -2}, {0, -1}, {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5} // Only vertical line points and center
                }),
            Arguments.of(0, 0, 5, 0,
                new int[][] {
                    {-5, 0}, {-4, 0}, {-3, 0}, {-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0} // Only horizontal line points and center
                }),
            Arguments.of(0, 0, 0, 0,
                new int[][] {
                    {0, 0} // Only center point
                }),
            Arguments.of(0, 0, 4, 4,
                new int[][] {
                    {0, 4},
                    {0, 4},
                    {0, -4},
                    {0, -4},
                    {1, 4},
                    {-1, 4},
                    {1, -4},
                    {-1, -4},
                    {2, 3},
                    {-2, 3},
                    {2, -3},
                    {-2, -3},
                    {3, 3},
                    {-3, 3},
                    {3, -3},
                    {-3, -3},
                    {3, 2},
                    {-3, 2},
                    {3, -2},
                    {-3, -2},
                    {4, 1},
                    {-4, 1},
                    {4, -1},
                    {-4, -1},
                    {4, 0},
                    {-4, 0},
                    {4, 0},
                    {-4, 0},
                }));
    }

    /**
     * Tests the drawEllipse method with various parameters.
     *
     * @param centerX       the x-coordinate of the center of the ellipse
     * @param centerY       the y-coordinate of the center of the ellipse
     * @param a             the length of the semi-major axis
     * @param b             the length of the semi-minor axis
     * @param expectedPoints the expected points forming the ellipse
     */
    @ParameterizedTest
    @MethodSource("ellipseTestProvider")
    @DisplayName("Test drawing ellipses with various parameters")
    void testDrawEllipse(int centerX, int centerY, int a, int b, int[][] expectedPoints) {
        List<int[]> points = MidpointEllipse.drawEllipse(centerX, centerY, a, b);

        // Validate the number of points and the specific points
        assertEquals(expectedPoints.length, points.size(), "Number of points should match expected.");

        for (int i = 0; i < expectedPoints.length; i++) {
            assertArrayEquals(expectedPoints[i], points.get(i), "Point mismatch at index " + i);
        }
    }
}
