package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LargestRectangleTest {

    @ParameterizedTest(name = "Histogram: {0} → Expected area: {1}")
    @MethodSource("histogramProvider")
    void testLargestRectangleHistogram(int[] heights, String expected) {
        assertEquals(expected, LargestRectangle.largestRectangleHistogram(heights));
    }

    static Stream<Arguments> histogramProvider() {
        return Stream.of(Arguments.of(new int[] {2, 1, 5, 6, 2, 3}, "10"), Arguments.of(new int[] {2, 4}, "4"), Arguments.of(new int[] {4, 4, 4, 4}, "16"), Arguments.of(new int[] {}, "0"), Arguments.of(new int[] {5}, "5"), Arguments.of(new int[] {0, 0, 0}, "0"),
            Arguments.of(new int[] {6, 2, 5, 4, 5, 1, 6}, "12"), Arguments.of(new int[] {2, 1, 5, 6, 2, 3, 1}, "10"), Arguments.of(createLargeArray(10000, 1), "10000"));
    }

    private static int[] createLargeArray(int size, int value) {
        int[] arr = new int[size];
        java.util.Arrays.fill(arr, value);
        return arr;
    }
}
