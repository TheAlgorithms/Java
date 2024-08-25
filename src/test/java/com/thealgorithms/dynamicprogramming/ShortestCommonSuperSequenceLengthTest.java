package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShortestCommonSuperSequenceLengthTest {
    @ParameterizedTest
    @CsvSource({"AGGTAB, GXTXAYB, 9", "ABC, ABC, 3", "ABC, DEF, 6", "'', ABC, 3", "ABCD, AB, 4", "ABC, BCD, 4", "A, B, 2"})
    void testShortestSuperSequence(String input1, String input2, int expected) {
        assertEquals(expected, ShortestCommonSuperSequenceLength.shortestSuperSequence(input1, input2));
    }
}
