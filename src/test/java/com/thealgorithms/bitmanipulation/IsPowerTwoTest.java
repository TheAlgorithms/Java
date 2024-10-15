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
        return Stream.of(Arguments.of(1, Boolean.TRUE), // 2^0
            Arguments.of(2, Boolean.TRUE), // 2^1
            Arguments.of(4, Boolean.TRUE), // 2^2
            Arguments.of(8, Boolean.TRUE), // 2^3
            Arguments.of(16, Boolean.TRUE), // 2^4
            Arguments.of(32, Boolean.TRUE), // 2^5
            Arguments.of(64, Boolean.TRUE), // 2^6
            Arguments.of(128, Boolean.TRUE), // 2^7
            Arguments.of(256, Boolean.TRUE), // 2^8
            Arguments.of(1024, Boolean.TRUE), // 2^10
            Arguments.of(0, Boolean.FALSE), // 0 is not a power of two
            Arguments.of(-1, Boolean.FALSE), // Negative number
            Arguments.of(-2, Boolean.FALSE), // Negative number
            Arguments.of(-4, Boolean.FALSE), // Negative number
            Arguments.of(3, Boolean.FALSE), // 3 is not a power of two
            Arguments.of(5, Boolean.FALSE), // 5 is not a power of two
            Arguments.of(6, Boolean.FALSE), // 6 is not a power of two
            Arguments.of(15, Boolean.FALSE), // 15 is not a power of two
            Arguments.of(1000, Boolean.FALSE), // 1000 is not a power of two
            Arguments.of(1023, Boolean.FALSE) // 1023 is not a power of two
        );
    }
}
