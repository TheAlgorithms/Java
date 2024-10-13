package com.thealgorithms.ciphers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * The ADFGVX cipher is a historically significant cipher used by
 * the German Army during World War I. It is a fractionating transposition
 * cipher that combines a Polybius square substitution with a columnar
 * transposition. It's named after the six letters (A, D, F, G, V, X)
 * that it uses in its substitution process.
 * https://en.wikipedia.org/wiki/ADFGVX_cipher
 *
 * @author bennybebo
 */
public class ADFGVXCipher {

    private static final char[] POLYBIUS_LETTERS = {'A', 'D', 'F', 'G', 'V', 'X'};
    private static final char[][] POLYBIUS_SQUARE = {{'N', 'A', '1', 'C', '3', 'H'}, {'8', 'T', 'B', '2', 'O', 'M'}, {'E', '5', 'W', 'R', 'P', 'D'}, {'4', 'F', '6', 'G', '7', 'I'}, {'9', 'J', '0', 'K', 'L', 'Q'}, {'S', 'U', 'V', 'X', 'Y', 'Z'}};
    private static final Map<String, Character> POLYBIUS_MAP = new HashMap<>();
    private static final Map<Character, String> REVERSE_POLYBIUS_MAP = new HashMap<>();

    static {
        for (int i = 0; i < POLYBIUS_SQUARE.length; i++) {
            for (int j = 0; j < POLYBIUS_SQUARE[i].length; j++) {
                String key = "" + POLYBIUS_LETTERS[i] + POLYBIUS_LETTERS[j];
                POLYBIUS_MAP.put(key, POLYBIUS_SQUARE[i][j]);
                REVERSE_POLYBIUS_MAP.put(POLYBIUS_SQUARE[i][j], key);
            }
        }
    }

    // Encrypts the plaintext using the ADFGVX cipher
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z0-9]", "");
        StringBuilder fractionatedText = new StringBuilder();

        // Step 1: Polybius square substitution
        for (char c : plaintext.toCharArray()) {
            fractionatedText.append(REVERSE_POLYBIUS_MAP.get(c));
        }

        // Step 2: Columnar transposition
        return columnarTransposition(fractionatedText.toString(), key);
    }

    // Decrypts the ciphertext using the ADFGVX cipher
    public String decrypt(String ciphertext, String key) {
        // Step 1: Reverse the columnar transposition
        String fractionatedText = reverseColumnarTransposition(ciphertext, key);

        // Step 2: Polybius square substitution
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < fractionatedText.length(); i += 2) {
            String pair = fractionatedText.substring(i, i + 2);
            plaintext.append(POLYBIUS_MAP.get(pair));
        }

        return plaintext.toString();
    }

    private String columnarTransposition(String text, String key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length());
        char[][] table = new char[numRows][key.length()];
        for (char[] row : table) {
            Arrays.fill(row, '_'); // Fill with underscores to handle empty cells
        }

        // Fill the table row by row
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

    private String reverseColumnarTransposition(String ciphertext, String key) {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length());
        char[][] table = new char[numRows][key.length()];

        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        int index = 0;
        // Fill the table column by column according to the sorted key order
        for (char keyChar : sortedKey) {
            int column = key.indexOf(keyChar);
            for (int row = 0; row < numRows; row++) {
                if (index < ciphertext.length()) {
                    table[row][column] = ciphertext.charAt(index++);
                } else {
                    table[row][column] = '_'; // Fill empty cells with an underscore
                }
            }
        }

        // Read the table row by row to get the fractionated text
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
