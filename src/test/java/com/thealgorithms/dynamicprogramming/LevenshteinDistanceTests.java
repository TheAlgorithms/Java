package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LevenshteinDistanceTests {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testLevenshteinDistance(final int expected, final String str1, final String str2, final ToIntBiFunction<String, String> dist) {
        assertEquals(expected, dist.applyAsInt(str1, str2));
        assertEquals(expected, dist.applyAsInt(str2, str1));
        assertEquals(0, dist.applyAsInt(str1, str1));
        assertEquals(0, dist.applyAsInt(str2, str2));
    }

    private static Stream<Arguments> testCases() {
        final Object[][] testData = {
            {0, "", ""},
            {0, "Hello, World!", "Hello, World!"},
            {4, "", "Rust"},
            {3, "horse", "ros"},
            {6, "tan", "elephant"},
            {8, "execute", "intention"},
            {1, "a", "b"},
            {1, "a", "aa"},
            {1, "a", ""},
            {1, "a", "ab"},
            {1, "a", "ba"},
            {2, "a", "bc"},
            {2, "a", "cb"},
        };

        final List<ToIntBiFunction<String, String>> methods = Arrays.asList(LevenshteinDistance::naiveLevenshteinDistance, LevenshteinDistance::optimizedLevenshteinDistance);

        return Stream.of(testData).flatMap(input -> methods.stream().map(method -> Arguments.of(input[0], input[1], input[2], method)));
    }
}
