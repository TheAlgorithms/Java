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
		 int m, result = 0;
		 char letter;
		 String decimalStr;
		 hexaStr = hexaStr.toUpperCase();
		 
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
		 return decimalStr ;
	    }
}
