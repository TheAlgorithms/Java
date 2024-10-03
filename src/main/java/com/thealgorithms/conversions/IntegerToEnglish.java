package com.thealgorithms.conversions;

import java.util.HashMap;

public final class IntegerToEnglish {
    private static final HashMap<Integer, String> BASE_NUMBERS_MAP = new HashMap<>() {
        {
            put(0, "");
            put(1, "One");
            put(2, "Two");
            put(3, "Three");
            put(4, "Four");
            put(5, "Five");
            put(6, "Six");
            put(7, "Seven");
            put(8, "Eight");
            put(9, "Nine");
            put(10, "Ten");
            put(11, "Eleven");
            put(12, "Twelve");
            put(13, "Thirteen");
            put(14, "Fourteen");
            put(15, "Fifteen");
            put(16, "Sixteen");
            put(17, "Seventeen");
            put(18, "Eighteen");
            put(19, "Nineteen");
            put(20, "Twenty");
            put(30, "Thirty");
            put(40, "Forty");
            put(50, "Fifty");
            put(60, "Sixty");
            put(70, "Seventy");
            put(80, "Eighty");
            put(90, "Ninety");
            put(100, "Hundred");
        }
    };

    private static final HashMap<Integer, String> THOUSAND_POWER_MAP = new HashMap<>() {
        {
            put(1, "Thousand");
            put(2, "Million");
            put(3, "Billion");
        }
    };

    private IntegerToEnglish() {
    }

    /**
        converts numbers < 1000 to english words
     */
    private static String convertToWords(int number) {
        int remainder = number % 100;

        String result;

        if (remainder <= 20) {
            result = BASE_NUMBERS_MAP.get(remainder);
        } else if (BASE_NUMBERS_MAP.containsKey(remainder)) {
            result = BASE_NUMBERS_MAP.get(remainder);
        } else {
            int tensDigit = remainder / 10;
            int onesDigit = remainder % 10;

            result = String.format("%s %s", BASE_NUMBERS_MAP.get(tensDigit * 10), BASE_NUMBERS_MAP.get(onesDigit));
        }

        int hundredsDigit = number / 100;

        if (hundredsDigit > 0) {
            result = String.format("%s %s%s", BASE_NUMBERS_MAP.get(hundredsDigit), BASE_NUMBERS_MAP.get(100), (result.isEmpty() ? "" : " " + result));
        }

        return result;
    }

    /**
      Only convert groups of three digit if they are non-zero
     */
    public static String integerToEnglishWords(int number) {
        if (number == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();

        int index = 0;

        while (number > 0) {
            int remainder = number % 1000;
            number /= 1000;

            if (remainder > 0) {
                String subResult = convertToWords(remainder);

                if (!subResult.isEmpty()) {
                    if (!result.isEmpty()) {
                        result.insert(0, subResult + " " + THOUSAND_POWER_MAP.get(index) + " ");
                    } else {
                        if (index > 0) {
                            result = new StringBuilder(subResult + " " + THOUSAND_POWER_MAP.get(index));
                        } else {
                            result = new StringBuilder(subResult);
                        }
                    }
                }
            }

            index++;
        }

        return result.toString();
    }
}
