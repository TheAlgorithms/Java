package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ModeTest {
    @ParameterizedTest
    @MethodSource("tcStream")
    void basicTest(final int[] expected, final int[] numbers) {
        assertArrayEquals(expected, Mode.mode(numbers));
    }

    private static Stream<Arguments> tcStream() {
        return Stream.of(Arguments.of(null, new int[] {}), Arguments.of(new int[] {5}, new int[] {5}), Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}), Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {5, 4, 3, 2, 1}),
            Arguments.of(new int[] {7}, new int[] {7, 9, 9, 4, 5, 6, 7, 7, 8}), Arguments.of(new int[] {7, 9}, new int[] {7, 9, 9, 4, 5, 6, 7, 7, 9}));
    }
}
