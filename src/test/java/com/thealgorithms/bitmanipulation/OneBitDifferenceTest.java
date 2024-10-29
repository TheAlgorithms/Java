package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OneBitDifferenceTest {

    @ParameterizedTest
    @CsvSource({"7, 5, true", "3, 2, true", "10, 8, true", "15, 15, false", "4, 1, false"})
    void testDifferByOneBit(int x, int y, boolean expected) {
        assertEquals(expected, OneBitDifference.differByOneBit(x, y));
    }
}
