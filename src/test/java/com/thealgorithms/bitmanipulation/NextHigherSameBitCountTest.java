package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NextHigherSameBitCountTest {

    @ParameterizedTest
    @CsvSource({
        "5, 6", // 101 -> 110
        "7, 11", // 0111 -> 1011
        "3, 5", // 011 -> 101
        "12, 17", // 001100 -> 010001
        "15, 23" // 01111 -> 10111
    })
    void
    testNextHigherSameBitCount(int input, int expected) {
        assertEquals(expected, NextHigherSameBitCount.nextHigherSameBitCount(input));
    }
}
