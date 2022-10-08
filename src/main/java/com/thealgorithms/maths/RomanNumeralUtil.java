package com.thealgorithms.maths;

/**
 * Translates numbers into the Roman Numeral System.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Roman_numerals">Roman
 * numerals</a>
 * @author Sokratis Fotkatzikis
 * @version 1.0
 */
public class RomanNumeralUtil {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 5999;
    //1000-5999
    private static final String[] RN_M = {
        "",
        "M",
        "MM",
        "MMM",
        "MMMM",
        "MMMMM",
    };
    //100-900
    private static final String[] RN_C = {
        "",
        "C",
        "CC",
        "CCC",
        "CD",
        "D",
        "DC",
        "DCC",
        "DCCC",
        "CM",
    };
    //10-90
    private static final String[] RN_X = {
        "",
        "X",
        "XX",
        "XXX",
        "XL",
        "L",
        "LX",
        "LXX",
        "LXXX",
        "XC",
    };
    //1-9
    private static final String[] RN_I = {
        "",
        "I",
        "II",
        "III",
        "IV",
        "V",
        "VI",
        "VII",
        "VIII",
        "IX",
    };

    public static String generate(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(
                String.format(
                    "The number must be in the range [%d, %d]",
                    MIN_VALUE,
                    MAX_VALUE
                )
            );
        }

        return (
            RN_M[number / 1000] +
            RN_C[number % 1000 / 100] +
            RN_X[number % 100 / 10] +
            RN_I[number % 10]
        );
    }
}
