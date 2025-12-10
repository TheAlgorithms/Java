package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts any Binary Number to a Hexadecimal Number
 *
 * @author Nishita Aggarwal
 */
public final class BinaryToHexadecimal {
    private static final int BITS_IN_HEX_DIGIT = 4;
    private static final int BASE_BINARY = 2;
    private static final int BASE_DECIMAL = 10;
    private static final int HEX_START_DECIMAL = 10;
    private static final int HEX_END_DECIMAL = 15;

    private BinaryToHexadecimal() {
    }

    /**
     * Converts a binary number to a hexadecimal number.
     *
     * @param binary The binary number to convert.
     * @return The hexadecimal representation of the binary number.
     * @throws IllegalArgumentException If the binary number contains digits other than 0 and 1.
     */
    public static String binToHex(int binary) {
        Map<Integer, String> hexMap = initializeHexMap();
        StringBuilder hex = new StringBuilder();

        while (binary != 0) {
            int decimalValue = 0;
            for (int i = 0; i < BITS_IN_HEX_DIGIT; i++) {
                int currentBit = binary % BASE_DECIMAL;
                if (currentBit > 1) {
                    throw new IllegalArgumentException("Incorrect binary digit: " + currentBit);
                }
                binary /= BASE_DECIMAL;
                decimalValue += (int) (currentBit * Math.pow(BASE_BINARY, i));
            }
            hex.insert(0, hexMap.get(decimalValue));
        }

        return !hex.isEmpty() ? hex.toString() : "0";
    }

    /**
     * Initializes the hexadecimal map with decimal to hexadecimal mappings.
     *
     * @return The initialized map containing mappings from decimal numbers to hexadecimal digits.
     */
    private static Map<Integer, String> initializeHexMap() {
        Map<Integer, String> hexMap = new HashMap<>();
        for (int i = 0; i < BASE_DECIMAL; i++) {
            hexMap.put(i, String.valueOf(i));
        }
        for (int i = HEX_START_DECIMAL; i <= HEX_END_DECIMAL; i++) {
            hexMap.put(i, String.valueOf((char) ('A' + i - HEX_START_DECIMAL)));
        }
        return hexMap;
    }
}
