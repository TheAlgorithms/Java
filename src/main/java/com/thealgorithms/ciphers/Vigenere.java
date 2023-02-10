package com.thealgorithms.ciphers;

/**
 * A Java implementation of Vigenere Cipher.
 *
 * @author straiffix
 * @author beingmartinbmc
 */
public class Vigenere {

    public String encrypt(final String message, final String key) {
        StringBuilder result = new StringBuilder();

        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append(
                        (char) (
                            (c + key.toUpperCase().charAt(j) - 2 * 'A') %
                            26 +
                            'A'
                        )
                    );
                } else {
                    result.append(
                        (char) (
                            (c + key.toLowerCase().charAt(j) - 2 * 'a') %
                            26 +
                            'a'
                        )
                    );
                }
            } else {
                result.append(c);
            }
            j = ++j % key.length();
        }
        return result.toString();
    }

    public String decrypt(final String message, final String key) {
        StringBuilder result = new StringBuilder();

        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append(
                        (char) (
                            'Z' - (25 - (c - key.toUpperCase().charAt(j))) % 26
                        )
                    );
                } else {
                    result.append(
                        (char) (
                            'z' - (25 - (c - key.toLowerCase().charAt(j))) % 26
                        )
                    );
                }
            } else {
                result.append(c);
            }

            j = ++j % key.length();
        }
        return result.toString();
    }
}
