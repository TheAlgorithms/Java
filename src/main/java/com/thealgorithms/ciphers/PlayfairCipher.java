package com.thealgorithms.ciphers;

import java.util.HashMap;

/**
 * A Java implementation of Playfair Cipher.
 * It is a classical encryption technique that encrypts digraphs (pairs of letters) instead of individual letters.
 * Its primary mechanism revolves around a 5x5 matrix, which serves as the key-table for both encryption and decryption.
 */
public class PlayfairCipher {
    public Character[][] Matrix;
    /**
     * Map for storing index of character
     */
    HashMap<Character, Pair> IndexMap;

    PlayfairCipher(String keyword) {
        Matrix = Create5x5MatrixFrom(keyword);
    }

    class Pair {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    /**
     * Remove duplicates from Keyword and replace 'J' with 'I'
     *         Fill matrix with Keyword followed by remaining letters (skip 'J')

     */
    public Character[][] Create5x5MatrixFrom(String keyword) {
        Character[][] Matrix = new Character[5][5];
        IndexMap = new HashMap<>();
        keyword = keyword.replaceAll("\\s", "").toUpperCase().replaceAll("J", "I");
        int x = 0;
        for (int i = 0; i < keyword.length(); i++) {
            if (!IndexMap.containsKey(keyword.charAt(i))) {
                IndexMap.put(keyword.charAt(i), new Pair(x / 5, x % 5));
                Matrix[x / 5][x % 5] = keyword.charAt(i);
                x++;
            }
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            if (!IndexMap.containsKey(ch)) {
                IndexMap.put(ch, new Pair(x / 5, x % 5));
                Matrix[x / 5][x % 5] = ch;
                x++;
            }
        }
        return Matrix;
    }

    public String encrypt(String PlainText) {
        int i = 0;
        String encrypted = "";
        while (i < PlainText.length()) {
            Pair a = new Pair(0, 0);
            Pair b = new Pair(0, 0);
            /***
             * encryption takes place in pair of two characters
             * if characters are same or one character is left we add X
             * I and J are at same cell

             */
            if (i + 1 == PlainText.length() || PlainText.charAt(i) == PlainText.charAt(i + 1)) {
                i++;
                a = IndexMap.get(PlainText.charAt(i));
                b = IndexMap.get('X');
            } else {
                i += 2;
                a = IndexMap.get(PlainText.charAt(i));
                b = IndexMap.get(PlainText.charAt(i + 1));
            }
            /**
             * Same Row: If both letters of a digraph are in the same row, each letter is replaced by the one immediately to its right, wrapping around if necessary.
             * Same Column: If both letters are in the same column, each letter is replaced by the one immediately below it, again wrapping around if needed.
             * Rectangle Formation: If the letters are on different rows and columns, a rectangle is formed using the two letters. Each letter of the digraph is then replaced by the letter in its same row but on the other horizontal side of the rectangle.
             */
            if (a.row == b.row) {
                encrypted += Matrix[a.row][(a.col + 1) % 5] + Matrix[b.row][(b.col + 1) % 5];

            } else if (a.col == b.col) {
                encrypted += Matrix[(a.row + 1) % 5][a.col] + Matrix[(b.row + 1) % 5][b.col];
            } else {
                encrypted += Matrix[a.row][b.col] + Matrix[b.row][a.col];
            }

        }
        return encrypted;

    }

    /**
     * The same rules apply for decryption, but in reverse.
     * For instance, for letters in the same row, each letter is replaced by the one immediately to its left during decryption.
     * @param Ciphertext
     * @return
     */
    public String decrypt(String Ciphertext) {
        int i = 0;
        String decrypted = "";
        while (i < Ciphertext.length()) {
            Pair a = new Pair(0, 0);
            Pair b = new Pair(0, 0);


            i += 2;
            a = IndexMap.get(Ciphertext.charAt(i));
            b = IndexMap.get(Ciphertext.charAt(i + 1));

            if (a.row == b.row) {
                decrypted += Matrix[a.row][(a.col - 1) % 5] + Matrix[b.row][(b.col - 1) % 5];

            } else if (a.col == b.col) {
                decrypted += Matrix[(a.row - 1) % 5][a.col] + Matrix[(b.row - 1) % 5][b.col];
            } else {
                decrypted += Matrix[a.row][b.col] + Matrix[b.row][a.col];
            }

        }
        return decrypted;
    }


}
