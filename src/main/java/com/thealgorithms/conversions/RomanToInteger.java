package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.Map;

public final class RomanToInteger {
    private RomanToInteger() {
    }

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

    private static int romanSymbolToInt(final char symbol) {
        return ROMAN_TO_INT.computeIfAbsent(symbol, c -> { throw new IllegalArgumentException("Unknown Roman symbol: " + c); });
    }

    // Roman Number = Roman Numerals

    /**
     * This function convert Roman number into Integer
     *
     * @param a Roman number string
     * @return integer
     */
    public static int romanToInt(String a) {
        a = a.toUpperCase();
        char prev = ' ';

        int sum = 0;

        int newPrev = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            char c = a.charAt(i);

            if (prev != ' ') {
                // checking current Number greater than previous or not
                newPrev = romanSymbolToInt(prev) > newPrev ? romanSymbolToInt(prev) : newPrev;
            }

            int currentNum = romanSymbolToInt(c);

            // if current number greater than prev max previous then add
            if (currentNum >= newPrev) {
                sum += currentNum;
            } else {
                // subtract upcoming number until upcoming number not greater than prev max
                sum -= currentNum;
            }

            prev = c;
        }

        return sum;
    }
}
