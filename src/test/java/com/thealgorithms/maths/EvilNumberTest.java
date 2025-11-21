package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EvilNumberTest {
    @ParameterizedTest
    @CsvSource({"0", "3", "10", "129", "222", "500", "777", "1198"})
    void evilNumbersTest(int n) {
        assertTrue(EvilNumber.isEvilNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"1", "7", "100", "333", "555", "1199"})
    void odiousNumbersTest(int n) {
        assertFalse(EvilNumber.isEvilNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"-1"})
    void throwsNegativeNumbersNotAllowed(int n) {
        assertThrows(IllegalArgumentException.class, () -> EvilNumber.isEvilNumber(n));
    }
}
