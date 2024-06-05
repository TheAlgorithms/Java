package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FindMaxRecursionTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void numberTests(int expected, int[] input) {
        Assertions.assertEquals(expected, FindMaxRecursion.max(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(5, new int[] {5, 5, 5, 5, 5}), Arguments.of(0, new int[] {-1, 0}), Arguments.of(-1, new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}), Arguments.of(9, new int[] {3, -2, 3, 9, -4, -4, 8}), Arguments.of(3, new int[] {3}));
    }

    @Test
    public void testFindMaxThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> FindMaxRecursion.max(new int[] {}));
    }
}
