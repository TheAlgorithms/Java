package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class LamportSignatureTest {

    @Test
    void testValidSignatureVerifies() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[] message = "hello lamport".getBytes(StandardCharsets.UTF_8);

        byte[] signature = keyPair.sign(message);

        assertTrue(keyPair.verify(message, signature));
    }

    @Test
    void testTamperedMessageFailsVerification() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[] message = "hello lamport".getBytes(StandardCharsets.UTF_8);
        byte[] signature = keyPair.sign(message);

        assertFalse(keyPair.verify("hello lamport!".getBytes(StandardCharsets.UTF_8), signature));
    }

    @Test
    void testTamperedSignatureFailsVerification() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[] message = "hello lamport".getBytes(StandardCharsets.UTF_8);
        byte[] signature = keyPair.sign(message);

        signature[0] = (byte) (signature[0] ^ 0xFF);

        assertFalse(keyPair.verify(message, signature));
    }

    @Test
    void testDifferentKeyPairDoesNotVerify() {
        LamportSignature.KeyPair keyPair1 = LamportSignature.generateKeyPair();
        LamportSignature.KeyPair keyPair2 = LamportSignature.generateKeyPair();
        byte[] message = "post-quantum".getBytes(StandardCharsets.UTF_8);

        byte[] signature = keyPair1.sign(message);

        assertFalse(keyPair2.verify(message, signature));
    }

    @Test
    void testSecondSignatureThrowsException() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[] first = "first message".getBytes(StandardCharsets.UTF_8);
        byte[] second = "second message".getBytes(StandardCharsets.UTF_8);

        keyPair.sign(first);

        assertThrows(IllegalStateException.class, () -> keyPair.sign(second));
    }

    @Test
    void testNullAndMalformedInput() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[] validMessage = "message".getBytes(StandardCharsets.UTF_8);
        byte[] validSignature = keyPair.sign(validMessage);

        assertThrows(IllegalArgumentException.class, () -> keyPair.sign(null));
        assertThrows(IllegalArgumentException.class, () -> keyPair.verify(null, validSignature));
        assertThrows(IllegalArgumentException.class, () -> keyPair.verify(validMessage, null));
        assertThrows(IllegalArgumentException.class, () -> keyPair.verify(validMessage, new byte[1]));
    }

    @Test
    void testPublicKeyMatchesPrivateKeyHash() {
        LamportSignature.KeyPair keyPair = LamportSignature.generateKeyPair();
        byte[][][] publicKey = keyPair.getPublicKey();
        byte[][][] privateKey = keyPair.getPrivateKey();

        for (int bit = 0; bit < 256; bit++) {
            for (int value = 0; value < 2; value++) {
                byte[] hash = LamportSignature.sha256(privateKey[bit][value]);
                assertArrayEquals(hash, publicKey[bit][value]);
            }
        }
    }
}
