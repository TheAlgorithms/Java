package com.thealgorithms.ciphers.a5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.BitSet;
import org.junit.jupiter.api.Test;

// Basic tests for sanity check
class LFSRTest {

    // Represents 0100 1110 0010 1111 0100 1101 0111 1100 0001 1110 1011 1000 1000 1011 0011 1010
    // But we start reverse way because bitset starts from most right (1010)
    byte[] sessionKeyBytes = {
        58,
        (byte) 139,
        (byte) 184,
        30,
        124,
        77,
        47,
        78,
    };

    // Represents 11 1010 1011 0011 1100 1011
    byte[] frameCounterBytes = { (byte) 203, (byte) 179, 58 };

    @Test
    void initialize() {
        BitSet sessionKey = BitSet.valueOf(sessionKeyBytes);
        BitSet frameCounter = BitSet.valueOf(frameCounterBytes);

        BitSet expected = new BitSet(19);
        expected.set(0);
        expected.set(1);
        expected.set(3);
        expected.set(4);
        expected.set(5);
        expected.set(7);
        expected.set(9);
        expected.set(10);
        expected.set(11);
        expected.set(12);
        expected.set(13);
        expected.set(15);
        expected.set(16);
        expected.set(17);

        LFSR lfsr0 = new LFSR(19, 8, new int[] { 13, 16, 17, 18 });
        lfsr0.initialize(sessionKey, frameCounter);
        assertEquals(expected.toString(), lfsr0.toString());
    }

    @Test
    void clock() {
        BitSet sessionKey = BitSet.valueOf(sessionKeyBytes);
        BitSet frameCounter = BitSet.valueOf(frameCounterBytes);

        LFSR lfsr0 = new LFSR(19, 8, new int[] { 13, 16, 17, 18 });
        lfsr0.initialize(sessionKey, frameCounter);

        BitSet expected = new BitSet(19);
        expected.set(0);
        expected.set(1);
        expected.set(2);
        expected.set(4);
        expected.set(5);
        expected.set(6);
        expected.set(8);
        expected.set(10);
        expected.set(11);
        expected.set(12);
        expected.set(13);
        expected.set(14);
        expected.set(16);
        expected.set(17);
        expected.set(18);

        lfsr0.clock();
        assertEquals(expected.toString(), lfsr0.toString());
    }

    @Test
    void getClockBit() {
        BitSet sessionKey = BitSet.valueOf(sessionKeyBytes);
        BitSet frameCounter = BitSet.valueOf(frameCounterBytes);

        LFSR lfsr0 = new LFSR(19, 8, new int[] { 13, 16, 17, 18 });

        assertFalse(lfsr0.getClockBit());

        lfsr0.initialize(sessionKey, frameCounter);

        assertFalse(lfsr0.getClockBit());
    }
}
