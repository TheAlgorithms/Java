package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class to convert Roman numerals into integers.
 *
 * <p>Roman numerals are based on seven symbols given below:
 * <ul>
 *   <li>I = 1</li>
 *   <li>V = 5</li>
 *   <li>X = 10</li>
 *   <li>L = 50</li>
 *   <li>C = 100</li>
 *   <li>D = 500</li>
 *   <li>M = 1000</li>
 * </ul>
 *
 * <p>If a smaller numeral appears before a larger numeral, it is subtracted.
 * Otherwise, it is added. For example:
 * <pre>
 *   MCMXCIV = 1000 + (1000 - 100) + (100 - 10) + (5 - 1) = 1994
 * </pre>
 */
public final class RomanToInteger {

    private static final Map<Character, Integer> ROMAN_TO_INT = new HashMap<>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    private RomanToInteger() {
    }

    /**
     * Converts a single Roman numeral character to its integer value.
     *
     * @param symbol the Roman numeral character
     * @return the corresponding integer value
     * @throws IllegalArgumentException if the symbol is not a valid Roman numeral
     */
    private static int romanSymbolToInt(final char symbol) {
        return ROMAN_TO_INT.computeIfAbsent(symbol, c -> { throw new IllegalArgumentException("Unknown Roman symbol: " + c); });
    }

    /**
     * Converts a Roman numeral string to its integer equivalent.
     * Steps:
     * <ol>
     *     <li>Iterate over the string from right to left.</li>
     *     <li>For each character, convert it to an integer value.</li>
     *     <li>If the current value is greater than or equal to the max previous value, add it.</li>
     *     <li>Otherwise, subtract it from the sum.</li>
     *     <li>Update the max previous value.</li>
     *     <li>Return the sum.</li>
     * </ol>
     *
     * @param roman the Roman numeral string
     * @return the integer value of the Roman numeral
     * @throws IllegalArgumentException if the input contains invalid Roman characters
     * @throws NullPointerException if the input is {@code null}
     */
    public static int romanToInt(String roman) {
        if (roman == null) {
            throw new NullPointerException("Input cannot be null");
        }

        roman = roman.toUpperCase();
        int sum = 0;
        int maxPrevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanSymbolToInt(roman.charAt(i));
            if (currentValue >= maxPrevValue) {
                sum += currentValue;
                maxPrevValue = currentValue;
            } else {
                sum -= currentValue;
            }
        }

        return sum;
    }
}
