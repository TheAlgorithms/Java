package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test case for IsPowerFour class
 */

public class IsPowerTwoTest {

    @ParameterizedTest
    @MethodSource("provideNumbersForPowerFour")
    public void testIsPowerFour(int number, boolean expected) {
        if (expected) {
            assertTrue(IsPowerFour.isPowerFour(number));
        } else {
            assertFalse(IsPowerFour.isPowerFour(number));
        }
    }

    private static Stream<Arguments> provideNumbersForPowerFour() {
        return Stream.of(Arguments.of(1, Boolean.TRUE), // 4^0
            Arguments.of(4, Boolean.TRUE), // 4^1
            Arguments.of(16, Boolean.TRUE), // 4^2
            Arguments.of(64, Boolean.TRUE), // 4^3
            Arguments.of(256, Boolean.TRUE), // 4^4
            Arguments.of(1024, Boolean.FALSE), // 1024 = 2^10, not 4^n
            Arguments.of(0, Boolean.FALSE), // 0 is not a power of four
            Arguments.of(-4, Boolean.FALSE), // Negative number
            Arguments.of(-16, Boolean.FALSE), // Negative number
            Arguments.of(2, Boolean.FALSE), // 2 is not a power of four
            Arguments.of(8, Boolean.FALSE), // 8 = 2^3, not 4^n
            Arguments.of(12, Boolean.FALSE), // 12 is not a power of four
            Arguments.of(20, Boolean.FALSE), // 20 is not a power of four
            Arguments.of(100, Boolean.FALSE), // 100 is not a power of four
            Arguments.of(4096, Boolean.TRUE) // 4^6 = 4096
        );
    }
}
