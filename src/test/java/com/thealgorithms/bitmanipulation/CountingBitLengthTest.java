package com.thealgorithms.bitmanipulation;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CountingBitLengthTest {
    static Stream<Object[]> inputStream() {
        return Stream.of(new Object[] {3, 5}, new Object[] {0, 0}, new Object[] {2, -3});
    }

    @ParameterizedTest
    @MethodSource("inputStream")
    void bitLengthTest(int expected, int num) {
        Assertions.assertEquals(expected, CountingBitLength.countingBitLength(num));
    }
}
