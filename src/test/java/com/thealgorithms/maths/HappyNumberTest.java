package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HappyNumberTest {

    @ParameterizedTest
    @CsvSource({"1", "7", "19", "100", "565", "998", "1000000"})
    void testHappyNumbers(final int n) {
        assertTrue(HappyNumber.isHappy(n));
        assertTrue(HappyNumber.isHappyNumber(n));
    }

    @ParameterizedTest
    @CsvSource({"2", "4", "20", "300", "999", "9999"})
    void testUnhappyNumbers(final int n) {
        assertFalse(HappyNumber.isHappy(n));
        assertFalse(HappyNumber.isHappyNumber(n));
    }
}
