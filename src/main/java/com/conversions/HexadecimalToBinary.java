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
		
		String binaryString = "", hexaNumbers = "0123456789ABCDEF", 
				decimalStr ="", binaryStringBefore ="" , binaryStringAfter = "";
		int indexOfHex, decimalNumber = 0, k = 1, n =1, z=1, decimalNumberBefore = 0
				, decimalNumberAfter = 0;
		char letter;
		int binaryArray[] = new int [60];
		int binaryArrayBefore[] = new int [60];
		int binaryArrayAfter[] = new int [60];
		
		hexStr = hexStr.toUpperCase();
		int pointPosition = hexStr.indexOf(".");
		/**
		 * Transform the hexadecimal number to decimal number
		 */
		if ( pointPosition == -1) {
		for ( int i = 0 ;  i < hexStr.length(); i++) {
				letter = hexStr.charAt(i);
				indexOfHex = hexaNumbers.indexOf(letter);
				decimalNumber = 16 * decimalNumber + indexOfHex;
			}
		}
		else {
			for ( int i = 0 ;  i < pointPosition ; i++) {
				letter = hexStr.charAt(i);
				indexOfHex = hexaNumbers.indexOf(letter);
				decimalNumberBefore = 16 * decimalNumberBefore + indexOfHex;
			}
			String decimalNumberBeforeStr = String.valueOf(decimalNumberBefore);

			for ( int i = pointPosition+1 ;  i < hexStr.length() ; i++) {
				letter = hexStr.charAt(i);
				indexOfHex = hexaNumbers.indexOf(letter);
				decimalNumberAfter = 16 * decimalNumberAfter + indexOfHex;
			}

			String decimalNumberAfterStr = String.valueOf(decimalNumberAfter);
			
			decimalStr = decimalNumberBeforeStr + '.' + decimalNumberAfterStr;
			}

		
		
		int pointPositionDec = decimalStr.indexOf(".");
		/**
		 * Check whether the result contains a floating point or not
		 */
		if (pointPositionDec == -1) {
			while (decimalNumber != 0) {
				binaryArray[k++] = decimalNumber % 2;
				decimalNumber = decimalNumber / 2;
			}	

		}else {
			/**
			 * If it contains floating points we need to divide it into two parts before the point and after it
			 */
				while (decimalNumberBefore != 0) {
					binaryArrayBefore[z++] = decimalNumberBefore % 2;
					decimalNumberBefore = decimalNumberBefore / 2;
				}	
				while (decimalNumberAfter != 0) {
					binaryArrayAfter[n++] = decimalNumberAfter % 2;
					decimalNumberAfter = decimalNumberAfter / 2;
				}	

		}

		if(pointPositionDec == -1) {
			for ( int j = k-1 ; j>0 ; j--) {
				binaryString = binaryString + binaryArray[j];
			}	
		}else {
			for ( int j = z-1 ; j>0 ; j--) {
				binaryStringBefore = binaryStringBefore + binaryArrayBefore[j];
			}	

			for ( int j = n-1 ; j>0 ; j--) {
				binaryStringAfter = binaryStringAfter + binaryArrayAfter[j];
			}
			/**
			 * Remove the zeroes in the end of the hexadecimal
			 */
			binaryStringAfter = binaryStringAfter.replaceAll("0*$", "").replaceAll("\\.$", "");
			
			
			binaryString = binaryStringBefore + "." + binaryStringAfter;
		}

		return binaryString;
		
	}
	
}
