package com.thealgorithms.conversions;

import java.math.BigDecimal;

/**
 A Java-based utility for converting numeric values into their English word
 representations. Whether you need to convert a small number, a large number
 with millions and billions, or even a number with decimal places, this utility
 has you covered.
 *
 */
public class NumberToWords {

    private NumberToWords() {
    }

    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static final String[] powers = {"", "Thousand", "Million", "Billion", "Trillion"};

    private static final String ZERO = "Zero";
    private static final String POINT = " Point";
    private static final String NEGATIVE = "Negative ";

    public static String convert(BigDecimal number) {
        if (number == null) {
            return "Invalid Input";
        }

        // Check for negative sign
        boolean isNegative = number.signum() < 0;

        // Split the number into whole and fractional parts
        BigDecimal[] parts = number.abs().divideAndRemainder(BigDecimal.ONE);
        BigDecimal wholePart = parts[0]; // Keep whole part as BigDecimal
        String fractionalPartStr = parts[1].compareTo(BigDecimal.ZERO) > 0 ? parts[1].toPlainString().substring(2) : ""; // Get fractional part only if it exists

        // Convert whole part to words
        StringBuilder result = new StringBuilder();
        if (isNegative) {
            result.append(NEGATIVE);
        }
        result.append(convertWholeNumberToWords(wholePart));

        // Convert fractional part to words
        if (!fractionalPartStr.isEmpty()) {
            result.append(POINT);
            for (char digit : fractionalPartStr.toCharArray()) {
                int digitValue = Character.getNumericValue(digit);
                result.append(" ").append(digitValue == 0 ? ZERO : units[digitValue]);
            }
        }

        return result.toString().trim();
    }

    private static String convertWholeNumberToWords(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return ZERO;
        }

        StringBuilder words = new StringBuilder();
        int power = 0;

        while (number.compareTo(BigDecimal.ZERO) > 0) {
            // Get the last three digits
            BigDecimal[] divisionResult = number.divideAndRemainder(BigDecimal.valueOf(1000));
            int chunk = divisionResult[1].intValue();

            if (chunk > 0) {
                String chunkWords = convertChunk(chunk);
                if (power > 0) {
                    words.insert(0, powers[power] + " ");
                }
                words.insert(0, chunkWords + " ");
            }

            number = divisionResult[0]; // Continue with the remaining part
            power++;
        }

        return words.toString().trim();
    }

    private static String convertChunk(int number) {
        String chunkWords;

        if (number < 20) {
            chunkWords = units[number];
        } else if (number < 100) {
            chunkWords = tens[number / 10] + (number % 10 > 0 ? " " + units[number % 10] : "");
        } else {
            chunkWords = units[number / 100] + " Hundred" + (number % 100 > 0 ? " " + convertChunk(number % 100) : "");
        }

        return chunkWords;
    }
}