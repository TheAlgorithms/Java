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

    /**
     * Encrypt text by shifting every Latin char by add number shift for ASCII
     * Example : A + 1 -> B
     *
     * @return Encrypted message
     */
    public String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();

        shift %= 26;

        final int length = message.length();
        for (int i = 0; i < length; i++) {
            //            int current = message.charAt(i); //using char to shift characters because ascii
            // is in-order latin alphabet
            char current = message.charAt(i); // Java law : char + int = char

            if (isCapitalLatinLetter(current)) {
                current += shift;
                encoded.append((char) (current > 'Z' ? current - 26 : current)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                current += shift;
                encoded.append((char) (current > 'z' ? current - 26 : current)); // 26 = number of latin letters
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

        shift %= 26;

        final int length = encryptedMessage.length();
        for (int i = 0; i < length; i++) {
            char current = encryptedMessage.charAt(i);
            if (isCapitalLatinLetter(current)) {
                current -= shift;
                decoded.append((char) (current < 'A' ? current + 26 : current)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                current -= shift;
                decoded.append((char) (current < 'a' ? current + 26 : current)); // 26 = number of latin letters
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
