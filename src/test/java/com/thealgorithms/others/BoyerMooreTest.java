package com.thealgorithms.others;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoyerMooreTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void numberTests(int expected, int[] input) {
        Assertions.assertEquals(expected, BoyerMoore.findmajor(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(5, new int[] {5, 5, 5, 2}), Arguments.of(10, new int[] {10, 10, 20}), Arguments.of(10, new int[] {10, 20, 10}), Arguments.of(10, new int[] {20, 10, 10}), Arguments.of(-1, new int[] {10, 10, 20, 20, 30, 30}));
    }
}
