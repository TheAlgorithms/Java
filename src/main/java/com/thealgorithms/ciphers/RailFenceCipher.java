package com.thealgorithms.ciphers;

import java.util.Arrays;

/**
 * The rail fence cipher (also called a zigzag cipher) is a classical type of transposition cipher.
 * It derives its name from the manner in which encryption is performed, in analogy to a fence built with horizontal rails.
 * https://en.wikipedia.org/wiki/Rail_fence_cipher
 * @author https://github.com/Krounosity
 */

public class RailFenceCipher {

    // Encrypts the input string using the rail fence cipher method with the given number of rails.
    public String encrypt(String str, int rails) {

        // Base case of single rail or rails are more than the number of characters in the string
        if (rails == 1 || rails >= str.length()) {
            return str;
        }

        // Boolean flag to determine if the movement is downward or upward in the rail matrix.
        boolean down = true;
        // Create a 2D array to represent the rails (rows) and the length of the string (columns).
        char[][] strRail = new char[rails][str.length()];

        // Initialize all positions in the rail matrix with a placeholder character ('\n').
        for (int i = 0; i < rails; i++) {
            Arrays.fill(strRail[i], '\n');
        }

        int row = 0; // Start at the first row
        int col = 0; // Start at the first column

        int i = 0;

        // Fill the rail matrix with characters from the string based on the rail pattern.
        while (col < str.length()) {
            // Change direction to down when at the first row.
            if (row == 0) {
                down = true;
            }
            // Change direction to up when at the last row.
            else if (row == rails - 1) {
                down = false;
            }

            // Place the character in the current position of the rail matrix.
            strRail[row][col] = str.charAt(i);
            col++; // Move to the next column.
            // Move to the next row based on the direction.
            if (down) {
                row++;
            } else {
                row--;
            }

            i++;
        }

        // Construct the encrypted string by reading characters row by row.
        StringBuilder encryptedString = new StringBuilder();
        for (char[] chRow : strRail) {
            for (char ch : chRow) {
                if (ch != '\n') {
                    encryptedString.append(ch);
                }
            }
        }
        return encryptedString.toString();
    }
    // Decrypts the input string using the rail fence cipher method with the given number of rails.
    public String decrypt(String str, int rails) {

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
}
