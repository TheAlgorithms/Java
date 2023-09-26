package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FindMaxTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void numberTests(int expected, int[] input) {
        Assertions.assertEquals(expected, FindMax.findMax(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(10, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arguments.of(5, new int[] {5, 5, 5, 5, 5}), Arguments.of(0, new int[] {-1, 0}), Arguments.of(-1, new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}), Arguments.of(9, new int[] {3, -2, 3, 9, -4, -4, 8}));
    }

    @Test
    public void testFindMaxThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> FindMax.findMax(new int[] {}));
    }
}
