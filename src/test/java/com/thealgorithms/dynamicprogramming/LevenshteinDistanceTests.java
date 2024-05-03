package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LevenshteinDistanceTests {
    private static final Object[][] TEST_CASES
        = {{0, "", ""}, {0, "Hello, World!", "Hello, World!"}, {4, "", "Rust"}, {3, "horse", "ros"}, {6, "tan", "elephant"}, {8, "execute", "intention"}, {0, "", ""}, {0, "Hello, World!", "Hello, World!"}, {4, "", "Rust"}, {3, "horse", "ros"}, {6, "tan", "elephant"}, {8, "execute", "intention"}};

    @ParameterizedTest
    @MethodSource("testCases")
    void testLevenshteinDistance(int expectedDistance, String str1, String str2) {
        assertEquals(expectedDistance, LevenshteinDistance.naiveLevenshteinDistance(str1, str2));
        assertEquals(expectedDistance, LevenshteinDistance.optimizedLevenshteinDistance(str1, str2));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(TEST_CASES).map(args -> Arguments.of(args[0], args[1], args[2]));
    }
}
