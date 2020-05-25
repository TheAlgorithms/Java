package com.conversions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalToDecimalTest {

	@Test
	void testHexadecimalToDecimalTest() {

		HexadecimalToDecimal hexadecimalToDecimal = new HexadecimalToDecimal();
        Assertions.assertEquals("171", hexadecimalToDecimal.hexToDecimal("AB"), "Incorrect Conversion");
        Assertions.assertEquals("5680077", hexadecimalToDecimal.hexToDecimal("56ABCD"), "Incorrect Conversion");
        Assertions.assertEquals("5174921", hexadecimalToDecimal.hexToDecimal("4ef689"), "Incorrect Conversion");
        Assertions.assertEquals("1263", hexadecimalToDecimal.hexToDecimal("4EF"), "Incorrect Conversion");
        Assertions.assertEquals("11259375", hexadecimalToDecimal.hexToDecimal("ABCDEF"), "Incorrect Conversion");
        //It returns -1 if you enter a wrong hexaDecimal
        Assertions.assertEquals("-1", hexadecimalToDecimal.hexToDecimal("K"), "Incorrect Conversion");
        
	}

}
