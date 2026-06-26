package com.thealgorithms.ciphers;

/**
 * The Atbash cipher is a classic substitution cipher that substitutes each letter
 * with its opposite letter in the alphabet.
 *
 * For example:
 * - 'A' becomes 'Z', 'B' becomes 'Y', 'C' becomes 'X', and so on.
 * - Similarly, 'a' becomes 'z', 'b' becomes 'y', and so on.
 *
 * The cipher works identically for both uppercase and lowercase letters.
 * Non-alphabetical characters remain unchanged in the output.
 *
 * This cipher is symmetric, meaning that applying the cipher twice will return
 * the original text. Therefore, the same function is used for both encryption and decryption.
 *
 * <p>Usage Example:</p>
 * <pre>
 * AtbashCipher cipher = new AtbashCipher("Hello World!");
 * String encrypted = cipher.convert(); // Output: "Svool Dliow!"
 * </pre>
 *
 * @author <a href="https://github.com/Krounosity">Krounosity</a>
 * @see <a href="https://en.wikipedia.org/wiki/Atbash">Atbash Cipher (Wikipedia)</a>
 */
public class AtbashCipher {

    private String toConvert;

    public AtbashCipher() {
    }

    /**
     * Constructor with a string parameter.
     *
     * @param str The string to be converted using the Atbash cipher
     */
    public AtbashCipher(String str) {
        this.toConvert = str;
    }

    /**
     * Returns the current string set for conversion.
     *
     * @return The string to be converted
     */
    public String getString() {
        return toConvert;
    }

    /**
     * Sets the string to be converted using the Atbash cipher.
     *
     * @param str The new string to convert
     */
    public void setString(String str) {
        this.toConvert = str;
    }

    /**
     * Checks if a character is uppercase.
     *
     * @param ch The character to check
     * @return {@code true} if the character is uppercase, {@code false} otherwise
     */
    private boolean isCapital(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * Checks if a character is lowercase.
     *
     * @param ch The character to check
     * @return {@code true} if the character is lowercase, {@code false} otherwise
     */
    private boolean isSmall(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * Converts the input string using the Atbash cipher.
     * Alphabetic characters are substituted with their opposite in the alphabet,
     * while non-alphabetic characters remain unchanged.
     *
     * @return The converted string after applying the Atbash cipher
     */
    public String convert() {
        StringBuilder convertedString = new StringBuilder();

        for (char ch : toConvert.toCharArray()) {
            if (isSmall(ch)) {
                convertedString.append((char) ('z' - (ch - 'a')));
            } else if (isCapital(ch)) {
                convertedString.append((char) ('Z' - (ch - 'A')));
            } else {
                convertedString.append(ch);
            }
        }
        return convertedString.toString();
    }
}
