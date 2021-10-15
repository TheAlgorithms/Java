package Ciphers;

import java.util.HashMap;
import java.util.Map;

public class Affine {

    public static void main(String[] args) {
        test("javaalgorithms", 3, 7, 26);
        test("hacktoberfest", 11, 17, 26);
        test("opensourcecontribution", 23, 73, 26);
        test("abcdefghijklmnopqrstuvwxyz", 37, 73, 26);
    }

    /**
     * Test function which do encryption and decryption.
     * @param plainText: The text to encrypt
     * @param a: key for encryption
     * @param b: key for encryption; a & b should be coprime
     * @param m: size of alphabet set
     */
    private static void test(final String plainText,
                             final int a,
                             final int b,
                             final int m) {
        System.out.println("Input plain text: " + plainText);
        final String cipherText = encrypt(plainText, a, b, m);
        System.out.println("Encrypted plain text: " + cipherText);
        final String decryptedText = decrypt(cipherText, a, b, m);
        System.out.println("Decrypted cipher text: " + decryptedText);
        assert decryptedText.equals(plainText): "Encryption/Decryption didn't work correctly";
    }
    /**
     *
     * @param plainText: The text to encrypt
     * @param a: key for encryption
     * @param b: key for encryption; a & b should be coprime
     * @param m: size of alphabet set
     * @return cipherText
     *
     * Encryption Formula used: {E(x)=(ax+b) mod (m)}
     */
    private static String encrypt(final String plainText,
                                  final int a,
                                  final int b,
                                  final int m) {
        final char[] cipherText = new char[plainText.length()];
        final Map<Integer, Integer> lookup = new HashMap<>();
        for(int i = 0; i < m; i++) {
            final int encodedValue = (a * i + b) % m;
            lookup.put(i, encodedValue);
        }
        for(int i = 0; i < plainText.length(); i++) {
            cipherText[i] = (char) ('a' + lookup.get(plainText.charAt(i) - 'a'));
        }
        return new String(cipherText);
    }

    /**
     *
     * @param cipherText: text to decrypt
     * @param a: key for encryption
     * @param b: key for encryption; a & b should be coprime
     * @param m: size of alphabet set
     * @return plaintext
     *
     * Decryption Formula used: {D(x)=a^(-1)(x-b) mod (m)}
     * where a^(-1) is the modular multiplicative inverse of a modulo m. I.e., it satisfies the equation
     * { 1=aa^(-1) mod (m)}
     *
     * Since calculating inverse might be tricky a smart approach is to create a reverse lookup. Every
     * language set has a limited number of character. So, we can calculate encoded character for each character
     * and put in a map where encoded character is the key and original character is its value. Later use this
     * reverse lookup map to decrypt.
     */
    private static String decrypt(final String cipherText,
                                  final int a,
                                  final int b,
                                  final int m) {
        final char[] plainText = new char[cipherText.length()];
        final Map<Integer, Integer> reverseLookup = new HashMap<>();
        for(int i = 0; i < m; i++) {
            final int encodedValue = (a * i + b) % m;
            reverseLookup.put(encodedValue, i);
        }
        for(int i = 0; i < cipherText.length(); i++) {
            plainText[i] = (char) ('a' + reverseLookup.get(cipherText.charAt(i) - 'a'));
        }

        return new String(plainText);
    }
}
