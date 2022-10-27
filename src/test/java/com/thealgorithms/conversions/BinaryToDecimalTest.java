package com.thealgorithms.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryToDecimalTest {

    @Test
    public void testBinaryToDecimal() {
        //zeros at the starting should be removed
        assertEquals(0, BinaryToDecimal.binaryToDecimal(0));
        assertEquals(1, BinaryToDecimal.binaryToDecimal(1));
        assertEquals(5, BinaryToDecimal.binaryToDecimal(101));
        assertEquals(63, BinaryToDecimal.binaryToDecimal(111111));
        assertEquals(512, BinaryToDecimal.binaryToDecimal(1000000000));
    }
}