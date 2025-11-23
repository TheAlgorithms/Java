package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * ElGamal Encryption Algorithm Implementation
 * 
 * ElGamal is an asymmetric key encryption algorithm for public-key cryptography
 * based on the Discrete Logarithm Problem (DLP). It provides semantic security
 * through randomization in the encryption process.
 * 
 * Key Components:
 * - p: Large prime number (modulus)
 * - g: Generator of the multiplicative group modulo p
 * - x: Private key (random integer)
 * - y: Public key where y = g^x mod p
 * 
 * Encryption: For message m, choose random k and compute:
 *   c1 = g^k mod p
 *   c2 = m * y^k mod p
 *   Ciphertext = (c1, c2)
 * 
 * Decryption: Recover m using:
 *   m = c2 * (c1^x)^-1 mod p
 *   where (c1^x)^-1 is the modular multiplicative inverse
 * 
 * @author TheAlgorithms
 */
public final class ElGamalCipher {

    private ElGamalCipher() {
        // Utility class - prevent instantiation
    }

    /**
     * Represents an ElGamal key pair containing public and private keys
     */
    public static class KeyPair {
        private final BigInteger p; // Prime modulus
        private final BigInteger g; // Generator
        private final BigInteger x; // Private key
        private final BigInteger y; // Public key (y = g^x mod p)

        public KeyPair(BigInteger p, BigInteger g, BigInteger x, BigInteger y) {
            this.p = p;
            this.g = g;
            this.x = x;
            this.y = y;
        }

        public BigInteger getP() {
            return p;
        }

        public BigInteger getG() {
            return g;
        }

        public BigInteger getPrivateKey() {
            return x;
        }

        public BigInteger getPublicKey() {
            return y;
        }
    }

    /**
     * Represents an ElGamal ciphertext as a pair (c1, c2)
     */
    public static class Ciphertext {
        private final BigInteger c1;
        private final BigInteger c2;

        public Ciphertext(BigInteger c1, BigInteger c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public BigInteger getC1() {
            return c1;
        }

        public BigInteger getC2() {
            return c2;
        }

        @Override
        public String toString() {
            return "Ciphertext{c1=" + c1 + ", c2=" + c2 + "}";
        }
    }

    /**
     * Generates ElGamal key pair with specified bit length
     * 
     * Steps:
     * 1. Generate a large prime p
     * 2. Find a generator g of the multiplicative group mod p
     * 3. Choose random private key x in range [2, p-2]
     * 4. Compute public key y = g^x mod p
     * 
     * @param bitLength The bit length for the prime (e.g., 512, 1024, 2048)
     * @return KeyPair containing (p, g, x, y)
     */
    public static KeyPair generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();
        
        // Generate a large prime p
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        
        // Find a generator g (simplified: use a small generator that works for most primes)
        // In practice, we often use g = 2 or find a primitive root
        BigInteger g = findGenerator(p, random);
        
        // Generate private key x: random number in range [2, p-2]
        BigInteger x;
        do {
            x = new BigInteger(bitLength - 1, random);
        } while (x.compareTo(BigInteger.TWO) < 0 || x.compareTo(p.subtract(BigInteger.TWO)) > 0);
        
        // Calculate public key y = g^x mod p
        BigInteger y = g.modPow(x, p);
        
        return new KeyPair(p, g, x, y);
    }

    /**
     * Finds a generator for the multiplicative group modulo p
     * Simplified approach: tries small values until finding a suitable generator
     * 
     * @param p The prime modulus
     * @param random Random number generator
     * @return A generator g
     */
    private static BigInteger findGenerator(BigInteger p, Random random) {
        // Simplified: use 2 as generator (works for most safe primes)
        // For production, should verify g is a primitive root
        BigInteger g = BigInteger.valueOf(2);
        
        // If 2 doesn't work, try other small values
        while (g.compareTo(p) < 0) {
            // Check if g is a valid generator (simplified check)
            if (g.modPow(p.subtract(BigInteger.ONE), p).equals(BigInteger.ONE)) {
                return g;
            }
            g = g.add(BigInteger.ONE);
        }
        
        return BigInteger.valueOf(2); // Fallback
    }

    /**
     * Encrypts a message using ElGamal encryption
     * 
     * Process:
     * 1. Choose random k in range [2, p-2]
     * 2. Compute c1 = g^k mod p
     * 3. Compute c2 = m * y^k mod p
     * 4. Return ciphertext (c1, c2)
     * 
     * The random k ensures semantic security - same message encrypted
     * multiple times produces different ciphertexts
     * 
     * @param message The plaintext message as BigInteger (must be < p)
     * @param keyPair The key pair containing public parameters
     * @return Ciphertext (c1, c2)
     */
    public static Ciphertext encrypt(BigInteger message, KeyPair keyPair) {
        if (message.compareTo(keyPair.getP()) >= 0) {
            throw new IllegalArgumentException("Message must be less than modulus p");
        }
        
        SecureRandom random = new SecureRandom();
        BigInteger p = keyPair.getP();
        BigInteger g = keyPair.getG();
        BigInteger y = keyPair.getPublicKey();
        
        // Choose random k in range [2, p-2]
        BigInteger k;
        do {
            k = new BigInteger(p.bitLength() - 1, random);
        } while (k.compareTo(BigInteger.TWO) < 0 || k.compareTo(p.subtract(BigInteger.TWO)) > 0);
        
        // Compute c1 = g^k mod p
        BigInteger c1 = g.modPow(k, p);
        
        // Compute c2 = m * y^k mod p
        BigInteger c2 = message.multiply(y.modPow(k, p)).mod(p);
        
        return new Ciphertext(c1, c2);
    }

