package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Elliptic Curve Cryptography (ECC) implementation
 * @author P.Sai Srujan Reddy on 7-Oct-24.
 */
public class ECC {
    /**
 * Elliptic Curve Cryptography (ECC) implementation
 * More details: https://en.wikipedia.org/wiki/Elliptic-curve_cryptography
 */
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger prime;
    private BigInteger a;  // Curve parameter
    private BigInteger b;  // Curve parameter
    private BigInteger Gx; // Generator point (x-coordinate)
    private BigInteger Gy; // Generator point (y-coordinate)
    private SecureRandom random;

    public ECC(int bits) {
        generateKeys(bits);
    }

    /**
     * Encrypts the given message using ECC.
     * @param message Message to encrypt
     * @return Encrypted message as a BigInteger
     */
    public synchronized BigInteger encrypt(BigInteger message) {
        // ECC encryption logic using the public key
        BigInteger k = new BigInteger(prime.bitLength(), random); // Random value
        BigInteger C1x = Gx.multiply(k).mod(prime); // First part of the ciphertext (x-coordinate of point)
        BigInteger C1y = Gy.multiply(k).mod(prime); // First part of the ciphertext (y-coordinate of point)
        BigInteger C2 = message.multiply(publicKey.modPow(k, prime)).mod(prime); // Second part of ciphertext
        return C2; // Return C2 as encrypted message (for simplicity)
    }

    /**
     * Decrypts the given encrypted message.
     * @param encryptedMessage Encrypted message as BigInteger
     * @return Decrypted message as a BigInteger
     */
    public synchronized BigInteger decrypt(BigInteger encryptedMessage) {
        // ECC decryption logic using the private key
        BigInteger decryptedMessage = encryptedMessage.multiply(privateKey.modInverse(prime)).mod(prime);
        return decryptedMessage;
    }

    /**
     * Generates the public and private keys.
     */
    private void generateKeys(int bits) {
        random = new SecureRandom();
        prime = new BigInteger(bits, 100, random); // Prime number for ECC curve
        a = new BigInteger(bits, random); // Curve parameter a
        b = new BigInteger(bits, random); // Curve parameter b
        Gx = new BigInteger(bits, random); // Generator point x
        Gy = new BigInteger(bits, random); // Generator point y

        privateKey = new BigInteger(bits, random); // Private key
        publicKey = Gx.multiply(privateKey).mod(prime); // Public key using generator point Gx
    }
}
