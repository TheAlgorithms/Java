package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AbundantNumberTest {
    @ParameterizedTest
    @CsvSource({"12", "66", "222", "444", "888", "2424"})
    void abundantNumbersTest(int n) {
        assertTrue(AbundantNumber.isAbundant(n));
        assertTrue(AbundantNumber.isAbundantNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "6", "111", "333", "2222"})
    void nonAbundantNumbersTest(int n) {
        assertFalse(AbundantNumber.isAbundant(n));
        assertFalse(AbundantNumber.isAbundantNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"0", "-1"})
    void throwsNegativeNumbersNotAllowed(int n) {
        assertThrows(IllegalArgumentException.class, () -> AbundantNumber.isAbundant(n));
        assertThrows(IllegalArgumentException.class, () -> AbundantNumber.isAbundantNumber(n));
    }
}
