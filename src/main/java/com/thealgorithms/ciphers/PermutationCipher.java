package com.thealgorithms.ciphers;

import java.util.HashSet;
import java.util.Set;

/**
 * A Java implementation of Permutation Cipher.
 * It is a type of transposition cipher in which the plaintext is divided into blocks
 * and the characters within each block are rearranged according to a fixed permutation key.
 *
 * For example, with key {3, 1, 2} and plaintext "HELLO", the text is divided into blocks
 * of 3 characters: "HEL" and "LO" (with padding). The characters are then rearranged
 * according to the key positions.
 *
 * @author GitHub Copilot
 */
public class PermutationCipher {

    private static final char PADDING_CHAR = 'X';

    /**
     * Encrypts the given plaintext using the permutation cipher with the specified key.
     *
     * @param plaintext the text to encrypt
     * @param key the permutation key (array of integers representing positions)
     * @return the encrypted text
     * @throws IllegalArgumentException if the key is invalid
     */
    public String encrypt(String plaintext, int[] key) {
        validateKey(key);

        if (plaintext == null || plaintext.isEmpty()) {
            return plaintext;
        }

        // Remove spaces and convert to uppercase for consistent processing
        String cleanText = plaintext.replaceAll("\\s+", "").toUpperCase();

        // Pad the text to make it divisible by key length
        String paddedText = padText(cleanText, key.length);

        StringBuilder encrypted = new StringBuilder();

        // Process text in blocks of key length
        for (int i = 0; i < paddedText.length(); i += key.length) {
            String block = paddedText.substring(i, Math.min(i + key.length, paddedText.length()));
            encrypted.append(permuteBlock(block, key));
        }

        return encrypted.toString();
    }

    /**
     * Decrypts the given ciphertext using the permutation cipher with the specified key.
     *
     * @param ciphertext the text to decrypt
     * @param key the permutation key (array of integers representing positions)
     * @return the decrypted text
     * @throws IllegalArgumentException if the key is invalid
     */
    public String decrypt(String ciphertext, int[] key) {
        validateKey(key);

        if (ciphertext == null || ciphertext.isEmpty()) {
            return ciphertext;
        }

        // Create the inverse permutation
        int[] inverseKey = createInverseKey(key);

        StringBuilder decrypted = new StringBuilder();

        // Process text in blocks of key length
        for (int i = 0; i < ciphertext.length(); i += key.length) {
            String block = ciphertext.substring(i, Math.min(i + key.length, ciphertext.length()));
            decrypted.append(permuteBlock(block, inverseKey));
        }

        // Remove padding characters from the end
        return removePadding(decrypted.toString());
    }
    /**
     * Validates that the permutation key is valid.
     * A valid key must contain all integers from 1 to n exactly once, where n is the key length.
     *
     * @param key the permutation key to validate
     * @throws IllegalArgumentException if the key is invalid
     */
    private void validateKey(int[] key) {
        if (key == null || key.length == 0) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        Set<Integer> keySet = new HashSet<>();
        for (int position : key) {
            if (position < 1 || position > key.length) {
                throw new IllegalArgumentException("Key must contain integers from 1 to " + key.length);
            }
            if (!keySet.add(position)) {
                throw new IllegalArgumentException("Key must contain each position exactly once");
            }
        }
    }

    /**
     * Pads the text with padding characters to make its length divisible by the block size.
     *
     * @param text the text to pad
     * @param blockSize the size of each block
     * @return the padded text
     */
    private String padText(String text, int blockSize) {
        int remainder = text.length() % blockSize;
        if (remainder == 0) {
            return text;
        }

        int paddingNeeded = blockSize - remainder;
        StringBuilder padded = new StringBuilder(text);
        for (int i = 0; i < paddingNeeded; i++) {
            padded.append(PADDING_CHAR);
        }

        return padded.toString();
    }
    /**
     * Applies the permutation to a single block of text.
     *
     * @param block the block to permute
     * @param key the permutation key
     * @return the permuted block
     */
    private String permuteBlock(String block, int[] key) {
        if (block.length() != key.length) {
            // Handle case where block is shorter than key (shouldn't happen with proper padding)
            block = padText(block, key.length);
        }

        char[] result = new char[key.length];
        char[] blockChars = block.toCharArray();

        for (int i = 0; i < key.length; i++) {
            // Key positions are 1-based, so subtract 1 for 0-based array indexing
            result[i] = blockChars[key[i] - 1];
        }

        return new String(result);
    }

    /**
     * Creates the inverse permutation key for decryption.
     *
     * @param key the original permutation key
     * @return the inverse key
     */
    private int[] createInverseKey(int[] key) {
        int[] inverse = new int[key.length];

        for (int i = 0; i < key.length; i++) {
            // The inverse key maps each position to where it should go
            inverse[key[i] - 1] = i + 1;
        }

        return inverse;
    }

    /**
     * Removes padding characters from the end of the decrypted text.
     *
     * @param text the text to remove padding from
     * @return the text without padding
     */
    private String removePadding(String text) {
        if (text.isEmpty()) {
            return text;
        }

        int i = text.length() - 1;
        while (i >= 0 && text.charAt(i) == PADDING_CHAR) {
            i--;
        }

        return text.substring(0, i + 1);
    }

    /**
     * Gets the padding character used by this cipher.
     *
     * @return the padding character
     */
    public char getPaddingChar() {
        return PADDING_CHAR;
    }
}
