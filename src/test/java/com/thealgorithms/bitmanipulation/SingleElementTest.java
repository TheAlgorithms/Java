package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class SingleElementTest {

    /**
     * Parameterized test to find the single non-duplicate element
     * in the given arrays.
     *
     * @param arr  the input array where every element appears twice except one
     * @param expected the expected single element result
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindSingleElement(int[] arr, int expected) {
        assertEquals(expected, SingleElement.findSingleElement(arr));
    }

    /**
     * Provides test cases for the parameterized test.
     *
     * @return Stream of arguments consisting of arrays and expected results
     */
    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {1, 1, 2, 2, 4, 4, 3}, 3), Arguments.of(new int[] {1, 2, 2, 3, 3}, 1), Arguments.of(new int[] {10}, 10));
    }
}
