package com.thealgorithms.ciphers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Columnar Transposition Cipher Encryption and Decryption.
 */
public final class ColumnarTranspositionCipher {

    private static final char PADDING_CHAR = '≈';

    private ColumnarTranspositionCipher() {
    }

    /**
     * Encrypts a message using the Columnar Transposition Cipher with the given key.
     *
     * @param message The message to encrypt.
     * @param key The key to use for encryption.
     * @return The encrypted message.
     */
    public static String encrypt(String message, String key) {
        int numColumns = key.length();
        int numRows = (int) Math.ceil((double) message.length() / numColumns);

        char[][] grid = new char[numRows][numColumns];

        // Fill the grid with the message and padding characters
        fillGrid(message, grid, numRows, numColumns);

        // Get the sorted column order based on the key
        Integer[] order = getColumnOrder(key);

        // Read columns in sorted order to build the encrypted message
        StringBuilder encryptedMessage = new StringBuilder();
        for (int col : order) {
            for (int row = 0; row < numRows; row++) {
                encryptedMessage.append(grid[row][col]);
            }
        }

        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message that was encrypted using the Columnar Transposition Cipher with the given key.
     *
     * @param message The encrypted message.
     * @param key The key used for encryption.
     * @return The decrypted message.
     */
    public static String decrypt(String message, String key) {
        int numColumns = key.length();
        int numRows = (int) Math.ceil((double) message.length() / numColumns);

        char[][] grid = new char[numRows][numColumns];

        // Get the sorted column order based on the key
        Integer[] order = getColumnOrder(key);

        // Fill the grid by columns based on the sorted order
        int index = 0;
        for (int col : order) {
            for (int row = 0; row < numRows; row++) {
                grid[row][col] = message.charAt(index++);
            }
        }

        // Read the grid row by row to get the decrypted message, removing padding
        StringBuilder decryptedMessage = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                char c = grid[row][col];
                if (c != PADDING_CHAR) {
                    decryptedMessage.append(c);
                }
            }
        }

        return decryptedMessage.toString();
    }

    /**
     * Fills the grid with the message and padding characters.
     *
     * @param message The message to encrypt.
     * @param grid The grid to fill.
     * @param numRows The number of rows in the grid.
     * @param numColumns The number of columns in the grid.
     */
    private static void fillGrid(String message, char[][] grid, int numRows, int numColumns) {
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (index < message.length()) {
                    grid[row][col] = message.charAt(index++);
                } else {
                    grid[row][col] = PADDING_CHAR;
                }
            }
        }
    }

    /**
     * Gets the order of the columns based on the sorted key characters.
     *
     * @param key The key to use for sorting.
     * @return An array containing the order of the columns.
     */
    private static Integer[] getColumnOrder(String key) {
        Integer[] order = new Integer[key.length()];
        Character[] keyChars = new Character[key.length()];

        for (int i = 0; i < key.length(); i++) {
            order[i] = i;
            keyChars[i] = key.charAt(i);
        }

        Arrays.sort(order, Comparator.comparingInt(o -> keyChars[o]));
        return order;
    }
}
