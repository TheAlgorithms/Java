package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IPConverterTest {

    private static final String IP_1 = "192.168.1.1";
    private static final String IP_1_BINARY = "11000000.10101000.00000001.00000001";
    private static final String IP_2 = "127.3.4.5";
    private static final String IP_2_BINARY = "01111111.00000011.00000100.00000101";
    private static final String IP_3 = "0.0.0.0";
    private static final String IP_3_BINARY = "00000000.00000000.00000000.00000000";

    @Test
    public void testIpToBinary() {
        assertEquals(IP_1_BINARY, IPConverter.ipToBinary(IP_1));
        assertEquals(IP_2_BINARY, IPConverter.ipToBinary(IP_2));
        assertEquals(IP_3_BINARY, IPConverter.ipToBinary(IP_3));
    }

    @Test
    public void testBinaryToIP() {
        assertEquals(IP_1, IPConverter.binaryToIP(IP_1_BINARY));
        assertEquals(IP_2, IPConverter.binaryToIP(IP_2_BINARY));
        assertEquals(IP_3, IPConverter.binaryToIP(IP_3_BINARY));
    }
}
