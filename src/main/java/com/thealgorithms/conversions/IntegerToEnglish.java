package com.thealgorithms.conversions;

import java.util.Map;

public final class IntegerToEnglish {
    private static final Map<Integer, String> BASE_NUMBERS_MAP = Map.ofEntries(Map.entry(0, ""), Map.entry(1, "One"), Map.entry(2, "Two"), Map.entry(3, "Three"), Map.entry(4, "Four"), Map.entry(5, "Five"), Map.entry(6, "Six"), Map.entry(7, "Seven"), Map.entry(8, "Eight"), Map.entry(9, "Nine"),
        Map.entry(10, "Ten"), Map.entry(11, "Eleven"), Map.entry(12, "Twelve"), Map.entry(13, "Thirteen"), Map.entry(14, "Fourteen"), Map.entry(15, "Fifteen"), Map.entry(16, "Sixteen"), Map.entry(17, "Seventeen"), Map.entry(18, "Eighteen"), Map.entry(19, "Nineteen"), Map.entry(20, "Twenty"),
        Map.entry(30, "Thirty"), Map.entry(40, "Forty"), Map.entry(50, "Fifty"), Map.entry(60, "Sixty"), Map.entry(70, "Seventy"), Map.entry(80, "Eighty"), Map.entry(90, "Ninety"), Map.entry(100, "Hundred"));

    private static final Map<Integer, String> THOUSAND_POWER_MAP = Map.ofEntries(Map.entry(1, "Thousand"), Map.entry(2, "Million"), Map.entry(3, "Billion"));

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
            result = String.format("%s %s%s", BASE_NUMBERS_MAP.get(hundredsDigit), BASE_NUMBERS_MAP.get(100), result.isEmpty() ? "" : " " + result);
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
