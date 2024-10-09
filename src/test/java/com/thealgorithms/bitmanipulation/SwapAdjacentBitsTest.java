package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SwapAdjacentBitsTest {

    @ParameterizedTest
    @CsvSource({
        "2, 1", // 2 (10 in binary) should become 1 (01 in binary)
        "43, 23", // 43 should become 23
        "153, 102", // 153 should become 102
        "15, 15", // 15 (1111) remains 15 (1111)
        "0, 0" // 0 (0000) remains 0 (0000)
    })
    void
    testSwapAdjacentBits(int input, int expected) {
        assertEquals(expected, SwapAdjacentBits.swapAdjacentBits(input));
    }
}
