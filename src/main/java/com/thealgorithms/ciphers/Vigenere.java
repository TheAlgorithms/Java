package com.thealgorithms.ciphers;

/**
 * A Java implementation of the Vigenère Cipher.
 *
 * The Vigenère Cipher is a polyalphabetic substitution cipher that uses a
 * keyword to shift letters in the plaintext by different amounts, depending
 * on the corresponding character in the keyword. It wraps around the alphabet,
 * ensuring the shifts are within 'A'-'Z' or 'a'-'z'.
 *
 * Non-alphabetic characters (like spaces, punctuation) are kept unchanged.
 *
 * Encryption Example:
 * - Plaintext: "Hello World!"
 * - Key: "suchsecret"
 * - Encrypted Text: "Zynsg Yfvev!"
 *
 * Decryption Example:
 * - Ciphertext: "Zynsg Yfvev!"
 * - Key: "suchsecret"
 * - Decrypted Text: "Hello World!"
 *
 * Wikipedia Reference:
 * <a href="https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher">Vigenère Cipher - Wikipedia</a>
 *
 * @author straiffix
 * @author beingmartinbmc
 */
public class Vigenere {

    /**
     * Encrypts a given message using the Vigenère Cipher with the specified key.
     * Steps:
     * 1. Iterate over each character in the message.
     * 2. If the character is a letter, shift it by the corresponding character in the key.
     * 3. Preserve the case of the letter.
     * 4. Preserve non-alphabetic characters.
     * 5. Move to the next character in the key (cyclic).
     * 6. Return the encrypted message.
     *
     * @param message The plaintext message to encrypt.
     * @param key The keyword used for encryption.
     * @throws IllegalArgumentException if the key is empty.
     * @return The encrypted message.
     */
    public String encrypt(final String message, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ((c + key.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A'));
                } else {
                    result.append((char) ((c + key.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a'));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Decrypts a given message encrypted with the Vigenère Cipher using the specified key.
     * Steps:
     * 1. Iterate over each character in the message.
     * 2. If the character is a letter, shift it back by the corresponding character in the key.
     * 3. Preserve the case of the letter.
     * 4. Preserve non-alphabetic characters.
     * 5. Move to the next character in the key (cyclic).
     * 6. Return the decrypted message.
     *
     * @param message The encrypted message to decrypt.
     * @param key The keyword used for decryption.
     * @throws IllegalArgumentException if the key is empty.
     * @return The decrypted plaintext message.
     */
    public String decrypt(final String message, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ('Z' - (25 - (c - key.toUpperCase().charAt(j))) % 26));
                } else {
                    result.append((char) ('z' - (25 - (c - key.toLowerCase().charAt(j))) % 26));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
