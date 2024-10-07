package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class ECCTest {

    ECC ecc = new ECC(256); // ECC with 256-bit key

    @Test
    void testECC() {
        // given
        BigInteger message = new BigInteger("123456789"); // Message to encrypt

        // when
        BigInteger cipherText = ecc.encrypt(message);
        BigInteger decryptedMessage = ecc.decrypt(cipherText);

        // then
        assertEquals(message, decryptedMessage);
    }
}
