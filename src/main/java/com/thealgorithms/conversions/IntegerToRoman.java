package com.thealgorithms.conversions;

/**
 * A utility class to convert integers into Roman numerals.
 *
 * <p>Roman numerals follow these rules:
 * <ul>
 *   <li>I = 1</li>
 *   <li>IV = 4</li>
 *   <li>V = 5</li>
 *   <li>IX = 9</li>
 *   <li>X = 10</li>
 *   <li>XL = 40</li>
 *   <li>L = 50</li>
 *   <li>XC = 90</li>
 *   <li>C = 100</li>
 *   <li>D = 500</li>
 *   <li>M = 1000</li>
 * </ul>
 *
 * <p>Conversion is based on repeatedly subtracting the largest possible Roman numeral value
 * from the input number until it reaches zero. For example, 1994 is converted as:
 * <pre>
 *   1994 -> MCMXCIV (1000 + 900 + 90 + 4)
 * </pre>
 */
public final class IntegerToRoman {

    // Array of Roman numeral values in descending order
    private static final int[] ALL_ROMAN_NUMBERS_IN_ARABIC = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    // Corresponding Roman numeral symbols
    private static final String[] ALL_ROMAN_NUMBERS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private IntegerToRoman() {
    }

    /**
     * Converts an integer to its Roman numeral representation.
     * Steps:
     * <ol>
     *     <li>Iterate over the Roman numeral values in descending order</li>
     *     <li>Calculate how many times a numeral fits</li>
     *     <li>Append the corresponding symbol</li>
     *     <li>Subtract the value from the number</li>
     *     <li>Repeat until the number is zero</li>
     *     <li>Return the Roman numeral representation</li>
     * </ol>
     *
     * @param num the integer value to convert (must be greater than 0)
     * @return the Roman numeral representation of the input integer
     *         or an empty string if the input is non-positive
     */
    public static String integerToRoman(int num) {
        if (num <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ALL_ROMAN_NUMBERS_IN_ARABIC.length; i++) {
            int times = num / ALL_ROMAN_NUMBERS_IN_ARABIC[i];
            builder.append(ALL_ROMAN_NUMBERS[i].repeat(Math.max(0, times)));
            num -= times * ALL_ROMAN_NUMBERS_IN_ARABIC[i];
        }

        return builder.toString();
    }
}
