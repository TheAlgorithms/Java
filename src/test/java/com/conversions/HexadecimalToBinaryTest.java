package com.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalToBinaryTest {

	@Test
	void test() {
		HexadecimalToBinary hexadecimalToBinary = new HexadecimalToBinary();
        Assertions.assertEquals("10101011", hexadecimalToBinary.hexToBin("AB"), "Incorrect Conversion");
        Assertions.assertEquals("10101101010101111001101", hexadecimalToBinary.hexToBin("56ABCD"), "Incorrect Conversion");
        Assertions.assertEquals("10011101111011010001001", hexadecimalToBinary.hexToBin("4ef689"), "Incorrect Conversion");
        Assertions.assertEquals("10011101111", hexadecimalToBinary.hexToBin("4EF"), "Incorrect Conversion");
        Assertions.assertEquals("101010111100110111101111", hexadecimalToBinary.hexToBin("ABCDEF"), "Incorrect Conversion");
        //It returns -1 if you enter a wrong hexaDecimal
        Assertions.assertEquals("-1", hexadecimalToBinary.hexToBin("K"), "Incorrect Conversion");
	}

}