    /**
     * Decrypts a ciphertext using ElGamal decryption
     * 
     * Process:
     * 1. Compute s = c1^x mod p (shared secret)
     * 2. Compute s^-1 (modular multiplicative inverse of s)
     * 3. Recover m = c2 * s^-1 mod p
     * 
     * Mathematical proof:
     * c2 * (c1^x)^-1 = (m * y^k) * (g^(k*x))^-1
     *                = (m * g^(k*x)) * (g^(k*x))^-1
     *                = m
     * 
     * @param ciphertext The ciphertext (c1, c2)
     * @param keyPair The key pair containing private key
     * @return Decrypted plaintext message
     */
    public static BigInteger decrypt(Ciphertext ciphertext, KeyPair keyPair) {
        BigInteger c1 = ciphertext.getC1();
        BigInteger c2 = ciphertext.getC2();
        BigInteger x = keyPair.getPrivateKey();
        BigInteger p = keyPair.getP();
        
        // Compute s = c1^x mod p
        BigInteger s = c1.modPow(x, p);
        
        // Compute s^-1 mod p (modular multiplicative inverse)
        BigInteger sInverse = s.modInverse(p);
        
        // Recover message: m = c2 * s^-1 mod p
        BigInteger message = c2.multiply(sInverse).mod(p);
        
        return message;
    }

    /**
     * Converts a string to BigInteger for encryption
     * 
     * @param text The input string
     * @return BigInteger representation
     */
    public static BigInteger stringToBigInteger(String text) {
        byte[] bytes = text.getBytes();
        return new BigInteger(1, bytes);
    }

    /**
     * Converts BigInteger back to string after decryption
     * 
     * @param number The BigInteger to convert
     * @return Original string
     */
    public static String bigIntegerToString(BigInteger number) {
        byte[] bytes = number.toByteArray();
        // Handle sign byte if present
        if (bytes[0] == 0 && bytes.length > 1) {
            byte[] tmp = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, tmp, 0, tmp.length);
            bytes = tmp;
        }
        return new String(bytes);
    }

    /**
     * Main method demonstrating ElGamal encryption and decryption
     */
    public static void main(String[] args) {
        System.out.println("=== ElGamal Encryption Algorithm Demo ===\n");
        
        // Example 1: Encrypting a small integer
        System.out.println("Example 1: Encrypting a small integer");
        System.out.println("--------------------------------------");
        
        // Generate keys with 512-bit prime (use 1024 or 2048 for production)
        System.out.println("Generating keys (512-bit)...");
        KeyPair keyPair = generateKeys(512);
        
        System.out.println("Prime (p): " + keyPair.getP());
        System.out.println("Generator (g): " + keyPair.getG());
        System.out.println("Private key (x): " + keyPair.getPrivateKey());
        System.out.println("Public key (y): " + keyPair.getPublicKey());
        
        // Message to encrypt
        BigInteger message = BigInteger.valueOf(12345);
        System.out.println("\nOriginal message: " + message);
        
        // Encrypt
        Ciphertext ciphertext = encrypt(message, keyPair);
        System.out.println("Encrypted: " + ciphertext);
        
        // Decrypt
        BigInteger decrypted = decrypt(ciphertext, keyPair);
        System.out.println("Decrypted message: " + decrypted);
        
        // Verify
        System.out.println("Decryption successful: " + message.equals(decrypted));
        
        // Example 2: Demonstrating semantic security
        System.out.println("\n\nExample 2: Demonstrating Semantic Security");
        System.out.println("------------------------------------------");
        System.out.println("Same message encrypted twice produces different ciphertexts:");
        
        Ciphertext ct1 = encrypt(message, keyPair);
        Ciphertext ct2 = encrypt(message, keyPair);
        
        System.out.println("Encryption 1: " + ct1);
        System.out.println("Encryption 2: " + ct2);
        System.out.println("Are ciphertexts different? " + !ct1.getC1().equals(ct2.getC1()));
        
        // Both decrypt to same message
        System.out.println("Both decrypt to: " + decrypt(ct1, keyPair) + " and " + decrypt(ct2, keyPair));
        
        // Example 3: String encryption
        System.out.println("\n\nExample 3: Encrypting a String");
        System.out.println("-------------------------------");
        
        String text = "Hello";
        System.out.println("Original text: " + text);
        
        BigInteger textAsNumber = stringToBigInteger(text);
        System.out.println("Text as BigInteger: " + textAsNumber);
        
        // Check if message is small enough
        if (textAsNumber.compareTo(keyPair.getP()) < 0) {
            Ciphertext textCiphertext = encrypt(textAsNumber, keyPair);
            System.out.println("Encrypted: " + textCiphertext);
            
            BigInteger decryptedNumber = decrypt(textCiphertext, keyPair);
            String decryptedText = bigIntegerToString(decryptedNumber);
            System.out.println("Decrypted text: " + decryptedText);
            System.out.println("Match: " + text.equals(decryptedText));
        } else {
            System.out.println("Text too large for current key size. Use larger keys or split text.");
        }
    }
}