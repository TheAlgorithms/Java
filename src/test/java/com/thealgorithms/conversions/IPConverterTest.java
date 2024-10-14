package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IPConverterTest {

    private static String generateTestIP(int a, int b, int c, int d) {
        return String.format("%d.%d.%d.%d", a, b, c, d);
    }

    private static String generateTestBinary(int a, int b, int c, int d) {
        return String.format("%8s.%8s.%8s.%8s", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(c), Integer.toBinaryString(d)).replace(' ', '0');
    }

    @Test
    public void testIpToBinary() {
        assertEquals(generateTestBinary(192, 168, 1, 1), IPConverter.ipToBinary(generateTestIP(192, 168, 1, 1)));
        assertEquals(generateTestBinary(127, 3, 4, 5), IPConverter.ipToBinary(generateTestIP(127, 3, 4, 5)));
        assertEquals(generateTestBinary(0, 0, 0, 0), IPConverter.ipToBinary(generateTestIP(0, 0, 0, 0)));
    }

    @Test
    public void testBinaryToIP() {
        assertEquals(generateTestIP(192, 168, 1, 1), IPConverter.binaryToIP(generateTestBinary(192, 168, 1, 1)));
        assertEquals(generateTestIP(127, 3, 4, 5), IPConverter.binaryToIP(generateTestBinary(127, 3, 4, 5)));
        assertEquals(generateTestIP(0, 0, 0, 0), IPConverter.binaryToIP(generateTestBinary(0, 0, 0, 0)));
    }
}
