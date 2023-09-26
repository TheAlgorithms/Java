package com.thealgorithms.maths;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows; // Add this import

public class FindMaxTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void numberTests(int[] input) {
        Assertions.assertEquals(Arrays.stream(input).max().orElseThrow(), FindMax.findMax(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arguments.of(new int[] {5, 5, 5, 5, 5}), Arguments.of(new int[] {-1, 0}), Arguments.of(new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}));
    }

    @Test
    public void testFindMaxThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> FindMax.findMax(new int[] {}));
    }
}
