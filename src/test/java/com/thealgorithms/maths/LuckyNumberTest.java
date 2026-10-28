package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LuckyNumberTest {

    @ParameterizedTest
    @CsvSource({"1", "3", "13", "49", "109", "459", "949"})
    void luckyNumbersTest(int n) {
        assertTrue(LuckyNumber.isLucky(n));
        assertTrue(LuckyNumber.isLuckyNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"2", "17", "100", "300", "700"})
    void nonLuckyNumbersTest(int n) {
        assertFalse(LuckyNumber.isLucky(n));
        assertFalse(LuckyNumber.isLuckyNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"0", "-1"})
    void throwsNegativeNumbersNotAllowed(int n) {
        assertThrows(IllegalArgumentException.class, () -> LuckyNumber.isLucky(n));
        assertThrows(IllegalArgumentException.class, () -> LuckyNumber.isLuckyNumber(n));
    }
}
