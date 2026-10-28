package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WinternitzSignatureTest {

    private static final byte[] MESSAGE = "hello winternitz".getBytes(StandardCharsets.UTF_8);

    @Test
    void testValidSignatureVerifies() {
        WinternitzSignature keyPair = new WinternitzSignature();

        byte[][] signature = keyPair.sign(MESSAGE);

        assertTrue(WinternitzSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), 16));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 16, 256})
    void testSignAndVerifyForEverySupportedW(int w) {
        WinternitzSignature keyPair = new WinternitzSignature(w);

        byte[][] signature = keyPair.sign(MESSAGE);

        assertTrue(WinternitzSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), w));
    }

    @ParameterizedTest
    @CsvSource({"4, 128, 5", "16, 64, 3", "256, 32, 2"})
    void testParametersAreDerivedFromW(int w, int expectedLen1, int expectedLen2) {
        WinternitzSignature.Parameters parameters = WinternitzSignature.Parameters.forW(w);

        assertEquals(expectedLen1, parameters.len1());
        assertEquals(expectedLen2, parameters.len2());
        assertEquals(expectedLen1 + expectedLen2, parameters.len());
    }

    @Test
    void testDefaultSignatureHas67Values() {
        WinternitzSignature keyPair = new WinternitzSignature();

        byte[][] signature = keyPair.sign(MESSAGE);

        assertEquals(67, signature.length);
        assertEquals(67, keyPair.getPublicKey().length);
        for (byte[] value : signature) {
            assertEquals(32, value.length);
        }
    }

    @Test
    void testTamperedMessageFailsVerification() {
        WinternitzSignature keyPair = new WinternitzSignature();
        byte[] message = MESSAGE.clone();
        byte[][] signature = keyPair.sign(message);

        message[0] ^= 0x01;

        assertFalse(WinternitzSignature.verify(message, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testTamperedSignatureFailsVerification() {
        WinternitzSignature keyPair = new WinternitzSignature();
        byte[][] signature = keyPair.sign(MESSAGE);

        signature[10][0] ^= 0x01;

        assertFalse(WinternitzSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testDifferentPublicKeyDoesNotVerify() {
        WinternitzSignature keyPair1 = new WinternitzSignature();
        WinternitzSignature keyPair2 = new WinternitzSignature();

        byte[][] signature = keyPair1.sign(MESSAGE);

        assertFalse(WinternitzSignature.verify(MESSAGE, signature, keyPair2.getPublicKey(), 16));
    }

    @Test
    void testVerifyingWithDifferentWThrowsException() {
        WinternitzSignature keyPair = new WinternitzSignature(16);
        byte[][] signature = keyPair.sign(MESSAGE);
        byte[][] publicKey = keyPair.getPublicKey();

        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, signature, publicKey, 4));
    }

    @Test
    void testSecondSignatureThrowsException() {
        WinternitzSignature keyPair = new WinternitzSignature();
        byte[] second = "second message".getBytes(StandardCharsets.UTF_8);

        keyPair.sign(MESSAGE);

        assertThrows(IllegalStateException.class, () -> keyPair.sign(second));
    }

    @Test
    void testNullAndMalformedInput() {
        WinternitzSignature keyPair = new WinternitzSignature();
        byte[][] publicKey = keyPair.getPublicKey();
        byte[][] signature = keyPair.sign(MESSAGE);
        byte[][] tooShort = Arrays.copyOf(signature, 66);
        byte[][] wrongValueLength = signature.clone();
        wrongValueLength[0] = new byte[31];
        byte[][] nullValue = signature.clone();
        nullValue[0] = null;

        assertThrows(IllegalArgumentException.class, () -> new WinternitzSignature().sign(null));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(null, signature, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, null, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, tooShort, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, wrongValueLength, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, nullValue, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, signature, null, 16));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, signature, tooShort, 16));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 15, -16, 2, 8, 512})
    void testUnsupportedWThrowsException(int w) {
        assertThrows(IllegalArgumentException.class, () -> new WinternitzSignature(w));
        assertThrows(IllegalArgumentException.class, () -> WinternitzSignature.verify(MESSAGE, new byte[67][32], new byte[67][32], w));
    }

    @Test
    void testModifyingReturnedPublicKeyDoesNotChangeInternalState() {
        WinternitzSignature keyPair = new WinternitzSignature();
        byte[][] signature = keyPair.sign(MESSAGE);

        byte[][] exposed = keyPair.getPublicKey();
        exposed[0][0] ^= 0x01;
        exposed[1] = new byte[32];

        assertTrue(WinternitzSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testEmptyAndLongMessages() {
        byte[] empty = new byte[0];
        byte[] longMessage = new byte[8192];
        for (int i = 0; i < longMessage.length; i++) {
            longMessage[i] = (byte) i;
        }
        WinternitzSignature keyPair1 = new WinternitzSignature();
        WinternitzSignature keyPair2 = new WinternitzSignature();

        byte[][] emptySignature = keyPair1.sign(empty);
        byte[][] longSignature = keyPair2.sign(longMessage);

        assertTrue(WinternitzSignature.verify(empty, emptySignature, keyPair1.getPublicKey(), 16));
        assertTrue(WinternitzSignature.verify(longMessage, longSignature, keyPair2.getPublicKey(), 16));
    }

    @Test
    void testDifferentMessagesProduceDifferentSignatures() {
        WinternitzSignature keyPair1 = new WinternitzSignature();
        WinternitzSignature keyPair2 = new WinternitzSignature();

        byte[][] signature1 = keyPair1.sign("first message".getBytes(StandardCharsets.UTF_8));
        byte[][] signature2 = keyPair2.sign("second message".getBytes(StandardCharsets.UTF_8));

        assertFalse(Arrays.deepEquals(signature1, signature2));
    }
}
