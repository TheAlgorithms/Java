package com.thealgorithms.ciphers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The ADFGVX cipher is a fractionating transposition cipher that was used by
 * the German Army during World War I. It combines a **Polybius square substitution**
 * with a **columnar transposition** to enhance encryption strength.
 * <p>
 * The name "ADFGVX" refers to the six letters (A, D, F, G, V, X) used as row and
 * column labels in the Polybius square. This cipher was designed to secure
 * communication and create complex, hard-to-break ciphertexts.
 * <p>
 * Learn more: <a href="https://en.wikipedia.org/wiki/ADFGVX_cipher">ADFGVX Cipher - Wikipedia</a>.
 * <p>
 * Example usage:
 * <pre>
 * ADFGVXCipher cipher = new ADFGVXCipher();
 * String encrypted = cipher.encrypt("attack at 1200am", "PRIVACY");
 * String decrypted = cipher.decrypt(encrypted, "PRIVACY");
 * </pre>
 *
 * @author bennybebo
 */
public class ADFGVXCipher {

    // Constants used in the Polybius square
    private static final char[] POLYBIUS_LETTERS = {'A', 'D', 'F', 'G', 'V', 'X'};
    private static final char[][] POLYBIUS_SQUARE = {{'N', 'A', '1', 'C', '3', 'H'}, {'8', 'T', 'B', '2', 'O', 'M'}, {'E', '5', 'W', 'R', 'P', 'D'}, {'4', 'F', '6', 'G', '7', 'I'}, {'9', 'J', '0', 'K', 'L', 'Q'}, {'S', 'U', 'V', 'X', 'Y', 'Z'}};

    // Maps for fast substitution lookups
    private static final Map<String, Character> POLYBIUS_MAP = new HashMap<>();
    private static final Map<Character, String> REVERSE_POLYBIUS_MAP = new HashMap<>();

    // Static block to initialize the lookup tables from the Polybius square
    static {
        for (int i = 0; i < POLYBIUS_SQUARE.length; i++) {
            for (int j = 0; j < POLYBIUS_SQUARE[i].length; j++) {
                String key = "" + POLYBIUS_LETTERS[i] + POLYBIUS_LETTERS[j];
                POLYBIUS_MAP.put(key, POLYBIUS_SQUARE[i][j]);
                REVERSE_POLYBIUS_MAP.put(POLYBIUS_SQUARE[i][j], key);
            }
        }
    }

    /**
     * Encrypts a given plaintext using the ADFGVX cipher with the provided keyword.
     * Steps:
     * 1. Substitute each letter in the plaintext with a pair of ADFGVX letters.
     * 2. Perform a columnar transposition on the fractionated text using the keyword.
     *
     * @param plaintext The message to be encrypted (can contain letters and digits).
     * @param key       The keyword for columnar transposition.
     * @return The encrypted message as ciphertext.
     */
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z0-9]", ""); // Sanitize input
        StringBuilder fractionatedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            fractionatedText.append(REVERSE_POLYBIUS_MAP.get(c));
        }

        return columnarTransposition(fractionatedText.toString(), key);
    }

    /**
     * Decrypts a given ciphertext using the ADFGVX cipher with the provided keyword.
     * Steps:
     * 1. Reverse the columnar transposition performed during encryption.
     * 2. Substitute each pair of ADFGVX letters with the corresponding plaintext letter.
     * The resulting text is the decrypted message.
     *
     * @param ciphertext The encrypted message.
     * @param key        The keyword used during encryption.
     * @return The decrypted plaintext message.
     */
    public String decrypt(String ciphertext, String key) {
        String fractionatedText = reverseColumnarTransposition(ciphertext, key);

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < fractionatedText.length(); i += 2) {
            String pair = fractionatedText.substring(i, i + 2);
            plaintext.append(POLYBIUS_MAP.get(pair));
        }

        return plaintext.toString();
    }

    /**
     * Helper method: Performs columnar transposition during encryption
     *
     * @param text The fractionated text to be transposed
     * @param key  The keyword for columnar transposition
     * @return The transposed text
     */
    private String columnarTransposition(String text, String key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length());
        char[][] table = new char[numRows][key.length()];
        for (char[] row : table) { // Fill empty cells with underscores
            Arrays.fill(row, '_');
        }

        // Populate the table row by row
        for (int i = 0; i < text.length(); i++) {
            table[i / key.length()][i % key.length()] = text.charAt(i);
        }

        // Read columns based on the alphabetical order of the key
        StringBuilder ciphertext = new StringBuilder();
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        for (char keyChar : sortedKey) {
            int column = key.indexOf(keyChar);
            for (char[] row : table) {
                if (row[column] != '_') {
                    ciphertext.append(row[column]);
                }
            }
        }

        return ciphertext.toString();
    }

    /**
     * Helper method: Reverses the columnar transposition during decryption
     *
     * @param ciphertext The transposed text to be reversed
     * @param key        The keyword used during encryption
     * @return The reversed text
     */
    private String reverseColumnarTransposition(String ciphertext, String key) {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length());
        char[][] table = new char[numRows][key.length()];

        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        int index = 0;
        // Populate the table column by column according to the sorted key
        for (char keyChar : sortedKey) {
            int column = key.indexOf(keyChar);
            for (int row = 0; row < numRows; row++) {
                if (index < ciphertext.length()) {
                    table[row][column] = ciphertext.charAt(index++);
                } else {
                    table[row][column] = '_';
                }
            }
        }

        // Read the table row by row to reconstruct the fractionated text
        StringBuilder fractionatedText = new StringBuilder();
        for (char[] row : table) {
            for (char cell : row) {
                if (cell != '_') {
                    fractionatedText.append(cell);
                }
            }
        }

        return fractionatedText.toString();
    }
}
