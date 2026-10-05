package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HorsSignatureTest {

    private static final byte[] MESSAGE = "hello hors".getBytes(StandardCharsets.UTF_8);

    @Test
    void testValidSignatureVerifies() {
        HorsSignature keyPair = new HorsSignature();

        byte[][] signature = keyPair.sign(MESSAGE);

        assertTrue(HorsSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), keyPair.getK()));
    }

    @Test
    void testSameKeySignsSeveralMessages() {
        HorsSignature keyPair = new HorsSignature();
        byte[][] publicKey = keyPair.getPublicKey();

        for (int i = 0; i < 5; i++) {
            byte[] message = ("message " + i).getBytes(StandardCharsets.UTF_8);
            byte[][] signature = keyPair.sign(message);

            assertTrue(HorsSignature.verify(message, signature, publicKey, keyPair.getK()));
        }
    }

    @Test
    void testSigningIsDeterministic() {
        HorsSignature keyPair = new HorsSignature();

        assertArrayEquals(keyPair.sign(MESSAGE), keyPair.sign(MESSAGE));
    }

    @Test
    void testTamperedMessageFailsVerification() {
        HorsSignature keyPair = new HorsSignature();
        byte[][] signature = keyPair.sign(MESSAGE);
        byte[] tampered = MESSAGE.clone();

        tampered[0] ^= 0x01;

        assertFalse(HorsSignature.verify(tampered, signature, keyPair.getPublicKey(), keyPair.getK()));
    }

    @Test
    void testTamperedSignatureFailsVerification() {
        HorsSignature keyPair = new HorsSignature();
        byte[][] signature = keyPair.sign(MESSAGE);

        signature[3][0] ^= 0x01;

        assertFalse(HorsSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), keyPair.getK()));
    }

    @Test
    void testDifferentPublicKeyDoesNotVerify() {
        HorsSignature keyPair1 = new HorsSignature();
        HorsSignature keyPair2 = new HorsSignature();

        byte[][] signature = keyPair1.sign(MESSAGE);

        assertFalse(HorsSignature.verify(MESSAGE, signature, keyPair2.getPublicKey(), keyPair1.getK()));
    }

    @Test
    void testMessageIndicesWithByteAlignedTau() {
        byte[] digest = new byte[32];
        digest[0] = (byte) 0xFF;
        digest[1] = 0x00;
        digest[2] = (byte) 0x80;
        digest[3] = 0x7F;

        assertArrayEquals(new int[] {255, 0, 128, 127}, HorsSignature.messageIndices(digest, 4, 8));
    }

    @Test
    void testMessageIndicesAcrossByteBoundaries() {
        byte[] first = new byte[32];
        first[0] = (byte) 0xFF;
        first[1] = (byte) 0xC0;
        byte[] second = new byte[32];
        second[1] = 0x7F;
        second[2] = (byte) 0xF0;

        // 11111111 11|000000 0000 -> [1023, 0]
        assertArrayEquals(new int[] {1023, 0}, HorsSignature.messageIndices(first, 2, 10));
        // 00000000 01|111111 1111 -> [1, 1023]
        assertArrayEquals(new int[] {1, 1023}, HorsSignature.messageIndices(second, 2, 10));
    }

    @Test
    void testSignatureRevealsSecretsAtDerivedIndices() throws NoSuchAlgorithmException {
        HorsSignature keyPair = new HorsSignature();
        byte[][] publicKey = keyPair.getPublicKey();

        byte[][] signature = keyPair.sign(MESSAGE);

        int[] indices = HorsSignature.messageIndices(sha256(MESSAGE), 16, 10);
        byte[][] expectedHashes = new byte[16][];
        byte[][] actualHashes = new byte[signature.length][];
        int[] expectedLengths = new int[16];
        int[] actualLengths = new int[signature.length];
        Arrays.fill(expectedLengths, 32);
        for (int j = 0; j < signature.length; j++) {
            actualHashes[j] = sha256(signature[j]);
            actualLengths[j] = signature[j].length;
        }
        for (int j = 0; j < 16; j++) {
            expectedHashes[j] = publicKey[indices[j]];
        }

        assertArrayEquals(expectedLengths, actualLengths);
        assertArrayEquals(expectedHashes, actualHashes);
    }

    @ParameterizedTest
    @CsvSource({"16, 4", "256, 32", "1024, 16", "4096, 21"})
    void testSignAndVerifyForDifferentParameters(int t, int k) {
        HorsSignature keyPair = new HorsSignature(t, k);

        byte[][] signature = keyPair.sign(MESSAGE);

        assertArrayEquals(new int[] {k, k, t}, new int[] {signature.length, keyPair.getK(), keyPair.getPublicKey().length});
        assertTrue(HorsSignature.verify(MESSAGE, signature, keyPair.getPublicKey(), k));
    }

    @ParameterizedTest
    @CsvSource({"0, 16", "8, 2", "100, 4", "131072, 4", "1024, 0", "1024, 26", "1024, -1", "1024, 2147483647"})
    void testUnsupportedParametersThrowException(int t, int k) {
        assertThrows(IllegalArgumentException.class, () -> new HorsSignature(t, k));
    }

    @Test
    void testNullAndMalformedInput() {
        HorsSignature keyPair = new HorsSignature(16, 4);
        byte[][] publicKey = keyPair.getPublicKey();
        byte[][] signature = keyPair.sign(MESSAGE);
        byte[][] nullValue = signature.clone();
        nullValue[0] = null;
        byte[][] shortValue = signature.clone();
        shortValue[0] = new byte[31];
        byte[][] tooManyValues = new byte[65][32];

        assertThrows(IllegalArgumentException.class, () -> keyPair.sign(null));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(null, signature, publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, null, publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, nullValue, publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, shortValue, publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, new byte[0][], publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, tooManyValues, publicKey, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, signature, null, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, signature, new byte[15][32], 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, signature, nullValue, 4));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, signature, publicKey, 3));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, signature, publicKey, 0));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, tooManyValues, publicKey, 65));
        assertThrows(IllegalArgumentException.class, () -> HorsSignature.messageIndices(new byte[1], 2, 8));
    }

    @Test
    void testTruncatedSignatureIsRejected() {
        HorsSignature keyPair = new HorsSignature();
        byte[][] truncated = Arrays.copyOf(keyPair.sign(MESSAGE), 1);

        assertThrows(IllegalArgumentException.class, () -> HorsSignature.verify(MESSAGE, truncated, keyPair.getPublicKey(), keyPair.getK()));
    }

    @Test
    void testModifyingReturnedArraysDoesNotChangeInternalState() {
        HorsSignature keyPair = new HorsSignature();
        byte[][] expectedPublicKey = keyPair.getPublicKey();
        byte[][] expectedSignature = keyPair.sign(MESSAGE);

        keyPair.getPublicKey()[0][0] ^= 0x01;
        keyPair.getPublicKey()[1] = new byte[32];
        keyPair.sign(MESSAGE)[0][0] ^= 0x01;

        assertArrayEquals(expectedPublicKey, keyPair.getPublicKey());
        assertArrayEquals(expectedSignature, keyPair.sign(MESSAGE));
        assertTrue(HorsSignature.verify(MESSAGE, expectedSignature, keyPair.getPublicKey(), keyPair.getK()));
    }

    private static byte[] sha256(byte[] data) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(data);
    }
}
