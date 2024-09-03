package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PalindromicPartitioningTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("a", 0), Arguments.of("aa", 0), Arguments.of("ab", 1), Arguments.of("ababbbabbababa", 3), Arguments.of("abcde", 4), Arguments.of("abacdcaba", 0));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMinimalPartitions(String input, int expected) {
        assertEquals(expected, PalindromicPartitioning.minimalPartitions(input));
    }
}
