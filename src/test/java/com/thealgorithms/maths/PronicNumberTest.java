package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PronicNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 6, 12, 20, 30, 42, 110, 272, 380, 420, 1260, 2550})
    void testForPronicNumber(final int number) {
        Assertions.assertTrue(PronicNumber.isPronic(number));
        Assertions.assertTrue(PronicNumber.isPronicNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 21, 36, 150, 2500})
    void testForNonPronicNumber(final int number) {
        Assertions.assertFalse(PronicNumber.isPronic(number));
        Assertions.assertFalse(PronicNumber.isPronicNumber(number));
    }
}
