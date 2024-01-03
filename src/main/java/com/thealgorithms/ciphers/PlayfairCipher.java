package com.thealgorithms.ciphers;

public class PlayfairCipher {

    private char[][] matrix;
    private String key;

    public PlayfairCipher(String key) {
        this.key = key;
        generateMatrix();
    }

    public String encrypt(String plaintext) {
        plaintext = prepareText(plaintext.replace("J", "I"));
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char char1 = plaintext.charAt(i);
            char char2 = plaintext.charAt(i + 1);
            int[] pos1 = findPosition(char1);
            int[] pos2 = findPosition(char2);
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            if (row1 == row2) {
                ciphertext.append(matrix[row1][(col1 + 1) % 5]);
                ciphertext.append(matrix[row2][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                ciphertext.append(matrix[(row1 + 1) % 5][col1]);
                ciphertext.append(matrix[(row2 + 1) % 5][col2]);
            } else {
                ciphertext.append(matrix[row1][col2]);
                ciphertext.append(matrix[row2][col1]);
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char char1 = ciphertext.charAt(i);
            char char2 = ciphertext.charAt(i + 1);
            int[] pos1 = findPosition(char1);
            int[] pos2 = findPosition(char2);
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            if (row1 == row2) {
                plaintext.append(matrix[row1][(col1 + 4) % 5]);
                plaintext.append(matrix[row2][(col2 + 4) % 5]);
            } else if (col1 == col2) {
                plaintext.append(matrix[(row1 + 4) % 5][col1]);
                plaintext.append(matrix[(row2 + 4) % 5][col2]);
            } else {
                plaintext.append(matrix[row1][col2]);
                plaintext.append(matrix[row2][col1]);
            }
        }
        return plaintext.toString();
    }

    private void generateMatrix() {
        String keyWithoutDuplicates = removeDuplicateChars(key + "ABCDEFGHIKLMNOPQRSTUVWXYZ");
        matrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyWithoutDuplicates.charAt(index);
                index++;
            }
        }
    }

    private String removeDuplicateChars(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (result.indexOf(String.valueOf(str.charAt(i))) == -1) {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    private String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder preparedText = new StringBuilder();
        char prevChar = '\0';
        for (char c : text.toCharArray()) {
            if (c != prevChar) {
                preparedText.append(c);
                prevChar = c;
            } else {
                preparedText.append('X').append(c);
                prevChar = '\0';
            }
        }
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    private int[] findPosition(char c) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    public void printMatrix() {
        System.out.println("\nPlayfair Cipher Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
