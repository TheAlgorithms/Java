package com.thealgorithms.conversions;

/**
 * Converting Integers into Roman Numerals
 *
 * <p>
 * ('I', 1); ('IV',4); ('V', 5); ('IX',9); ('X', 10); ('XL',40); ('L', 50);
 * ('XC',90); ('C', 100); ('D', 500); ('M', 1000);
 */
public final class IntegerToRoman {
    private IntegerToRoman() {
    }

    private static final int[] ALL_ROMAN_NUMBERS_IN_ARABIC = new int[] {
        1000,
        900,
        500,
        400,
        100,
        90,
        50,
        40,
        10,
        9,
        5,
        4,
        1,
    };
    private static final String[] ALL_ROMAN_NUMBERS = new String[] {
        "M",
        "CM",
        "D",
        "CD",
        "C",
        "XC",
        "L",
        "XL",
        "X",
        "IX",
        "V",
        "IV",
        "I",
    };

    // Value must be > 0
    public static String integerToRoman(int num) {
        if (num <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int a = 0; a < ALL_ROMAN_NUMBERS_IN_ARABIC.length; a++) {
            int times = num / ALL_ROMAN_NUMBERS_IN_ARABIC[a];
            for (int b = 0; b < times; b++) {
                builder.append(ALL_ROMAN_NUMBERS[a]);
            }

            num -= times * ALL_ROMAN_NUMBERS_IN_ARABIC[a];
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(IntegerToRoman.integerToRoman(2131));
    }
}
