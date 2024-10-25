package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CelebrityFinderTest {

    @ParameterizedTest
    @MethodSource("providePartyMatrices")
    public void testCelebrityFinder(int[][] party, int expected) {
        assertEquals(expected, CelebrityFinder.findCelebrity(party));
    }

    private static Stream<Arguments> providePartyMatrices() {
        return Stream.of(
            // Test case 1: Celebrity exists
            Arguments.of(new int[][] {{0, 1, 1}, {0, 0, 1}, {0, 0, 0}}, 2),

            // Test case 2: No celebrity
            Arguments.of(new int[][] {{0, 1, 0}, {1, 0, 1}, {1, 1, 0}}, -1),

            // Test case 3: Everyone knows each other, no celebrity
            Arguments.of(new int[][] {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, -1),

            // Test case 4: Single person, they are trivially a celebrity
            Arguments.of(new int[][] {{0}}, 0),

            // Test case 5: All know the last person, and they know no one
            Arguments.of(new int[][] {{0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 0}}, 3),

            // Test case 6: Larger party with no celebrity
            Arguments.of(new int[][] {{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 1}, {1, 1, 1, 0}}, -1),

            // Test case 7: Celebrity at the start of the matrix
            Arguments.of(new int[][] {{0, 0, 0}, {1, 0, 1}, {1, 1, 0}}, 0));
    }
}
