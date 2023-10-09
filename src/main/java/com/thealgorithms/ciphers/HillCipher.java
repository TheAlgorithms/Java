package com.thealgorithms.ciphers;

import java.util.Scanner;

/*
 * Java Implementation of Hill Cipher
 * Hill cipher is a polyalphabetic substitution cipher. Each letter is represented by a number
 * belonging to the set Z26 where A=0 , B=1, ..... Z=25. To encrypt a message, each block of n
 * letters (since matrix size is n x n) is multiplied by an invertible n × n matrix, against
 * modulus 26. To decrypt the message, each block is multiplied by the inverse of the matrix used
 * for encryption. The cipher key and plaintext/ciphertext are user inputs.
 * @author Ojasva Jain
 */
public class HillCipher {

    static Scanner userInput = new Scanner(System.in);

    /* Following function encrypts the message
     */
    static void encrypt(String message) {
        message = message.toUpperCase();
        // Get key matrix
        System.out.println("Enter key matrix size");
        int matrixSize = userInput.nextInt();
        System.out.println("Enter Key/encryptionKey matrix ");
        int[][] keyMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
        // check if det = 0
        validateDeterminant(keyMatrix, matrixSize);

        int[][] messageVector = new int[matrixSize][1];
        String CipherText = "";
        int[][] cipherMatrix = new int[matrixSize][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            int x, i;
            for (i = 0; i < matrixSize; i++) {
                cipherMatrix[i][0] = 0;

                for (x = 0; x < matrixSize; x++) {
                    cipherMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }
                System.out.println(cipherMatrix[i][0]);
                cipherMatrix[i][0] = cipherMatrix[i][0] % 26;
            }
            for (i = 0; i < matrixSize; i++) {
                CipherText += (char) (cipherMatrix[i][0] + 65);
            }
        }
        System.out.println("Ciphertext: " + CipherText);
    }

    // Following function decrypts a message
    static void decrypt(String message) {
        message = message.toUpperCase();
        // Get key matrix
        System.out.println("Enter key matrix size");
        int n = userInput.nextInt();
        System.out.println("Enter inverseKey/decryptionKey matrix ");
        int[][] keyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
        // check if det = 0
        validateDeterminant(keyMatrix, n);

        // solving for the required plaintext message
        int[][] messageVector = new int[n][1];
        String PlainText = "";
        int[][] plainMatrix = new int[n][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < n; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            int x, i;
            for (i = 0; i < n; i++) {
                plainMatrix[i][0] = 0;

                for (x = 0; x < n; x++) {
                    plainMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }

                plainMatrix[i][0] = plainMatrix[i][0] % 26;
            }
            for (i = 0; i < n; i++) {
                PlainText += (char) (plainMatrix[i][0] + 65);
            }
        }
        System.out.println("Plaintext: " + PlainText);
    }

    // Determinant calculator
    public static int determinant(int[][] a, int n) {
        int det = 0, sign = 1, p = 0, q = 0;

        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    // Function to implement Hill Cipher
    static void hillCipher(String message) {
        message.toUpperCase();
        System.out.println("What do you want to process from the message?");
        System.out.println("Press 1: To Encrypt");
        System.out.println("Press 2: To Decrypt");
        short sc = userInput.nextShort();
        if (sc == 1) {
            encrypt(message);
        } else if (sc == 2) {
            decrypt(message);
        } else {
            System.out.println("Invalid input, program terminated.");
        }
    }

    static void validateDeterminant(int[][] keyMatrix, int n) {
        if (determinant(keyMatrix, n) % 26 == 0) {
            System.out.println("Invalid key, as determinant = 0. Program Terminated");
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Get the message to be encrypted
        System.out.println("Enter message");
        String message = userInput.nextLine();
        hillCipher(message);
    }
}
