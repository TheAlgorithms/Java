package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SumOfOddNumbersTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void sumOfFirstNOddNumbersTests(int expected, int input) {
        Assertions.assertEquals(expected, SumOfOddNumbers.sumOfFirstNOddNumbers(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(1, 1), Arguments.of(4, 2), Arguments.of(9, 3), Arguments.of(16, 4), Arguments.of(25, 5), Arguments.of(100, 10));
    }

    @Test
    public void testSumOfFirstNOddNumbersThrowsExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> SumOfOddNumbers.sumOfFirstNOddNumbers(-1));
    }
}
