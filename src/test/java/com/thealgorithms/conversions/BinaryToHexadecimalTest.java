package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinaryToHexadecimalTest {

    @Test
    public void testBinaryToHexadecimal() {
        assertEquals("6A", BinaryToHexadecimal.binToHex(1101010));
        assertEquals("C", BinaryToHexadecimal.binToHex(1100));
    }
}
