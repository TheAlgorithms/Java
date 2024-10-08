package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SwapAdjacentBitsTest {

    @Test
    void testSwapAdjacentBits() {
        assertEquals(1, SwapAdjacentBits.swapAdjacentBits(2));

        assertEquals(23, SwapAdjacentBits.swapAdjacentBits(43));

        assertEquals(102, SwapAdjacentBits.swapAdjacentBits(153));

        assertEquals(15, SwapAdjacentBits.swapAdjacentBits(15));

        assertEquals(0, SwapAdjacentBits.swapAdjacentBits(0));
    }
}
