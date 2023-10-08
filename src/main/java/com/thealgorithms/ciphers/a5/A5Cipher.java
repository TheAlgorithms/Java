package com.thealgorithms.ciphers.a5;

import java.util.BitSet;

// https://en.wikipedia.org/wiki/A5/1
public class A5Cipher {

    private final A5KeyStreamGenerator keyStreamGenerator;
    private static final int KEY_STREAM_LENGTH = 228; // 28.5 bytes so we need to pad bytes or something

    public A5Cipher(BitSet sessionKey, BitSet frameCounter) {
        keyStreamGenerator = new A5KeyStreamGenerator();
        keyStreamGenerator.initialize(sessionKey, frameCounter);
    }

    public BitSet encrypt(BitSet plainTextBits) {
        // create a copy
        var result = new BitSet(KEY_STREAM_LENGTH);
        result.xor(plainTextBits);

        var key = keyStreamGenerator.getNextKeyStream();
        result.xor(key);

        return result;
    }

    public void resetCounter() {
        keyStreamGenerator.reInitialize();
    }
}
