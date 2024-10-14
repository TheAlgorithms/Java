package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EndianConverterTest {

    @Test
    public void testBigToLittleEndian() {
        assertEquals(0x78563412, EndianConverter.bigToLittleEndian(0x12345678));
        assertEquals(0x00000000, EndianConverter.bigToLittleEndian(0x00000000));
        assertEquals(0x00000001, EndianConverter.bigToLittleEndian(0x01000000));
    }

    @Test
    public void testLittleToBigEndian() {
        assertEquals(0x12345678, EndianConverter.littleToBigEndian(0x78563412));
        assertEquals(0x00000000, EndianConverter.littleToBigEndian(0x00000000));
        assertEquals(0x01000000, EndianConverter.littleToBigEndian(0x00000001));
    }
}
