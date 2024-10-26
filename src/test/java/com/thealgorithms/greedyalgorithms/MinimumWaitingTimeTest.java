package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MinimumWaitingTimeTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMinimumWaitingTime(int[] queries, int expected) {
        assertEquals(expected, MinimumWaitingTime.minimumWaitingTime(queries));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {3, 2, 1, 2, 6}, 17), Arguments.of(new int[] {3, 2, 1}, 4), Arguments.of(new int[] {1, 2, 3, 4}, 10), Arguments.of(new int[] {5, 5, 5, 5}, 30), Arguments.of(new int[] {}, 0));
    }
}
