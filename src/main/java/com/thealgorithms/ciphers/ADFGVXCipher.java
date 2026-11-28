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

        if (fractionatedText.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid ciphertext: odd number of ADFGVX letters.");
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < fractionatedText.length(); i += 2) {
            String pair = fractionatedText.substring(i, i + 2);
            Character letter = POLYBIUS_MAP.get(pair);
            if (letter == null) {
                throw new IllegalArgumentException("Invalid ciphertext: unknown ADFGVX pair \"" + pair + "\".");
            }
            plaintext.append(letter);
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
        for (int column : columnsInKeyOrder(key)) {
            for (char[] row : table) {
                if (row[column] != '_') {
                    ciphertext.append(row[column]);
                }
            }
        }

        return ciphertext.toString();
    }

    /**
     * Returns the column indices in the order the key imposes: alphabetical by
     * key letter, with repeated letters ordered by their first occurrence.
     * Using key.indexOf for repeated letters would map both occurrences to the
     * same column and make the transposition lossy.
     */
    private int[] columnsInKeyOrder(String key) {
        Integer[] order = new Integer[key.length()];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> {
            int byLetter = Character.compare(key.charAt(a), key.charAt(b));
            return byLetter != 0 ? byLetter : Integer.compare(a, b);
        });
        return Arrays.stream(order).mapToInt(Integer::intValue).toArray();
    }

    /**
     * Helper method: Reverses the columnar transposition during decryption
     *
     * @param ciphertext The transposed text to be reversed
     * @param key        The keyword used during encryption
     * @return The reversed text
     */
    private String reverseColumnarTransposition(String ciphertext, String key) {
        int numCols = key.length();
        int fullRows = ciphertext.length() / numCols;
        int remainder = ciphertext.length() % numCols;
        // The last row of the encryption table is only partially filled: the
        // first `remainder` columns hold one extra character. The previous
        // implementation assumed every column had the same length, which
        // misaligned the whole grid whenever the length was not a multiple of
        // the key length.
        int[] columnLength = new int[numCols];
        for (int column = 0; column < numCols; column++) {
            columnLength[column] = fullRows + (column < remainder ? 1 : 0);
        }

        int numRows = fullRows + (remainder > 0 ? 1 : 0);
        char[][] table = new char[numRows][numCols];

        int index = 0;
        // Populate the table column by column according to the sorted key
        for (int column : columnsInKeyOrder(key)) {
            for (int row = 0; row < columnLength[column]; row++) {
                table[row][column] = ciphertext.charAt(index++);
            }
        }

        // Read the table row by row to reconstruct the fractionated text
        StringBuilder fractionatedText = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numCols; column++) {
                if (row < columnLength[column]) {
                    fractionatedText.append(table[row][column]);
                }
            }
        }

        return fractionatedText.toString();
    }
}
