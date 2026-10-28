package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MerkleSignatureSchemeTest {

    private static final byte[] MESSAGE = "hello merkle".getBytes(StandardCharsets.UTF_8);

    @Test
    void testValidSignatureVerifies() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme();

        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);

        assertTrue(MerkleSignatureScheme.verify(MESSAGE, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testEveryLeafSignsAndVerifies() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(4, 16);
        byte[] publicKey = keyPair.getPublicKey();

        for (int i = 0; i < 16; i++) {
            byte[] message = ("message " + i).getBytes(StandardCharsets.UTF_8);
            MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(message);

            assertEquals(i, signature.getLeafIndex());
            assertEquals(4, signature.getAuthPath().length);
            assertTrue(MerkleSignatureScheme.verify(message, signature, publicKey, 16));
        }
    }

    @Test
    void testSigningBeyondCapacityThrowsException() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(2, 16);
        for (int i = 0; i < 4; i++) {
            keyPair.sign(MESSAGE);
        }

        assertThrows(IllegalStateException.class, () -> keyPair.sign(MESSAGE));
    }

    @Test
    void testRemainingSignaturesDecreases() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(2, 16);

        for (int expected = 4; expected > 0; expected--) {
            assertEquals(expected, keyPair.remainingSignatures());
            keyPair.sign(MESSAGE);
        }
        assertEquals(0, keyPair.remainingSignatures());
    }

    @Test
    void testTamperedMessageFailsVerification() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme();
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);
        byte[] tampered = MESSAGE.clone();

        tampered[0] ^= 0x01;

        assertFalse(MerkleSignatureScheme.verify(tampered, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testTamperedWotsSignatureFailsVerification() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme();
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);
        byte[][] wotsSignature = signature.getWotsSignature();

        wotsSignature[5][0] ^= 0x01;
        MerkleSignatureScheme.MerkleSignature tampered = new MerkleSignatureScheme.MerkleSignature(signature.getLeafIndex(), wotsSignature, signature.getWotsPublicKey(), signature.getAuthPath());

        assertFalse(MerkleSignatureScheme.verify(MESSAGE, tampered, keyPair.getPublicKey(), 16));
    }

    @Test
    void testTamperedAuthPathFailsVerification() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme();
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);
        byte[][] authPath = signature.getAuthPath();

        authPath[2][0] ^= 0x01;
        MerkleSignatureScheme.MerkleSignature tampered = new MerkleSignatureScheme.MerkleSignature(signature.getLeafIndex(), signature.getWotsSignature(), signature.getWotsPublicKey(), authPath);

        assertFalse(MerkleSignatureScheme.verify(MESSAGE, tampered, keyPair.getPublicKey(), 16));
    }

    @Test
    void testChangedLeafIndexFailsVerification() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme();
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);

        MerkleSignatureScheme.MerkleSignature tampered = new MerkleSignatureScheme.MerkleSignature(signature.getLeafIndex() + 1, signature.getWotsSignature(), signature.getWotsPublicKey(), signature.getAuthPath());

        assertFalse(MerkleSignatureScheme.verify(MESSAGE, tampered, keyPair.getPublicKey(), 16));
    }

    @Test
    void testDifferentPublicKeyDoesNotVerify() {
        MerkleSignatureScheme keyPair1 = new MerkleSignatureScheme();
        MerkleSignatureScheme keyPair2 = new MerkleSignatureScheme();

        MerkleSignatureScheme.MerkleSignature signature = keyPair1.sign(MESSAGE);

        assertFalse(MerkleSignatureScheme.verify(MESSAGE, signature, keyPair2.getPublicKey(), 16));
    }

    @ParameterizedTest
    @CsvSource({"2, 4", "2, 16", "4, 4", "4, 16", "6, 4", "6, 16"})
    void testSignAndVerifyForDifferentParameters(int h, int w) {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(h, w);

        MerkleSignatureScheme.MerkleSignature first = keyPair.sign(MESSAGE);
        MerkleSignatureScheme.MerkleSignature second = keyPair.sign(MESSAGE);

        assertEquals((1 << h) - 2, keyPair.remainingSignatures());
        assertEquals(h, first.getAuthPath().length);
        assertTrue(MerkleSignatureScheme.verify(MESSAGE, first, keyPair.getPublicKey(), w));
        assertTrue(MerkleSignatureScheme.verify(MESSAGE, second, keyPair.getPublicKey(), w));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 11, -4})
    void testUnsupportedHeightThrowsException(int h) {
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme(h, 16));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 15})
    void testUnsupportedWThrowsException(int w) {
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme(2, w));
    }

    @Test
    void testNullAndMalformedInput() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(2, 16);
        byte[] publicKey = keyPair.getPublicKey();
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);
        byte[][] wotsSignature = signature.getWotsSignature();
        byte[][] wotsPublicKey = signature.getWotsPublicKey();
        byte[][] authPath = signature.getAuthPath();

        assertThrows(IllegalArgumentException.class, () -> keyPair.sign(null));
        assertThrows(IllegalArgumentException.class, () -> MerkleSignatureScheme.verify(null, signature, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> MerkleSignatureScheme.verify(MESSAGE, null, publicKey, 16));
        assertThrows(IllegalArgumentException.class, () -> MerkleSignatureScheme.verify(MESSAGE, signature, null, 16));
        assertThrows(IllegalArgumentException.class, () -> MerkleSignatureScheme.verify(MESSAGE, signature, new byte[31], 16));
        assertThrows(IllegalArgumentException.class, () -> MerkleSignatureScheme.verify(MESSAGE, signature, publicKey, 4));

        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, null, wotsPublicKey, authPath));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, null, authPath));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, wotsPublicKey, null));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, wotsPublicKey, new byte[1][32]));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, wotsPublicKey, new byte[11][32]));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, wotsPublicKey, new byte[2][31]));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, wotsSignature, wotsPublicKey, new byte[2][]));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(0, new byte[67][], wotsPublicKey, authPath));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(-1, wotsSignature, wotsPublicKey, authPath));
        assertThrows(IllegalArgumentException.class, () -> new MerkleSignatureScheme.MerkleSignature(4, wotsSignature, wotsPublicKey, authPath));
    }

    @Test
    void testModifyingReturnedArraysDoesNotChangeInternalState() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(2, 16);
        MerkleSignatureScheme.MerkleSignature signature = keyPair.sign(MESSAGE);

        keyPair.getPublicKey()[0] ^= 0x01;
        signature.getWotsSignature()[0][0] ^= 0x01;
        signature.getWotsPublicKey()[0][0] ^= 0x01;
        signature.getAuthPath()[0][0] ^= 0x01;

        assertTrue(MerkleSignatureScheme.verify(MESSAGE, signature, keyPair.getPublicKey(), 16));
    }

    @Test
    void testSignaturesUseDifferentLeaves() {
        MerkleSignatureScheme keyPair = new MerkleSignatureScheme(2, 16);

        MerkleSignatureScheme.MerkleSignature first = keyPair.sign(MESSAGE);
        MerkleSignatureScheme.MerkleSignature second = keyPair.sign(MESSAGE);

        assertEquals(0, first.getLeafIndex());
        assertEquals(1, second.getLeafIndex());
    }
}
