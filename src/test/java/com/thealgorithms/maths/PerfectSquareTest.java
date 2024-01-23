package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PerfectSquareTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2 * 2, 3 * 3, 4 * 4, 5 * 5, 6 * 6, 7 * 7, 8 * 8, 9 * 9, 10 * 10, 11 * 11, 123 * 123})
    void positiveTest(final int number) {
        Assertions.assertTrue(PerfectSquare.isPerfectSquare(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3, -4, -5, -100, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13, 15, 17, 99, 101, 257, 999, 1001})
    void negativeTest(final int number) {
        Assertions.assertFalse(PerfectSquare.isPerfectSquare(number));
    }
}
