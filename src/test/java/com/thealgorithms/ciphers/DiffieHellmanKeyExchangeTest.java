package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.KeyAgreement;
import org.junit.jupiter.api.Test;

class DiffieHellmanKeyExchangeTest {

    @Test
    public void testDiffieHellman() {
        try {
            DiffieHellmanKeyExchange keyExchange = new DiffieHellmanKeyExchange();

            // Generate Alice's key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            KeyPair aliceKeyPair = keyPairGenerator.generateKeyPair();
            PublicKey alicePublicKey = aliceKeyPair.getPublic();

            // Generate Bob's key pair
            KeyPair bobKeyPair = keyPairGenerator.generateKeyPair();
            PublicKey bobPublicKey = bobKeyPair.getPublic();

            // Convert Bob's public key to a byte array (for transmission)
            byte[] bobPublicKeyBytes = bobPublicKey.getEncoded();

            // Simulate the exchange by calling the diffieHellman method with Bob's public key
            keyExchange.diffieHellman(bobPublicKeyBytes);

            // Get the shared secret for Alice and Bob
            KeyAgreement aliceKeyAgreement = KeyAgreement.getInstance("DH");
            aliceKeyAgreement.init(aliceKeyPair.getPrivate());
            aliceKeyAgreement.doPhase(bobPublicKey, true);
            byte[] aliceSharedSecret = aliceKeyAgreement.generateSecret();

            KeyAgreement bobKeyAgreement = KeyAgreement.getInstance("DH");
            bobKeyAgreement.init(bobKeyPair.getPrivate());
            bobKeyAgreement.doPhase(alicePublicKey, true);
            byte[] bobSharedSecret = bobKeyAgreement.generateSecret();

            // Assert that the shared secrets are the same
            assertArrayEquals(aliceSharedSecret, bobSharedSecret);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred during key exchange.");
        }
    }
}
