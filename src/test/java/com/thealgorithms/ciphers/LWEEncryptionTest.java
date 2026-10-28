package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LWEEncryptionTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void testBitRoundTrip(int bit) {
        LWEEncryption lwe = new LWEEncryption();

        for (int i = 0; i < 100; i++) {
            assertEquals(bit, lwe.decryptBit(lwe.encryptBit(bit)));
        }
    }

    @Test
    void testTextMessageRoundTrip() {
        LWEEncryption lwe = new LWEEncryption();
        byte[] message = "Hello, PQC!".getBytes(StandardCharsets.UTF_8);

        assertArrayEquals(message, lwe.decrypt(lwe.encrypt(message)));
    }

    @Test
    void testRandomMessageRoundTrip() {
        LWEEncryption lwe = new LWEEncryption();
        byte[] message = new byte[1024];
        new SecureRandom().nextBytes(message);

        assertArrayEquals(message, lwe.decrypt(lwe.encrypt(message)));
    }

    @Test
    void testEncryptionIsRandomized() {
        LWEEncryption lwe = new LWEEncryption();

        LWEEncryption.Ciphertext first = lwe.encryptBit(1);
        LWEEncryption.Ciphertext second = lwe.encryptBit(1);

        assertFalse(Arrays.equals(toArray(first), toArray(second)));
    }

    @Test
    void testWrongKeyDoesNotDecrypt() {
        LWEEncryption sender = new LWEEncryption();
        LWEEncryption other = new LWEEncryption();
        byte[] message = "sixteen byte msg".getBytes(StandardCharsets.UTF_8);

        byte[] decrypted = other.decrypt(sender.encrypt(message));

        assertFalse(Arrays.equals(message, decrypted));
    }

    @ParameterizedTest
    @ValueSource(bytes = {(byte) 0b10110001, (byte) 0xFF, (byte) 0x80, 0x00, 0x01, 0x7F})
    void testSingleByteBitOrder(byte value) {
        LWEEncryption lwe = new LWEEncryption();

        assertArrayEquals(new byte[] {value}, lwe.decrypt(lwe.encrypt(new byte[] {value})));
    }

    @Test
    void testInvalidInput() {
        LWEEncryption lwe = new LWEEncryption();
        LWEEncryption.Ciphertext[] sevenCiphertexts = Arrays.copyOf(lwe.encrypt(new byte[] {1}), 7);
        LWEEncryption.Ciphertext[] withNull = lwe.encrypt(new byte[] {1});
        withNull[3] = null;

        assertThrows(IllegalArgumentException.class, () -> lwe.encryptBit(2));
        assertThrows(IllegalArgumentException.class, () -> lwe.encryptBit(-1));
        assertThrows(IllegalArgumentException.class, () -> lwe.encrypt(null));
        assertThrows(IllegalArgumentException.class, () -> lwe.encrypt(new byte[0]));
        assertThrows(IllegalArgumentException.class, () -> lwe.decryptBit(null));
        assertThrows(IllegalArgumentException.class, () -> lwe.decrypt(null));
        assertThrows(IllegalArgumentException.class, () -> lwe.decrypt(new LWEEncryption.Ciphertext[0]));
        assertThrows(IllegalArgumentException.class, () -> lwe.decrypt(sevenCiphertexts));
        assertThrows(IllegalArgumentException.class, () -> lwe.decrypt(withNull));
        assertThrows(IllegalArgumentException.class, () -> new LWEEncryption.Ciphertext(null, 0));
        assertThrows(IllegalArgumentException.class, () -> new LWEEncryption.Ciphertext(new int[31], 0));
        assertThrows(IllegalArgumentException.class, () -> new LWEEncryption.Ciphertext(new int[33], 0));
    }

    @Test
    void testCiphertextIsImmutable() {
        int[] u = new int[32];
        Arrays.fill(u, 7);
        LWEEncryption.Ciphertext ciphertext = new LWEEncryption.Ciphertext(u, 5);
        int[] expected = u.clone();

        u[0] = 100;
        ciphertext.getU()[1] = 200;

        assertArrayEquals(expected, ciphertext.getU());
        assertEquals(5, ciphertext.getV());
    }

    @Test
    void testDecryptionBoundCheck() {
        LWEEncryption.checkDecryptionBound(128, 3, 3329);

        assertThrows(IllegalStateException.class, () -> LWEEncryption.checkDecryptionBound(128, 7, 3329));
    }

    // u followed by v, so that two ciphertexts can be compared with a single Arrays.equals
    private static int[] toArray(LWEEncryption.Ciphertext ciphertext) {
        int[] u = ciphertext.getU();
        int[] values = Arrays.copyOf(u, u.length + 1);
        values[u.length] = ciphertext.getV();
        return values;
    }
}
