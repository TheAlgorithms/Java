package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class FindNthBitTest {

    /**
     * A parameterized test that checks the value of the Nth bit for different inputs.
     *
     * @param num     the number whose Nth bit is being tested
     * @param n       the bit position
     * @param expected the expected value of the Nth bit (0 or 1)
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void findNthBitParameterizedTest(int num, int n, int expected) {
        assertEquals(expected, FindNthBit.findNthBit(num, n));
    }

    /**
     * Provides the test cases as a stream of arguments for the parameterized test.
     *
     * @return a stream of test cases where each case consists of a number, the bit position,
     * and the expected result.
     */
    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(13, 2, 0), // binary: 1101, 2nd bit is 0
            Arguments.of(13, 3, 1), // binary: 1101, 3rd bit is 1
            Arguments.of(4, 2, 0), // binary: 100, 2nd bit is 0
            Arguments.of(4, 3, 1), // binary: 100, 3rd bit is 1
            Arguments.of(1, 1, 1) // binary: 1, 1st bit is 1
        );
    }
}
