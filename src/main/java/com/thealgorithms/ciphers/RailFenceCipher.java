package com.thealgorithms.ciphers;

/**
 * The rail fence cipher (also called a zigzag cipher) is a classical type of transposition cipher.
 * It derives its name from the manner in which encryption is performed, in analogy to a fence built with horizontal rails.
 * https://en.wikipedia.org/wiki/Rail_fence_cipher
 * @author https://github.com/Krounosity
 */

public class RailFenceCipher {

    // Encrypts the input string using the rail fence cipher method with the given number of rails.
    public String encrypt(String str, int rails) {

        checkInput(str, rails);

        // Base case of single rail or rails are more than the number of characters in the string
        if (rails == 1 || rails >= str.length()) {
            return str;
        }

        // Boolean flag to determine if the movement is downward or upward in the rail pattern.
        boolean down = true;
        // Collect the characters of every rail separately. Using one buffer per rail (instead of a
        // rails x length matrix with a placeholder character) keeps every character of the input,
        // including characters that would otherwise be indistinguishable from the placeholder.
        StringBuilder[] railBuffers = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            railBuffers[i] = new StringBuilder();
        }

        int row = 0; // Start at the first rail

        // Distribute the characters of the string over the rails following the zigzag pattern.
        for (int i = 0; i < str.length(); i++) {
            // Change direction to down when at the first row.
            if (row == 0) {
                down = true;
            }
            // Change direction to up when at the last row.
            else if (row == rails - 1) {
                down = false;
            }

            // Append the character to the rail it belongs to.
            railBuffers[row].append(str.charAt(i));
            // Move to the next row based on the direction.
            if (down) {
                row++;
            } else {
                row--;
            }
        }

        // Construct the encrypted string by reading the rails top to bottom.
        StringBuilder encryptedString = new StringBuilder(str.length());
        for (StringBuilder railBuffer : railBuffers) {
            encryptedString.append(railBuffer);
        }
        return encryptedString.toString();
    }
    // Decrypts the input string using the rail fence cipher method with the given number of rails.
    public String decrypt(String str, int rails) {

        checkInput(str, rails);

        // Base case of single rail or rails are more than the number of characters in the string
        if (rails == 1 || rails >= str.length()) {
            return str;
        }
        // Boolean flag to determine if the movement is downward or upward in the rail matrix.
        boolean down = true;

        // Create a 2D array to represent the rails (rows) and the length of the string (columns).
        char[][] strRail = new char[rails][str.length()];

        int row = 0; // Start at the first row
        int col = 0; // Start at the first column

        // Mark the pattern on the rail matrix using '*'.
        while (col < str.length()) {
            // Change direction to down when at the first row.
            if (row == 0) {
                down = true;
            }
            // Change direction to up when at the last row.
            else if (row == rails - 1) {
                down = false;
            }

            // Mark the current position in the rail matrix.
            strRail[row][col] = '*';
            col++; // Move to the next column.
            // Move to the next row based on the direction.
            if (down) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0; // Index to track characters from the input string.
        // Fill the rail matrix with characters from the input string based on the marked pattern.
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < str.length(); j++) {
                if (strRail[i][j] == '*') {
                    strRail[i][j] = str.charAt(index++);
                }
            }
        }

        // Construct the decrypted string by following the zigzag pattern.
        StringBuilder decryptedString = new StringBuilder();
        row = 0; // Reset to the first row
        col = 0; // Reset to the first column

        while (col < str.length()) {
            // Change direction to down when at the first row.
            if (row == 0) {
                down = true;
            }
            // Change direction to up when at the last row.
            else if (row == rails - 1) {
                down = false;
            }
            // Append the character from the rail matrix to the decrypted string.
            decryptedString.append(strRail[row][col]);
            col++; // Move to the next column.
            // Move to the next row based on the direction.
            if (down) {
                row++;
            } else {
                row--;
            }
        }

        return decryptedString.toString();
    }

    // Rejects inputs the zigzag pattern is not defined for.
    private static void checkInput(String str, int rails) {
        if (str == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }
        if (rails <= 0) {
            throw new IllegalArgumentException("Number of rails must be positive, but was " + rails);
        }
    }
}
