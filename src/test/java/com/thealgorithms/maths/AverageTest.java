package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AverageTest {

    private static final double SMALL_VALUE = 0.00001d;

    @ParameterizedTest(name = "average({0}) should be approximately {1}")
    @MethodSource("provideDoubleArrays")
    void testAverageDouble(double[] numbers, double expected) {
        assertEquals(expected, Average.average(numbers), SMALL_VALUE);
    }

    @ParameterizedTest(name = "average({0}) should be {1}")
    @MethodSource("provideIntArrays")
    void testAverageInt(int[] numbers, long expected) {
        assertEquals(expected, Average.average(numbers));
    }

    @Test
    void testAverageDoubleThrowsExceptionOnNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Average.average((double[]) null));
        assertThrows(IllegalArgumentException.class, () -> Average.average(new double[0]));
    }

    @Test
    void testAverageIntThrowsExceptionOnNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Average.average((int[]) null));
        assertThrows(IllegalArgumentException.class, () -> Average.average(new int[0]));
    }

    private static Stream<Arguments> provideDoubleArrays() {
        return Stream.of(Arguments.of(new double[] {3d, 6d, 9d, 12d, 15d, 18d, 21d}, 12d), Arguments.of(new double[] {5d, 10d, 15d, 20d, 25d, 30d, 35d}, 20d), Arguments.of(new double[] {1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d}, 4.5d), Arguments.of(new double[] {0d, 0d, 0d}, 0d),
            Arguments.of(new double[] {-1d, -2d, -3d}, -2d), Arguments.of(new double[] {1e-10, 1e-10, 1e-10}, 1e-10));
    }

    private static Stream<Arguments> provideIntArrays() {
        return Stream.of(Arguments.of(new int[] {2, 4, 10}, 5L), Arguments.of(new int[] {0, 0, 0}, 0L), Arguments.of(new int[] {-1, -2, -3}, -2L), Arguments.of(new int[] {1, 1, 1, 1, 1}, 1L));
    }
}
