package com.thealgorithms.ciphers;

/**
 * The AffineCipher class implements the Affine cipher, a type of monoalphabetic substitution cipher.
 * It encrypts and decrypts messages using a linear transformation defined by the formula:
 *
 *     E(x) = (a * x + b) mod m
 *     D(y) = a^-1 * (y - b) mod m
 *
 * where:
 * - E(x) is the encrypted character,
 * - D(y) is the decrypted character,
 * - a is the multiplicative key (must be coprime to m),
 * - b is the additive key,
 * - x is the index of the plaintext character,
 * - y is the index of the ciphertext character,
 * - m is the size of the alphabet (26 for the English alphabet).
 *
 * The class provides methods for encrypting and decrypting messages, as well as a main method to demonstrate its usage.
 */
final class AffineCipher {
    private AffineCipher() {
    }

    // Key values of a and b
    static int a = 17;
    static int b = 20;

    /**
     * Encrypts a message using the Affine cipher.
     *
     * @param msg the plaintext message as a character array
     * @return the encrypted ciphertext
     */
    static String encryptMessage(char[] msg) {
        // Cipher Text initially empty
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < msg.length; i++) {
            // Avoid space to be encrypted
            /* applying encryption formula ( a * x + b ) mod m
            {here x is msg[i] and m is 26} and added 'A' to
            bring it in the range of ASCII alphabet [65-90 | A-Z] */
            if (msg[i] != ' ') {
                cipher.append((char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A'));
            } else { // else simply append space character
                cipher.append(msg[i]);
            }
        }
        return cipher.toString();
    }

    /**
     * Decrypts a ciphertext using the Affine cipher.
     *
     * @param cipher the ciphertext to decrypt
     * @return the decrypted plaintext message
     */
    static String decryptCipher(String cipher) {
        StringBuilder msg = new StringBuilder();
        int aInv = 0;
        int flag;

        // Find a^-1 (the multiplicative inverse of a in the group of integers modulo m.)
        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;

            // Check if (a * i) % 26 == 1,
            // then i will be the multiplicative inverse of a
            if (flag == 1) {
                aInv = i;
                break;
            }
        }
        for (int i = 0; i < cipher.length(); i++) {
            /* Applying decryption formula a^-1 * (x - b) mod m
            {here x is cipher[i] and m is 26} and added 'A'
            to bring it in the range of ASCII alphabet [65-90 | A-Z] */
            if (cipher.charAt(i) != ' ') {
                msg.append((char) (((aInv * ((cipher.charAt(i) - 'A') - b + 26)) % 26) + 'A'));
            } else { // else simply append space character
                msg.append(cipher.charAt(i));
            }
        }

        return msg.toString();
    }
}
