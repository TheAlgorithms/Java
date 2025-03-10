package com.thealgorithms.conversions;

/**
 * @author Varun Upadhyay (<a href="https://github.com/varunu28">...</a>)
 */
public final class AnyBaseToDecimal {
    private static final int CHAR_OFFSET_FOR_DIGIT = '0';
    private static final int CHAR_OFFSET_FOR_UPPERCASE = 'A' - 10;

    private AnyBaseToDecimal() {
    }

    /**
     * Convert any radix to a decimal number.
     *
     * @param input the string to be converted
     * @param radix the radix (base) of the input string
     * @return the decimal equivalent of the input string
     * @throws NumberFormatException if the input string or radix is invalid
     */
    public static int convertToDecimal(String input, int radix) {
        int result = 0;
        int power = 1;

        for (int i = input.length() - 1; i >= 0; i--) {
            int digit = valOfChar(input.charAt(i));
            if (digit >= radix) {
                throw new NumberFormatException("For input string: " + input);
            }
            result += digit * power;
            power *= radix;
        }
        return result;
    }

    /**
     * Convert a character to its integer value.
     *
     * @param character the character to be converted
     * @return the integer value represented by the character
     * @throws NumberFormatException if the character is not an uppercase letter or a digit
     */
    private static int valOfChar(char character) {
        if (Character.isDigit(character)) {
            return character - CHAR_OFFSET_FOR_DIGIT;
        } else if (Character.isUpperCase(character)) {
            return character - CHAR_OFFSET_FOR_UPPERCASE;
        } else {
            throw new NumberFormatException("invalid character:" + character);
        }
    }
}
