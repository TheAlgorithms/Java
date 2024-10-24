package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BandwidthAllocationTest {

    @ParameterizedTest
    @MethodSource("bandwidthProvider")
    public void testMaxValue(int capacity, int[] bandwidths, int[] values, int expected) {
        assertEquals(expected, BandwidthAllocation.maxValue(capacity, bandwidths, values));
    }

    private static Stream<Arguments> bandwidthProvider() {
        return Stream.of(Arguments.of(50, new int[] {20, 10, 30}, new int[] {40, 20, 30}, 80), Arguments.of(0, new int[] {5, 10}, new int[] {10, 20}, 0), Arguments.of(5, new int[] {5, 10}, new int[] {10, 20}, 10), Arguments.of(15, new int[] {10, 20}, new int[] {10, 25}, 18),
            Arguments.of(25, new int[] {10, 15, 20}, new int[] {10, 30, 50}, 60));
    }
}
