package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NextGreaterElementTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindNextGreaterElements(int[] input, int[] expected) {
        assertArrayEquals(expected, NextGreaterElement.findNextGreaterElements(input));
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {2, 7, 3, 5, 4, 6, 8}, new int[] {7, 8, 5, 6, 6, 8, 0}), Arguments.of(new int[] {5}, new int[] {0}), Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 0}), Arguments.of(new int[] {5, 4, 3, 2, 1}, new int[] {0, 0, 0, 0, 0}),
            Arguments.of(new int[] {4, 5, 2, 25}, new int[] {5, 25, 25, 0}), Arguments.of(new int[] {}, new int[] {}));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> NextGreaterElement.findNextGreaterElements(null));
    }
}
