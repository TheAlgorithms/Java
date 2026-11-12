package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ChaCha20Test {

    @Test
    void quarterRoundMatchesRfc8439Vector() {
        int[] state = {0x11111111, 0x01020304, 0x9b8d6f43, 0x01234567};

        ChaCha20.quarterRound(state, 0, 1, 2, 3);

        assertArrayEquals(new int[] {0xea2a92f4, 0xcb1cf8ce, 0x4581472e, 0x5881c4bb}, state);
    }

    @Test
    void encryptingZeroesMatchesRfc8439BlockVector() {
        byte[] key = new byte[32];
        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) i;
        }

        byte[] nonce = {0, 0, 0, 9, 0, 0, 0, 0x4a, 0, 0, 0, 0};
        byte[] expected = {0x10, (byte) 0xf1, (byte) 0xe7, (byte) 0xe4, (byte) 0xd1, 0x3b, 0x59, 0x15, 0x50, 0x0f, (byte) 0xdd, 0x1f, (byte) 0xa3, 0x20, 0x71, (byte) 0xc4, (byte) 0xc7, (byte) 0xd1, (byte) 0xf4, (byte) 0xc7, 0x33, (byte) 0xc0, 0x68, 0x03, 0x04, 0x22, (byte) 0xaa, (byte) 0x9a,
            (byte) 0xc3, (byte) 0xd4, 0x6c, 0x4e, (byte) 0xd2, (byte) 0x82, 0x64, 0x46, 0x07, (byte) 0x9f, (byte) 0xaa, 0x09, 0x14, (byte) 0xc2, (byte) 0xd7, 0x05, (byte) 0xd9, (byte) 0x8b, 0x02, (byte) 0xa2, (byte) 0xb5, 0x12, (byte) 0x9c, (byte) 0xd1, (byte) 0xde, 0x16, 0x4e, (byte) 0xb9,
            (byte) 0xcb, (byte) 0xd0, (byte) 0x83, (byte) 0xe8, (byte) 0xa2, 0x50, 0x3c, 0x4e};

        byte[] actual = ChaCha20.encrypt(new byte[64], key, nonce, 1);

        assertArrayEquals(expected, actual);
    }

    @Test
    void decryptRestoresInputAcrossMultipleBlocks() {
        byte[] input = new byte[130];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i * 3);
        }

        byte[] key = new byte[32];
        byte[] nonce = new byte[12];

        byte[] encrypted = ChaCha20.encrypt(input, key, nonce, 7);
        byte[] decrypted = ChaCha20.decrypt(encrypted, key, nonce, 7);

        assertArrayEquals(input, decrypted);
    }

    @Test
    void handlesPartialBlocksAroundBlockBoundary() {
        for (int length : new int[] {1, 63, 65}) {
            byte[] input = new byte[length];
            for (int i = 0; i < input.length; i++) {
                input[i] = (byte) (i + 1);
            }

            byte[] key = new byte[32];
            byte[] nonce = new byte[12];
            byte[] encrypted = ChaCha20.encrypt(input, key, nonce, 0);

            assertArrayEquals(input, ChaCha20.decrypt(encrypted, key, nonce, 0));
        }
    }

    @Test
    void emptyInputReturnsEmptyOutput() {
        assertArrayEquals(new byte[0], ChaCha20.encrypt(new byte[0], new byte[32], new byte[12], 1));
    }

    @Test
    void maximumCounterCanProcessOneBlock() {
        byte[] input = new byte[64];

        assertArrayEquals(input, ChaCha20.decrypt(ChaCha20.encrypt(input, new byte[32], new byte[12], 0xFFFF_FFFFL), new byte[32], new byte[12], 0xFFFF_FFFFL));
    }

    @Test
    void counterOverflowIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[65], new byte[32], new byte[12], 0xFFFF_FFFFL));
    }

    @Test
    void invalidKeyNonceAndCounterAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[1], new byte[31], new byte[12], 0));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[1], new byte[32], new byte[11], 0));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[1], new byte[32], new byte[12], -1));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[1], new byte[32], new byte[12], 0x1_0000_0000L));
    }
}
