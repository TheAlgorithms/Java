package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FactorialRecursionTest {
    @ParameterizedTest
    @MethodSource("inputStream")
    void testFactorialRecursion(long expected, int number) {
        assertEquals(expected, FactorialRecursion.factorial(number));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(1, 0), Arguments.of(1, 1), Arguments.of(2, 2), Arguments.of(6, 3), Arguments.of(120, 5));
    }

    @Test
    void testThrowsForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> FactorialRecursion.factorial(-1));
    }
}
