package com.thealgorithms.hashes;

import java.security.*;
import java.util.Base64;
import java.util.Scanner;

/**
 * The DSS class demonstrates the use of the Digital Signature Algorithm (DSA) for signing and verifying messages.
 * DSA is a Federal Information Processing Standard for digital signatures, based on the mathematical concept of modular exponentiation and discrete logarithms.
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Digital_Signature_Algorithm">Digital Signature Algorithm on Wikipedia</a>
 */
public class DSS {

    public static void main(String[] args) throws Exception {
        // Generate a key pair (public and private keys) for DSA
        KeyPair keyPair = generateKeyPair();

        // Get message input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to sign: ");
        String message = scanner.nextLine();
        scanner.close();

        // Sign the message using the private key
        byte[] signature = signMessage(message.getBytes(), keyPair.getPrivate());
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        // Verify the signature using the public key
        boolean verified = verifySignature(message.getBytes(), signature, keyPair.getPublic());
        System.out.println("Signature verified: " + verified);
    }

    /**
     * Generates a DSA key pair (private and public keys).
     *
     * @return a KeyPair containing a DSA private key and public key
     * @throws NoSuchAlgorithmException if the DSA algorithm is not available
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        // Create a KeyPairGenerator for DSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

        // Initialize the KeyPairGenerator with a key size and a secure random number generator
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(1024, secureRandom);

        // Generate and return the KeyPair
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Signs a message using a private key.
     *
     * @param message    the message to be signed
     * @param privateKey the private key used to sign the message
     * @return a byte array containing the digital signature
     * @throws Exception if an error occurs during the signing process
     */
    public static byte[] signMessage(byte[] message, PrivateKey privateKey) throws Exception {
        // Create a Signature object for signing with the DSA algorithm and SHA-256
        Signature signature = Signature.getInstance("SHA256withDSA");

        // Initialize the Signature object with the private key
        signature.initSign(privateKey);

        // Add the data to be signed
        signature.update(message);

        // Sign the data and return the digital signature
        return signature.sign();
    }

    /**
     * Verifies a digital signature using a public key.
     *
     * @param message   the original message data
     * @param signature the digital signature to verify
     * @param publicKey the public key used for verification
     * @return true if the signature is valid, false otherwise
     * @throws Exception if an error occurs during the verification process
     */
    public static boolean verifySignature(byte[] message, byte[] signature, PublicKey publicKey) throws Exception {
        // Create a Signature object for verification with the DSA algorithm and SHA-256
        Signature sig = Signature.getInstance("SHA256withDSA");

        // Initialize the Signature object with the public key
        sig.initVerify(publicKey);

        // Add the data to be verified
        sig.update(message);

        // Verify the signature and return the result
        return sig.verify(signature);
    }
}