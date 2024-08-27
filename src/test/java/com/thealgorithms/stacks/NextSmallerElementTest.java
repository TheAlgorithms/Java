package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NextSmallerElementTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindNextSmallerElements(int[] input, int[] expected) {
        assertArrayEquals(expected, NextSmallerElement.findNextSmallerElements(input));
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {2, 7, 3, 5, 4, 6, 8}, new int[] {-1, 2, 2, 3, 3, 4, 6}), Arguments.of(new int[] {5}, new int[] {-1}), Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {-1, 1, 2, 3, 4}), Arguments.of(new int[] {5, 4, 3, 2, 1}, new int[] {-1, -1, -1, -1, -1}),
            Arguments.of(new int[] {4, 5, 2, 25}, new int[] {-1, 4, -1, 2}), Arguments.of(new int[] {}, new int[] {}));
    }

    @Test
    void testFindNextSmallerElementsExceptions() {
        assertThrows(IllegalArgumentException.class, () -> NextSmallerElement.findNextSmallerElements(null));
    }
}
