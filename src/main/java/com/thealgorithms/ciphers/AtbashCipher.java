package com.thealgorithms.ciphers;

/**
 * The Atbash cipher is a simple substitution cipher that replaces each letter
 * in the alphabet with its reverse.
 * For example, 'A' becomes 'Z', 'B' becomes 'Y', and so on. It works
 * identically for both uppercase and lowercase letters.
 * It's a symmetric cipher, meaning applying it twice returns the original text.
 * Hence, the encrypting and the decrypting functions are identical
 * @author https://github.com/Krounosity
 * Learn more: https://en.wikipedia.org/wiki/Atbash
 */

public class AtbashCipher {

    private String toConvert;

    // Default constructor.
    AtbashCipher() {
    }

    // String setting constructor.
    AtbashCipher(String str) {
        toConvert = str;
    }

    // String getter method.
    public String getString() {
        return toConvert;
    }

    // String setter method.
    public void setString(String str) {
        toConvert = str;
    }

    // Checking whether the current character is capital.
    private boolean isCapital(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    // Checking whether the current character is smallcased.
    private boolean isSmall(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    // Converting text to atbash cipher code or vice versa.
    public String convert() {

        // Using StringBuilder to store new string.
        StringBuilder convertedString = new StringBuilder();

        // Iterating for each character.
        for (char ch : toConvert.toCharArray()) {

            // If the character is smallcased.
            if (isSmall(ch)) {
                convertedString.append((char) ('z' - (ch - 'a')));
            }
            // If the character is capital cased.
            else if (isCapital(ch)) {
                convertedString.append((char) ('Z' - (ch - 'A')));
            }
            // Non-alphabetical character.
            else {
                convertedString.append(ch);
            }
        }
        return convertedString.toString();
    }
}
