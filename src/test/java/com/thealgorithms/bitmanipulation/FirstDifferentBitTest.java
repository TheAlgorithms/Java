package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FirstDifferentBitTest {

    @ParameterizedTest
    @CsvSource({"10, 8, 1", "7, 5, 1", "15, 14, 0", "1, 2, 0"})
    void testFirstDifferentBit(int x, int y, int expected) {
        assertEquals(expected, FirstDifferentBit.firstDifferentBit(x, y));
    }
}
