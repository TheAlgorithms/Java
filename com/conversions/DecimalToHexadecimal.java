package com.conversions;

import java.math.BigInteger;

public class DecimalToHexadecimal {
    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final BigInteger valueHex = BigInteger.valueOf(16);
    private static final int sizeOfIntInHalfBytes = 8;
	private static final int numberOfBitsInAHalfByte = 4;
	private static final int halfByte = 0x0F;
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

    /**
     * This method converts and decimal number to a Hexadecimal number
     *
     * @param decimalStr
     * @return hexadecimal number
     */
    public String decimalToHex(String decimalStr) {
        BigInteger decimal = new BigInteger(decimalStr);

        int rem;
        String hex = "";
        while (decimal.compareTo(BigInteger.ZERO) > 0) {
            rem = decimal.mod(valueHex).intValueExact();
            hex = hexChars[rem] + hex;
            decimal = decimal.divide(valueHex);
        }
        return hex;
    }
    public static String decToHex(int dec) {
		StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
		hexBuilder.setLength(sizeOfIntInHalfBytes);
		for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
			int j = dec & halfByte;
			hexBuilder.setCharAt(i, hexDigits[j]);
			dec >>= numberOfBitsInAHalfByte;
		}
		return hexBuilder.toString().toLowerCase();
	}

	// Test above function.
	public static void main(String[] args) {
		System.out.println("Test...");
		int dec = 70654444;
		String libraryDecToHex = Integer.toHexString(dec);
		String decToHex = decToHex(dec);
		System.out.println("Result from the library : " + libraryDecToHex);
		System.out.println("Result decToHex method : " + decToHex);
	}
}

