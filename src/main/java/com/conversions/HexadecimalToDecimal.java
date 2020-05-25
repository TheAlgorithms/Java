package com.conversions;


public class HexadecimalToDecimal {

	/**
     * This method converts a Hexadecimal number to a decimal  number
     *
     * @param hexadecimalStr
     * @return decimal number
     */
	 public String hexToDecimal(String hexaStr) {
		 String hexaNumbers = "0123456789ABCDEF";
		 int m, result = 0, decimalNumberBefore = 0, power = -1;
		 Double decimalNumberAfter = 0.0;
		 char letter;
		 String decimalStr;
		 hexaStr = hexaStr.toUpperCase();
		 int pointPosition = hexaStr.indexOf(".");
		 /**
		  * Check whether the number contains a float point or not
		  */
			if ( pointPosition == -1) {
				 for (int i = 0 ; i < hexaStr.length() ; i++) {
					 /**
					  * Letter will store the hexadecimal number as long as we loop through
					  * the string
					  */
					 letter = hexaStr.charAt(i);

					 /**
					  * m is the index of the number that we are looping through in the
					  * hexaNumbers
					  */
					 m = hexaNumbers.indexOf(letter);
					 result = 16*result + m;
				 }
				  decimalStr = String.valueOf(result);

			}
				 else {
					 for ( int i = 0 ;  i < pointPosition ; i++) {
						 letter = hexaStr.charAt(i);
						 m = hexaNumbers.indexOf(letter);
						 decimalNumberBefore = 16*decimalNumberBefore + m;
						}
					 
						String decimalNumberBeforeStr = String.valueOf(decimalNumberBefore);
						
						for ( int i = pointPosition+1 ;  i < hexaStr.length() ; i++) {
							letter = hexaStr.charAt(i);
							m = hexaNumbers.indexOf(letter);
							decimalNumberAfter = (decimalNumberAfter + (Math.pow(16, power))*m);
							power = power-1;
						}
						/**
						 * Retrieve the decimal part of the result
						 */
						String decimalNumberAfterStr = String.valueOf(decimalNumberAfter);
						int indexOfDecimal = decimalNumberAfterStr.indexOf(".");
						decimalNumberAfterStr = decimalNumberAfterStr.substring(indexOfDecimal);
						
						decimalStr = decimalNumberBeforeStr + decimalNumberAfterStr;
				 }
			
					 return decimalStr ;
	    }
}
