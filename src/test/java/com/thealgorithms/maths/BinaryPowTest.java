package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryPowTest {

    @Test
    void testBinaryPow() {
        assertEquals(8, BinaryPow.binPow(2, 3));
        assertEquals(32768, BinaryPow.binPow(2, 15));
        assertEquals(19683, BinaryPow.binPow(3, 9));
    }

}
