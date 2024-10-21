package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BaconianCipherTest {

    BaconianCipher baconianCipher = new BaconianCipher();

    @Test
    void baconianCipherEncryptTest() {
        // given
        String plaintext = "MEET AT DAWN";

        // when
        String cipherText = baconianCipher.encrypt(plaintext);

        // then
        assertEquals("ABBAAAABAAAABAABAABBAAAAABAABBAAABBAAAAABABBAABBAB", cipherText);
    }

    @Test
    void baconianCipherDecryptTest() {
        // given
        String ciphertext = "ABBAAAABAAAABAABAABBAAAAABAABBAAABBAAAAABABBAABBAB";

        // when
        String plainText = baconianCipher.decrypt(ciphertext);

        // then
        assertEquals("MEETATDAWN", plainText);
    }
}
