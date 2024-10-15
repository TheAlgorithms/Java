package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test case for IsPowerTwo class
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsPowerTwoTest {

    @ParameterizedTest
    @MethodSource("provideNumbersForPowerTwo")
    public void testIsPowerTwo(int number, boolean expected) {
        if (expected) {
            assertTrue(IsPowerTwo.isPowerTwo(number));
        } else {
            assertFalse(IsPowerTwo.isPowerTwo(number));
        }
    }

    private static Stream<Arguments> provideNumbersForPowerTwo() {
        return Stream.of(Arguments.of(1, true), // 2^0
            Arguments.of(2, true), // 2^1
            Arguments.of(4, true), // 2^2
            Arguments.of(8, true), // 2^3
            Arguments.of(16, true), // 2^4
            Arguments.of(32, true), // 2^5
            Arguments.of(64, true), // 2^6
            Arguments.of(128, true), // 2^7
            Arguments.of(256, true), // 2^8
            Arguments.of(1024, true), // 2^10
            Arguments.of(0, false), // 0 is not a power of two
            Arguments.of(-1, false), // Negative number
            Arguments.of(-2, false), // Negative number
            Arguments.of(-4, false), // Negative number
            Arguments.of(3, false), // 3 is not a power of two
            Arguments.of(5, false), // 5 is not a power of two
            Arguments.of(6, false), // 6 is not a power of two
            Arguments.of(15, false), // 15 is not a power of two
            Arguments.of(1000, false), // 1000 is not a power of two
            Arguments.of(1023, false) // 1023 is not a power of two
        );
    }
}
