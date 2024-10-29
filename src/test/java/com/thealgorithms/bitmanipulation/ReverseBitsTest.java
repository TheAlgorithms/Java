package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReverseBitsTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testReverseBits(int input, int expected) {
        assertEquals(expected, ReverseBits.reverseBits(input));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Edge case: All bits are 0
            Arguments.of(0, 0),

            // Edge case: All bits are 1 (Two’s complement representation of -1)
            Arguments.of(-1, -1),

            // Case with random number 43261596
            Arguments.of(43261596, 964176192),

            // Case with maximum positive value for 32-bit integer
            Arguments.of(Integer.MAX_VALUE, -2),

            // Case with minimum value (all bits 1 except the sign bit)
            Arguments.of(Integer.MIN_VALUE, 1),

            // Case with a single bit set (2^0 = 1)
            Arguments.of(1, Integer.MIN_VALUE),

            // Case with alternating bits: 0b101010...10 (in binary)
            Arguments.of(0xAAAAAAAA, 0x55555555));
    }
}
