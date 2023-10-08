package com.thealgorithms.ciphers.a5;

import java.util.BitSet;

public class LFSR implements BaseLFSR {

    private final BitSet register;
    private final int length;
    private final int clockBitIndex;
    private final int[] tappingBitsIndices;

    public LFSR(int length, int clockBitIndex, int[] tappingBitsIndices) {
        this.length = length;
        this.clockBitIndex = clockBitIndex;
        this.tappingBitsIndices = tappingBitsIndices;
        register = new BitSet(length);
    }

    @Override
    public void initialize(BitSet sessionKey, BitSet frameCounter) {
        register.clear();
        clock(sessionKey, SESSION_KEY_LENGTH);
        clock(frameCounter, FRAME_COUNTER_LENGTH);
    }

    private void clock(BitSet key, int keyLength) {
        // We start from reverse because LFSR 0 index is the left most bit
        // while key 0 index is right most bit, so we reverse it
        for (int i = keyLength - 1; i >= 0; --i) {
            var newBit = key.get(i) ^ xorTappingBits();
            pushBit(newBit);
        }
    }

    @Override
    public boolean clock() {
        return pushBit(xorTappingBits());
    }

    public boolean getClockBit() {
        return register.get(clockBitIndex);
    }

    public boolean get(int bitIndex) {
        return register.get(bitIndex);
    }

    public boolean getLastBit() {
        return register.get(length - 1);
    }

    private boolean xorTappingBits() {
        boolean result = false;
        for (int i : tappingBitsIndices) {
            result ^= register.get(i);
        }
        return result;
    }

    private boolean pushBit(boolean bit) {
        boolean discardedBit = rightShift();
        register.set(0, bit);
        return discardedBit;
    }

    private boolean rightShift() {
        boolean discardedBit = get(length - 1);
        for (int i = length - 1; i > 0; --i) {
            register.set(i, get(i - 1));
        }
        register.set(0, false);
        return discardedBit;
    }

    @Override
    public String toString() {
        return register.toString();
    }
}
