package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SwapAdjacentBitsTest {

    @ParameterizedTest
    @CsvSource({"2, 1", // 2 (binary: 10) -> 1 (binary: 01)
        "43, 23", // 43 (binary: 101011) -> 23 (binary: 010111)
        "153, 102", // 153 (binary: 10011001) -> 102 (binary: 01100110)
        "15, 15", // 15 (binary: 1111) -> 15 (binary: 1111) (no change)
        "0, 0", // 0 (binary: 0000) -> 0 (binary: 0000) (no change)
        "1, 2", // 1 (binary: 01) -> 2 (binary: 10)
        "170, 85", // 170 (binary: 10101010) -> 85 (binary: 01010101)
        "85, 170", // 85 (binary: 01010101) -> 170 (binary: 10101010)
        "255, 255", // 255 (binary: 11111111) -> 255 (binary: 11111111) (no change)
        "128, 64", // 128 (binary: 10000000) -> 64 (binary: 01000000)
        "1024, 2048",
        "-1, -1", // -1 (all bits 1) remains -1 (no change due to two's complement)
        "-2, -3", // -2 (binary: ...1110) -> -3 (binary: ...1101)
        "2147483647, -1073741825", "-2147483648, -1073741824"})
    void
    testSwapAdjacentBits(int input, int expected) {
        assertEquals(expected, SwapAdjacentBits.swapAdjacentBits(input));
    }
}
