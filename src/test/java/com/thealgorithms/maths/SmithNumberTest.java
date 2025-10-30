package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SmithNumberTest {

    @ParameterizedTest
    @CsvSource({"4", "22", "121", "562", "985", "4937775"})
    void positiveSmithNumbersTest(int n) {
        assertTrue(SmithNumber.isSmithNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"2", "11", "100", "550", "999", "1234557"})
    void negativeSmithNumbersTest(int n) {
        assertFalse(SmithNumber.isSmithNumber(n));
    }
}
