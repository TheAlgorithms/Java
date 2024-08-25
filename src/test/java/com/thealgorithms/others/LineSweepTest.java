package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LineSweepTest {
    private record OverlapTestCase(int[][] ranges, boolean expected) {
    }

    private record MaximumEndPointTestCase(int[][] ranges, int expected) {
    }

    @ParameterizedTest
    @MethodSource("provideOverlapTestData")
    void testIsOverlap(OverlapTestCase testCase) {
        assertEquals(testCase.expected(), LineSweep.isOverlap(testCase.ranges()));
    }

    private static Stream<Arguments> provideOverlapTestData() {
        return Stream.of(Arguments.of(new OverlapTestCase(new int[][] {{0, 10}, {7, 20}, {15, 24}}, true)), Arguments.of(new OverlapTestCase(new int[][] {{0, 10}, {11, 20}, {21, 24}}, false)), Arguments.of(new OverlapTestCase(new int[][] {{0, 10}, {10, 20}, {21, 24}}, true)),
            Arguments.of(new OverlapTestCase(new int[][] {{5, 10}}, false)), Arguments.of(new OverlapTestCase(new int[][] {{1, 5}, {1, 5}, {1, 5}}, true)), Arguments.of(new OverlapTestCase(new int[][] {{1, 1}, {2, 2}, {3, 3}}, false)), Arguments.of(new OverlapTestCase(new int[][] {}, false)));
    }

    @ParameterizedTest
    @MethodSource("provideMaximumEndPointTestData")
    void testFindMaximumEndPoint(MaximumEndPointTestCase testCase) {
        assertEquals(testCase.expected(), LineSweep.findMaximumEndPoint(testCase.ranges()));
    }

    private static Stream<Arguments> provideMaximumEndPointTestData() {
        return Stream.of(Arguments.of(new MaximumEndPointTestCase(new int[][] {{10, 20}, {1, 100}, {14, 16}, {1, 8}}, 100)));
    }
}
