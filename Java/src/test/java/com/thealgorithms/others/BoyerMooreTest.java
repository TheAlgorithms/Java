package com.thealgorithms.others;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoyerMooreTest {

    @ParameterizedTest
    @MethodSource("inputStreamWithExistingMajority")
    void checkWhenMajorityExists(int expected, int[] input) {
        Assertions.assertEquals(expected, BoyerMoore.findMajor(input).get());
    }

    private static Stream<Arguments> inputStreamWithExistingMajority() {
        return Stream.of(Arguments.of(5, new int[] {5, 5, 5, 2}), Arguments.of(10, new int[] {10, 10, 20}), Arguments.of(10, new int[] {10, 20, 10}), Arguments.of(10, new int[] {20, 10, 10}), Arguments.of(4, new int[] {1, 4, 2, 4, 4, 5, 4}), Arguments.of(-1, new int[] {-1}));
    }

    @ParameterizedTest
    @MethodSource("inputStreamWithoutMajority")
    void checkWhenMajorityExists(int[] input) {
        Assertions.assertFalse(BoyerMoore.findMajor(input).isPresent());
    }

    private static Stream<Arguments> inputStreamWithoutMajority() {
        return Stream.of(Arguments.of(new int[] {10, 10, 20, 20, 30, 30}), Arguments.of(new int[] {10, 20, 30, 40, 50}), Arguments.of(new int[] {1, 2}), Arguments.of(new int[] {}));
    }
}
