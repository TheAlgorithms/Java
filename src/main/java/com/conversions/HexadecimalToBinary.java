package com.conversions;

public class HexadecimalToBinary {
	 /**
     * This method converts a hexadecimal number to
     * a binary number.
     *
     * @param hexStr The hexadecimal number
     * @return The binary number
     */
	
	public String hexToBin (String hexStr) {
		
		String binaryString = "", hexaNumbers = "0123456789ABCDEF";
		int indexOfHex, decimalNumber = 0, k = 1;
		char letter;
		int binaryArray[] = new int [50];
		
		hexStr = hexStr.toUpperCase();
		
		/**
		 * Transform the hexadecimal number to decimal number
		 */
		for ( int i = 0 ;  i < hexStr.length(); i++) {
			letter = hexStr.charAt(i);
			indexOfHex = hexaNumbers.indexOf(letter);
			decimalNumber = 16 * decimalNumber + indexOfHex;
		}

		/**
		 * Transform decimal number to binary and put it in an array
		 */
		while (decimalNumber != 0) {
			binaryArray[k++] = decimalNumber % 2;
			decimalNumber = decimalNumber / 2;
		}
		/**
		 * Put the binary in a string
		 */
		for ( int j = k-1 ; j>0 ; j--) {
			binaryString = binaryString + binaryArray[j];
		}

		return binaryString;
		
	}
	
}
