package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalToBinaryTest {

	@Test
	void test() {
		//HexadecimaltTesting
		HexadecimalToBinary hexadecimalToBinary = new HexadecimalToBinary();
        Assertions.assertEquals("1011110011101111", hexadecimalToBinary.hexToBin("BCEF"), "Incorrect Conversion");
        Assertions.assertEquals("10101101010101111001101", hexadecimalToBinary.hexToBin("56ABCD"), "Incorrect Conversion");
        Assertions.assertEquals("10011101111011010001001", hexadecimalToBinary.hexToBin("4ef689"), "Incorrect Conversion");
        Assertions.assertEquals("10011101111", hexadecimalToBinary.hexToBin("4EF"), "Incorrect Conversion");
        Assertions.assertEquals("101010111100110111101111", hexadecimalToBinary.hexToBin("ABCDEF"), "Incorrect Conversion");
        //It returns -1 if you enter a wrong hexaDecimal
        Assertions.assertEquals("-1", hexadecimalToBinary.hexToBin("K"), "Incorrect Conversion");
        
        
        //Hexadecimal with floating point testing 
        Assertions.assertEquals("101010111100.101111", hexadecimalToBinary.hexToBin("ABC.BC"), "Incorrect Conversion");
        Assertions.assertEquals("10101101010.101111001101", hexadecimalToBinary.hexToBin("56A.BCD"), "Incorrect Conversion");
        Assertions.assertEquals("1001110.1111011010001001", hexadecimalToBinary.hexToBin("4e.f689"), "Incorrect Conversion");
        Assertions.assertEquals("1001110.1111", hexadecimalToBinary.hexToBin("4E.F"), "Incorrect Conversion");
        Assertions.assertEquals("10101011110011011110.1111", hexadecimalToBinary.hexToBin("ABCDE.F"), "Incorrect Conversion");
  
	}

}
