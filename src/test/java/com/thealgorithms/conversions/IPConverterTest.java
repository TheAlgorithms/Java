package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IPConverterTest {

    @Test
    public void testIpToBinary() {
        assertEquals("11000000.10101000.00000001.00000001", IPConverter.ipToBinary("192.168.1.1"));
        assertEquals("01111111.00000011.00000100.00000101", IPConverter.ipToBinary("127.3.4.5"));
    }

    @Test
    public void testBinaryToIP() {
        assertEquals("192.168.1.1", IPConverter.binaryToIP("11000000.10101000.00000001.00000001"));
        assertEquals("127.3.4.5", IPConverter.binaryToIP("01111111.00000011.00000100.00000101"));
    }
}
