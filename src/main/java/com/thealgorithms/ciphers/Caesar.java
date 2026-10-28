package com.thealgorithms.ciphers;

/**
 * A Java implementation of Caesar Cipher. /It is a type of substitution cipher
 * in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions down the alphabet. /
 *
 * @author FAHRI YARDIMCI
 * @author khalil2535
 */
public class Caesar {
    private static int normalizeShift(final int shift) {
        return ((shift % 26) + 26) % 26;
    }

    /**
     * Encrypt text by shifting every Latin char by add number shift for ASCII
     * Example : A + 1 -> B
     *
     * @return Encrypted message
     */
    public String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();

        final int shiftChar = normalizeShift(shift);

        final int length = message.length();
        for (int i = 0; i < length; i++) {
            final char current = message.charAt(i);

            if (isCapitalLatinLetter(current)) {
                final int shifted = current + shiftChar;
                encoded.append((char) (shifted > 'Z' ? shifted - 26 : shifted)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                final int shifted = current + shiftChar;
                encoded.append((char) (shifted > 'z' ? shifted - 26 : shifted)); // 26 = number of latin letters
            } else {
                encoded.append(current);
            }
        }
        return encoded.toString();
    }

    /**
     * Decrypt message by shifting back every Latin char to previous the ASCII
     * Example : B - 1 -> A
     *
     * @return message
     */
    public String decode(String encryptedMessage, int shift) {
        StringBuilder decoded = new StringBuilder();

        final int shiftChar = normalizeShift(shift);

        final int length = encryptedMessage.length();
        for (int i = 0; i < length; i++) {
            final char current = encryptedMessage.charAt(i);
            if (isCapitalLatinLetter(current)) {
                final int shifted = current - shiftChar;
                decoded.append((char) (shifted < 'A' ? shifted + 26 : shifted)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                final int shifted = current - shiftChar;
                decoded.append((char) (shifted < 'a' ? shifted + 26 : shifted)); // 26 = number of latin letters
            } else {
                decoded.append(current);
            }
        }
        return decoded.toString();
    }

    /**
     * @return true if character is capital Latin letter or false for others
     */
    private static boolean isCapitalLatinLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * @return true if character is small Latin letter or false for others
     */
    private static boolean isSmallLatinLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     *  @return string array which contains all the possible decoded combination.
     */
    public String[] bruteforce(String encryptedMessage) {
        String[] listOfAllTheAnswers = new String[27];
        for (int i = 0; i <= 26; i++) {
            listOfAllTheAnswers[i] = decode(encryptedMessage, i);
        }

        return listOfAllTheAnswers;
    }
}
