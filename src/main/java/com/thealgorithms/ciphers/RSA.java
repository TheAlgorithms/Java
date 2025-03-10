package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA is an asymmetric cryptographic algorithm used for secure data encryption and decryption.
 * It relies on a pair of keys: a public key (used for encryption) and a private key
 * (used for decryption). The algorithm is based on the difficulty of factoring large prime numbers.
 *
 * This implementation includes key generation, encryption, and decryption methods that can handle both
 * text-based messages and BigInteger inputs. For more details on RSA:
 * <a href="https://en.wikipedia.org/wiki/RSA_(cryptosystem)">RSA Cryptosystem - Wikipedia</a>.
 *
 * Example Usage:
 * <pre>
 * RSA rsa = new RSA(1024);
 * String encryptedMessage = rsa.encrypt("Hello RSA!");
 * String decryptedMessage = rsa.decrypt(encryptedMessage);
 * System.out.println(decryptedMessage);  // Output: Hello RSA!
 * </pre>
 *
 * Note: The key size directly affects the security and performance of the RSA algorithm.
 * Larger keys are more secure but slower to compute.
 *
 * @author Nguyen Duy Tiep
 * @version 23-Oct-17
 */
public class RSA {

    private BigInteger modulus;
    private BigInteger privateKey;
    private BigInteger publicKey;

    /**
     * Constructor that generates RSA keys with the specified number of bits.
     *
     * @param bits The bit length of the keys to be generated. Common sizes include 512, 1024, 2048, etc.
     */
    public RSA(int bits) {
        generateKeys(bits);
    }

    /**
     * Encrypts a text message using the RSA public key.
     *
     * @param message The plaintext message to be encrypted.
     * @throws IllegalArgumentException If the message is empty.
     * @return The encrypted message represented as a String.
     */
    public synchronized String encrypt(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message is empty");
        }
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
    }

    /**
     * Encrypts a BigInteger message using the RSA public key.
     *
     * @param message The plaintext message as a BigInteger.
     * @return The encrypted message as a BigInteger.
     */
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    /**
     * Decrypts an encrypted message (as String) using the RSA private key.
     *
     * @param encryptedMessage The encrypted message to be decrypted, represented as a String.
     * @throws IllegalArgumentException If the message is empty.
     * @return The decrypted plaintext message as a String.
     */
    public synchronized String decrypt(String encryptedMessage) {
        if (encryptedMessage.isEmpty()) {
            throw new IllegalArgumentException("Message is empty");
        }
        return new String((new BigInteger(encryptedMessage)).modPow(privateKey, modulus).toByteArray());
    }

    /**
     * Decrypts an encrypted BigInteger message using the RSA private key.
     *
     * @param encryptedMessage The encrypted message as a BigInteger.
     * @return The decrypted plaintext message as a BigInteger.
     */
    public synchronized BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    /**
     * Generates a new RSA key pair (public and private keys) with the specified bit length.
     * Steps:
     * 1. Generate two large prime numbers p and q.
     * 2. Compute the modulus n = p * q.
     * 3. Compute Euler's totient function: φ(n) = (p-1) * (q-1).
     * 4. Choose a public key e (starting from 3) that is coprime with φ(n).
     * 5. Compute the private key d as the modular inverse of e mod φ(n).
     * The public key is (e, n) and the private key is (d, n).
     *
     * @param bits The bit length of the keys to be generated.
     */
    public final synchronized void generateKeys(int bits) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, random);
        BigInteger q = new BigInteger(bits / 2, 100, random);
        modulus = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = BigInteger.valueOf(3L);
        while (phi.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(BigInteger.TWO);
        }

        privateKey = publicKey.modInverse(phi);
    }
}
