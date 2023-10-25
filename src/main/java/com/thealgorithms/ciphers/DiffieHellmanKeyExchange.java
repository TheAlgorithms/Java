package com.thealgorithms.ciphers;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import java.util.Base64;

/*
 * A Java implementation of Diffie-Hellman Key Exchange
 */

public class DiffieHellmanKeyExchange {

    public static void diffieHellman(String[] args) throws Exception {
        // Generate Alice's key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman");
        KeyPair aliceKeyPair = keyPairGenerator.generateKeyPair();
        PrivateKey alicePrivateKey = aliceKeyPair.getPrivate();
        PublicKey alicePublicKey = aliceKeyPair.getPublic();

        // Convert Alice's public key to a byte array (for transmission)
        byte[] alicePublicKeyBytes = alicePublicKey.getEncoded();

        // In a real scenario, transmit alicePublicKeyBytes to Bob
        // Bob should have his own key pair, and he sends his public key to Alice

        // Simulate receiving Bob's public key (in a real scenario, this is done over the network)
        // Here, we'll convert it from a byte array to a PublicKey object
        // byte[] data = { 0x48, 0x65, 0x6C, 0x6C, 0x6F };
        byte[] bobPublicKeyBytes = {}; // Load Bob's public key from transmission
        KeyFactory keyFactory = KeyFactory.getInstance("DiffieHellman");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(bobPublicKeyBytes);
        PublicKey bobPublicKey = keyFactory.generatePublic(x509KeySpec);

        // Alice computes the shared secret
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DiffieHellman");
        keyAgreement.init(alicePrivateKey);
        keyAgreement.doPhase(bobPublicKey, true);
        byte[] sharedSecret = keyAgreement.generateSecret();

        // You now have the shared secret for both Alice and Bob
        System.out.println("Shared Secret (Alice & Bob): " + Base64.getEncoder().encodeToString(sharedSecret));
    }
}
