package com.thealgorithms.ciphers;

import java.util.Scanner;

/**
 * ProductCipher combines substitution and transposition ciphers.
 * Refactoring notes:
 *  - SRP: encryption, decryption, padding, and I/O are each in their own method.
 *  - OCP/DIP: SubstitutionCipher and TranspositionCipher implement the Cipher
 *    interface, so new cipher types can be added without touching existing code.
 *  - StringBuffer replaced with StringBuilder (thread-safety not needed here).
 *  - Magic number 5 (Caesar shift) extracted to a named constant.
 *  - The `n` variable is no longer reused for two different purposes.
 *  - God-method main() now delegates to focused, testable helpers.
 */
final class ProductCipher {

    private ProductCipher() {
    }

    // ------------------------------------------------------------------ //
    //  Cipher abstraction (Open/Closed + Dependency-Inversion principles) //
    // ------------------------------------------------------------------ //

    interface Cipher {
        String encrypt(String input, int key);
        String decrypt(String input, int key);
    }

    // ------------------------------------------------------------------ //
    //  Substitution cipher                                                 //
    // ------------------------------------------------------------------ //

    static final class SubstitutionCipher implements Cipher {

        private static final int SHIFT = 5;

        @Override
        public String encrypt(String input, int key) {
            // `key` is unused for a fixed-shift substitution cipher,
            // but kept in the signature to satisfy the Cipher contract.
            StringBuilder result = new StringBuilder(input.length());
            for (char c : input.toCharArray()) {
                result.append((char) (c + SHIFT));
            }
            return result.toString();
        }

        @Override
        public String decrypt(String input, int key) {
            StringBuilder result = new StringBuilder(input.length());
            for (char c : input.toCharArray()) {
                result.append((char) (c - SHIFT));
            }
            return result.toString();
        }
    }

    // ------------------------------------------------------------------ //
    //  Transposition cipher                                                //
    // ------------------------------------------------------------------ //

    static final class TranspositionCipher implements Cipher {

        private static final char PAD_CHAR = '/';

        /** Pad the text so its length is a multiple of {@code numRows}. */
        String pad(String text, int numRows) {
            int remainder = text.length() % numRows;
            if (remainder == 0) {
                return text;
            }
            int paddingNeeded = numRows - remainder;
            StringBuilder padded = new StringBuilder(text);
            padded.repeat(String.valueOf(PAD_CHAR), Math.max(0, paddingNeeded));
            return padded.toString();
        }

        /**
         * Reads the padded text column-by-column (row-major write, column-major read).
         * Prints the transposition matrix as a `side effect for traceability.
         */
        @Override
        public String encrypt(String input, int numRows) {
            String padded = pad(input, numRows);
            int numCols = padded.length() / numRows;

            System.out.println("Transposition Matrix: ");
            StringBuilder result = new StringBuilder(padded.length());

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    char c = padded.charAt(row + col * numRows);
                    System.out.print(c);
                    result.append(c);
                }
                System.out.println();
            }

            return result.toString();
        }

        /** Reverses the column-major read by treating the cipher as row-major. */
        @Override
        public String decrypt(String input, int numRows) {
            int numCols = input.length() / numRows;
            StringBuilder result = new StringBuilder(input.length());

            for (int row = 0; row < numCols; row++) {
                for (int col = 0; col < numRows; col++) {
                    result.append(input.charAt(row + col * numCols));
                }
            }
            return result.toString();
        }
    }

    // ------------------------------------------------------------------ //
    //  I/O helper (Single-Responsibility: only reads user input)          //
    // ------------------------------------------------------------------ //

    private static String readPlaintext(Scanner sc) {
        System.out.println("Enter the input to be encrypted: ");
        return sc.nextLine();
    }

    private static int readKey(Scanner sc) {
        System.out.println("Enter a number: ");
        return sc.nextInt();
    }

    // ------------------------------------------------------------------ //
    //  Entry point – thin orchestration only                               //
    // ------------------------------------------------------------------ //

    public static void main(String[] args) {
        Cipher substitution  = new SubstitutionCipher();
        Cipher transposition = new TranspositionCipher();

        try (Scanner sc = new Scanner(System.in)) {
            String plaintext = readPlaintext(sc);
            int key          = readKey(sc);

            // Encrypt
            String afterSubstitution = substitution.encrypt(plaintext, key);
            System.out.println("\nSubstituted text: ");
            System.out.println(afterSubstitution);

            System.out.println();
            String ciphertext = transposition.encrypt(afterSubstitution, key);
            System.out.println("\nFinal encrypted text: ");
            System.out.println(ciphertext);

            // Decrypt (use a separate variable; never reuse `key` for a different meaning)
            int decryptionKey = ciphertext.length() / key;
            String afterTranspositionDecrypt = transposition.decrypt(ciphertext, decryptionKey);
            String recovered = substitution.decrypt(afterTranspositionDecrypt, key);

            System.out.println("Plaintext: ");
            System.out.println(recovered);
        }
    }
}