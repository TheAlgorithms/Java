package com.conversions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalToDecimalTest {

	@Test
	void testHexadecimalToDecimalTest() {

		HexadecimalToDecimal hexadecimalToDecimal = new HexadecimalToDecimal();
		
		//HexadecimaltTesting
        Assertions.assertEquals("171", hexadecimalToDecimal.hexToDecimal("AB"), "Incorrect Conversion");
        Assertions.assertEquals("5680077", hexadecimalToDecimal.hexToDecimal("56ABCD"), "Incorrect Conversion");
        Assertions.assertEquals("5174921", hexadecimalToDecimal.hexToDecimal("4ef689"), "Incorrect Conversion");
        Assertions.assertEquals("1263", hexadecimalToDecimal.hexToDecimal("4EF"), "Incorrect Conversion");
        Assertions.assertEquals("11259375", hexadecimalToDecimal.hexToDecimal("ABCDEF"), "Incorrect Conversion");
        //It returns -1 if you enter a wrong hexaDecimal
        Assertions.assertEquals("-1", hexadecimalToDecimal.hexToDecimal("K"), "Incorrect Conversion");
        
        //Hexadecimal with floating point testing 
        Assertions.assertEquals("10.6875", hexadecimalToDecimal.hexToDecimal("A.B"), "Incorrect Conversion");
        Assertions.assertEquals("1386.737548828125", hexadecimalToDecimal.hexToDecimal("56A.BCD"), "Incorrect Conversion");
        Assertions.assertEquals("78.9630279541015625", hexadecimalToDecimal.hexToDecimal("4e.f689"), "Incorrect Conversion");
        Assertions.assertEquals("0.93359375", hexadecimalToDecimal.hexToDecimal(".EF"), "Incorrect Conversion");
        Assertions.assertEquals("171.8044281005859375", hexadecimalToDecimal.hexToDecimal("AB.CDEF"), "Incorrect Conversion");

	}

}
