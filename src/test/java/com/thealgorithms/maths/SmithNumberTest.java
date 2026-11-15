package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SmithNumberTest {

    @ParameterizedTest
    @CsvSource({"4", "22", "27", "58", "85", "94", "121", "562", "985", "4937775"})
    void positiveSmithNumbersTest(int n) {
        assertTrue(SmithNumber.isSmithNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"-22", "-4", "-1", "0", "1", "2", "3", "5", "6", "8", "9", "10", "11", "100", "550", "999", "1234557"})
    void negativeSmithNumbersTest(int n) {
        assertFalse(SmithNumber.isSmithNumber(n));
    }
}
