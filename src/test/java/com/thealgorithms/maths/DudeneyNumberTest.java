package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DudeneyNumberTest {
    @ParameterizedTest
    @CsvSource({"1", "512", "4913", "5832", "17576", "19683"})
    void positiveDudeneyBase10Power3(final int n) {
        assertTrue(DudeneyNumber.isDudeney(n));
    }

    @ParameterizedTest
    @CsvSource({"2", "19", "21", "125", "27", "343", "729", "19682", "19684"})
    void negativeDudeneyBase10Power3(final int n) {
        assertFalse(DudeneyNumber.isDudeney(n));
    }

    @ParameterizedTest
    @CsvSource({"0", "-1"})
    void throwsInputLessThanOne(final int n) {
        assertThrows(IllegalArgumentException.class, () -> DudeneyNumber.isDudeney(n));
    }
}
