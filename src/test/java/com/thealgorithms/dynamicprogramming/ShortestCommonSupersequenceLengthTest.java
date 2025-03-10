package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShortestCommonSupersequenceLengthTest {
    @ParameterizedTest
    @CsvSource({"AGGTAB, GXTXAYB, 9", "ABC, ABC, 3", "ABC, DEF, 6", "'', ABC, 3", "ABCD, AB, 4", "ABC, BCD, 4", "A, B, 2"})
    void testShortestSupersequence(String input1, String input2, int expected) {
        assertEquals(expected, ShortestCommonSupersequenceLength.shortestSuperSequence(input1, input2));
    }
}
