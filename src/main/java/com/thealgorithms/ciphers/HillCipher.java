package com.thealgorithms.ciphers;

public class HillCipher {

    // Encrypts the message using the key matrix
    public String encrypt(String message, int[][] keyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        int matrixSize = keyMatrix.length;
        validateDeterminant(keyMatrix, matrixSize);

        StringBuilder cipherText = new StringBuilder();
        int[] messageVector = new int[matrixSize];
        int[] cipherVector = new int[matrixSize];
        int index = 0;

        while (index < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (index < message.length()) {
                    messageVector[i] = message.charAt(index++) - 'A';
                } else {
                    messageVector[i] = 'X' - 'A'; // Padding with 'X' if needed
                }
            }

            for (int i = 0; i < matrixSize; i++) {
                cipherVector[i] = 0;
                for (int j = 0; j < matrixSize; j++) {
                    cipherVector[i] += keyMatrix[i][j] * messageVector[j];
                }
                cipherVector[i] = cipherVector[i] % 26;
                cipherText.append((char) (cipherVector[i] + 'A'));
            }
        }

        return cipherText.toString();
    }

    // Decrypts the message using the inverse key matrix
    public String decrypt(String message, int[][] inverseKeyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        int matrixSize = inverseKeyMatrix.length;
        validateDeterminant(inverseKeyMatrix, matrixSize);

        StringBuilder plainText = new StringBuilder();
        int[] messageVector = new int[matrixSize];
        int[] plainVector = new int[matrixSize];
        int index = 0;

        while (index < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (index < message.length()) {
                    messageVector[i] = message.charAt(index++) - 'A';
                } else {
                    messageVector[i] = 'X' - 'A'; // Padding with 'X' if needed
                }
            }

            for (int i = 0; i < matrixSize; i++) {
                plainVector[i] = 0;
                for (int j = 0; j < matrixSize; j++) {
                    plainVector[i] += inverseKeyMatrix[i][j] * messageVector[j];
                }
                plainVector[i] = plainVector[i] % 26;
                plainText.append((char) (plainVector[i] + 'A'));
            }
        }

        return plainText.toString();
    }

    // Validates that the determinant of the key matrix is not zero modulo 26
    private void validateDeterminant(int[][] keyMatrix, int n) {
        int det = determinant(keyMatrix, n) % 26;
        if (det == 0) {
            throw new IllegalArgumentException("Invalid key matrix. Determinant is zero modulo 26.");
        }
    }

    // Computes the determinant of a matrix recursively
    private int determinant(int[][] matrix, int n) {
        int det = 0;
        if (n == 1) {
            return matrix[0][0];
        }
        int sign = 1;
        int[][] subMatrix = new int[n - 1][n - 1];
        for (int x = 0; x < n; x++) {
            int subI = 0;
            for (int i = 1; i < n; i++) {
                int subJ = 0;
                for (int j = 0; j < n; j++) {
                    if (j != x) {
                        subMatrix[subI][subJ++] = matrix[i][j];
                    }
                }
                subI++;
            }
            det += sign * matrix[0][x] * determinant(subMatrix, n - 1);
            sign = -sign;
        }
        return det;
    }
}
